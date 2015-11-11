/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery','backbone','underscore','text!../../template/flow.html'],function($, Backbone, _, flowHtml){
    var FlowView = Backbone.View.extend({
        el: $('#flows-div'),
        template: _.template(flowHtml),
        events: {
            'click #page-back': 'closePageHandler'
        },
        initialize: function(){
            this.render();
        },
        render: function(){
            this.$el.html(this.template());
        },
        showPageHandler: function(){
            this.$el.addClass('pages-div-show');
        },
        closePageHandler: function(){
            this.$el.removeClass('pages-div-show');
        }


    });
    return FlowView;
});