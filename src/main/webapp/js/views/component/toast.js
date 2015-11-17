/**
 * Created by ssge on 2015/11/15.
 */

define(['jquery','backbone','underscore','text!../../../template/toast.html'],function($, Backbone, _, ToastHtml){
    var ToastView = Backbone.View.extend({
        el: '#toast-div',
        template: _.template(ToastHtml),
        initialize: function(){
            var that = this;
            Backbone.on('showToastEvent', function(params){
                that.render(params)
            })
        },
        render: function(params){
            this.$el.empty();
            this.$el.append(this.template({type:params.type,content:params.content}));
            this.$el.addClass('show-toast-div');
            var that = this;
            setTimeout(function(){
                that.$el.removeClass('show-toast-div');
            }, 5000)
        }

    });
    return ToastView;
});