/**
 * Created by ssge on 2015/11/6.
 */

define(['jquery', 'underscore', 'backbone', 'views/page','views/flow','views/compose-test'], function ($, _, Backbone, PageView, FlowView, ComposeTestView) {
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
            'click #compose-test': 'openComposeTestView'
        },
        render: function () {
            this.pageView = new PageView();
            this.flowView = new FlowView();
            this.composeTestView = new ComposeTestView();
            this.panorama.panorama({
                showscrollbuttons: true,
                keyboard: true,
                parallax: true
            });
        },
        openPagesView: function () {
            this.pageView.showPageHandler();
        },
        openFlowsView: function () {
            this.flowView.showPageHandler();
        },
        openComposeTestView: function(){
            this.composeTestView.showPageHandler();
        }

    });

    return AppView;

});