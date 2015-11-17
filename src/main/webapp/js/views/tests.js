/**
 * Created by ssge on 2015/11/8.
 */
define(['jquery','backbone','underscore','text!../../template/tests.html','views/compose-test','model/collection/test-collection','views/each-testcase'],
    function($, Backbone, _, testsHtml, ComposeTestView, TestCollection, EachTestCaseView){
    var TestsView = Backbone.View.extend({
        el: $('#tests-div'),
        template: _.template(testsHtml),
        events: {
            'click #page-back': 'closePageHandler',
            'scroll': 'handleScroll'
        },
        initialize: function(){
            var that = this;
            this.testCollection = new TestCollection();
            this.listenTo(this.testCollection, 'add', this.addOneTest);
            this.testCollection.fetch();
            this.render();
            $('.tests-body').bind('scroll', this.handleScroll);
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
        addOneTest: function(eachTest){
            var eachTestCaseView = new EachTestCaseView({model: eachTest});
            this.$("#testcases").append(eachTestCaseView.render().el);
            console.log('add one done');
        },
        handleScroll: function(){
            if($('.tests-body').scrollTop() > 0){
                $('.tests-command-bar').addClass('fixed');
            }else{
                $('.tests-command-bar').removeClass('fixed');
            }
        }


    });
    return TestsView;
});