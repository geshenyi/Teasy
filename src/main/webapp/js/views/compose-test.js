/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery','backbone','underscore','text!../../template/compose-test.html','codemirror','sockjs','stompjs','uuid'],
    function($, Backbone, _, composeTestHtml, CodeMirror, SockJS, Stomp, UUID){
    var ComposeTestView = Backbone.View.extend({
        el: $('#compose-test-div'),
        template: _.template(composeTestHtml),
        events: {
            'click #page-back': 'closePageHandler',
            'click #run-button': 'runTest'
        },
        initialize: function(){

            this.render();
        },
        render: function(){
            this.$el.html(this.template());
            this.myCodeMirror = CodeMirror(document.getElementById("script-area"),{
                lineNumbers: true
            });
        },
        showPageHandler: function(){
            this.$el.addClass('pages-div-show');
        },
        closePageHandler: function(){
            this.$el.removeClass('pages-div-show');
        },
        runTest: function(){
            $('#log-area').empty();
            $('#loading-div').fadeIn("slow");
            $('#screenshot-div').fadeOut("slow");
            console.log(this.myCodeMirror.getValue());
            var appendWSContent = function(frame){
                console.log(frame);
                $('#log-area').scrollTop($('#log-area')[0].scrollHeight);
                $('#log-area').append('<pre>'+frame.body+'</pre>');
            };
            var appendScreenShot = function(frame){
                $('#loading-div').fadeOut("slow");
                $('#screenshot-div').fadeIn("slow");
                $('#screenshot-div img').attr('src', 'http://localhost:8080/mindtest/screenshot/'+frame.body);
            };
            var socket = new SockJS("/mindtest/ws");
            var stompClient = Stomp.over(socket);
            var uuid = UUIDjs.create().toString();
            var that = this;
            var connectCallback = function() {
                stompClient.subscribe('/topic/'+uuid, appendWSContent);
                stompClient.subscribe('/topic/image/'+uuid, appendScreenShot);
                $.ajax({
                    type: 'POST',
                    url: 'http://localhost:8080/mindtest/test/single/run',
                    contentType: 'application/json',
                    data: JSON.stringify({'uuid': uuid, 'steps': that.myCodeMirror.getValue()}),
                    success: function(){
                        console.log('run successfully');
                    },
                    dataType: 'json'
                })
            };
            var errorCallback = function(error) {
                alert(error.headers.message);
            };
            stompClient.connect("guest", "guest", connectCallback, errorCallback);
        }


    });
    return ComposeTestView;
});