/**
 * Created by ssge on 2015/11/6.
 */

define(['jquery', 'underscore', 'backbone', 'views/page','views/flow','views/compose-test','views/tests','model/test','views/component/toast'],
    function ($, _, Backbone, PageView, FlowView, ComposeTestView, TestsView, TestModel, ToastView) {
    'use strict';

    var AppView = Backbone.View.extend({
        el: '#teasy-app',
        initialize: function () {
            this.panorama = $('.panorama');
            this.render();
        },
        events: {
            'click #pages': 'openPagesView',
            'click #flows': 'openFlowsView',
            'click #compose-test': 'openComposeTestView',
            'click #tests': 'openTestsView',
            'click #show-toast-button': 'showToast'
        },
        render: function () {
//            this.pageView = new PageView();
//            this.flowView = new FlowView();
//            this.composeTestView = new ComposeTestView();
            this.toastView = new ToastView();
            this.panorama.panorama({
                showscrollbuttons: true,
                keyboard: true,
                parallax: true
            });
        },
        openPagesView: function () {
            this.pageView = new PageView();
            this.pageView.showPageHandler();
        },
        openFlowsView: function () {
            this.flowView = new FlowView();
            this.flowView.showPageHandler();
        },
        openComposeTestView: function(){
            this.composeTestView = new ComposeTestView({model: new TestModel});
            this.composeTestView.showPageHandler();
        },
        openTestsView: function(){
            this.testsView = new TestsView();
            this.testsView.showPageHandler();
        },
        showToast: function(e) {
                e.preventDefault();
                var $toast = $( $('#show-toast-button').attr('href')).clone();
                $('#alerts-container').append($toast.addClass('in'));
        }

    });

    return AppView;

});