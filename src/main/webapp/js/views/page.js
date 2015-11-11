/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery','backbone','underscore','text!../../template/page.html'],function($, Backbone, _, pageHtml){
    var PageView = Backbone.View.extend({
        el: $('#pages-div'),
        template: _.template(pageHtml),
        events: {
            'click #page-back': 'closePageHandler'
        },
        initialize: function(){
            var that = this;
            this.render();
            Backbone.on('ShowPageEvent', this.showPageHandler)
        },
        render: function(){
            this.$el.html(this.template());
        },
        showPageHandler: function(){
//            var that = this;
            this.$el.addClass('pages-div-show');
        },
        closePageHandler: function(){
            this.$el.removeClass('pages-div-show');
        }


    });
    return PageView
});