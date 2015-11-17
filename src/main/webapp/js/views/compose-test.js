/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery', 'backbone', 'underscore', 'text!../../template/compose-test.html', 'codemirror', 'sockjs', 'stompjs', 'uuid', 'model/test', 'views/component/test-result'],
    function ($, Backbone, _, composeTestHtml, CodeMirror, SockJS, Stomp, UUID, TestModel, TestResultView) {
        var ComposeTestView = Backbone.View.extend({
            el: $('#compose-test-div'),
            template: _.template(composeTestHtml),
            events: {
                'click #page-back': 'closePageHandler',
                'click #run-button': 'runTest',
                'click #save-button': 'saveTest',
                'click #command-save': 'confirmSave',
                'click #command-cancel': 'cancelSave'
            },
            initialize: function () {
                console.log('Model=' + JSON.stringify(this.model));
                this.render();
                this.confirmDialog = $('.confirm-dialog');
            },
            render: function () {
                this.$el.html(this.template());
                this.myCodeMirror = CodeMirror(document.getElementById("script-area"), {
                    lineNumbers: true
                });
                this.$('#test-name-input').val(this.model.get('name'));
                this.$('#test-owner-select').val(this.model.get('owner'));
                this.myCodeMirror.setValue(this.model.get('script'));
            },
            showPageHandler: function () {
                this.$el.addClass('composetest-div-show');
            },
            closePageHandler: function () {
                if (typeof this.socket != 'undefined') {
                    this.socket.close();
                }
//            this.stompClient.close();
                this.$el.removeClass('composetest-div-show');
                this.undelegateEvents();
                this.$el.empty();

            },
            cancelSave: function () {
                this.confirmDialog.toggleClass('confirm-dialog-show');
            },
            confirmSave: function () {
                var name = this.$('#test-name-input').val();
                var owner = this.$("#test-owner-select").val();
                var script = this.myCodeMirror.getValue();
                if (typeof this.model != 'undefined' && typeof this.model.get("id") != 'undefined') {
                    this.model.set({name: name, owner: owner, script: script});
                    this.model.save([], {success: this.testCallback});
                } else {
                    var toSaveTestModel = new TestModel({
                        script: script,
                        name: name,
                        owner: owner
                    });
                    toSaveTestModel.save([], {success: this.testCallback, error: this.errorCallback});
                }
                this.confirmDialog.removeClass('confirm-dialog-show');
            },
            testCallback: function () {
                Backbone.trigger('showToastEvent', {type: 'success', content: 'Test has been saved.'})
            },
            errorCallback: function () {
                console.log('error callback');
            },
            saveTest: function () {
                this.confirmDialog.toggleClass('confirm-dialog-show');
            },
            runTest: function () {
                $('#log-area').empty();
                $('#loading-div').fadeIn("slow");
                $('#screenshot-div').fadeOut("slow");
                console.log(this.myCodeMirror.getValue());
                var appendWSContent = function (frame) {
                    console.log(frame);
                    $('#log-area').scrollTop($('#log-area')[0].scrollHeight);
                    $('#log-area').append('<pre>' + frame.body + '</pre>');
                };
                var appendScreenShot = function (frame) {
                    $('#loading-div').fadeOut("slow");
                    $('#screenshot-div').fadeIn("slow");
                    $('#screenshot-div img').attr('src', 'http://localhost:8080/mindtest/screenshot/' + frame.body);
                };
                this.socket = new SockJS("/mindtest/ws");
                this.stompClient = Stomp.over(this.socket);
                var uuid = UUIDjs.create().toString();
                var that = this;
                var connectCallback = function () {
                    that.stompClient.subscribe('/topic/' + uuid, appendWSContent);
                    that.stompClient.subscribe('/topic/image/' + uuid, appendScreenShot);
                    $.ajax({
                        type: 'POST',
                        url: 'http://localhost:8080/mindtest/test/single/run',
                        contentType: 'application/json',
                        data: JSON.stringify({'uuid': uuid, 'steps': that.myCodeMirror.getValue()}),
                        success: function (response) {
                            console.log(response);
                            console.log('run successfully');
                            var testResultView = new TestResultView({model: response})
                            that.socket.close();
                        },
                        error: function () {
                            that.socket.close();
                        },
                        dataType: 'json'
                    })
                };
                var errorCallback = function (error) {
                    alert(error.headers.message);
                };
                this.stompClient.connect("guest", "guest", connectCallback, errorCallback);
            }


        });
        return ComposeTestView;
    });