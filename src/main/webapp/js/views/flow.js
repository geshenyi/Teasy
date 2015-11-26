/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery','backbone','underscore','text!../../template/flow.html','model/collection/flow-collection','views/each-flow'],
    function($, Backbone, _, flowHtml, FlowCollection, EachFlowView){
    var FlowView = Backbone.View.extend({
        el: $('#flows-div'),
        template: _.template(flowHtml),
        events: {
            'click #page-back': 'closePageHandler',
            'scroll': 'handleScroll'
        },
        initialize: function(){
            var that = this;
            this.flowCollection = new FlowCollection();
            this.listenTo(this.flowCollection, 'add', this.addOneTest);
            this.flowCollection.fetch();
            this.render();
            $('.flows-body').bind('scroll', this.handleScroll);
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
        },
        addOneTest: function(eachFlow){
            var eachFlowView = new EachFlowView({model: eachFlow});
            this.$("#flows").append(eachFlowView.render().el);
            console.log('add one done');
        },
        handleScroll: function(){
            if($('.flows-body').scrollTop() > 0){
                $('.flows-command-bar').addClass('fixed');
            }else{
                $('.flows-command-bar').removeClass('fixed');
            }
        }


    });
    return FlowView;
});