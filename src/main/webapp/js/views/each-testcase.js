/**
 * Created by ssge on 2015/11/13.
 */

define(['jquery', 'backbone', 'underscore','text!../../template/each-test.html','views/compose-test'], function ($, Backbone, _, eachTestCaseHtml, ComposeTestView) {
    var EachTestCaseView = Backbone.View.extend({
        tagName: 'div',
        className: 'pure-u-1-4',
        template: _.template(eachTestCaseHtml),
        events: {
            'click .eachtest': 'openTestPopup'
        },
        initialize: function(){
            this.render();
            this.listenTo(this.model, 'change', this.render);
        },
        render: function(){
            this.$el.html(this.template(this.model.toJSON()));
            return this;
        },
        openTestPopup: function(){
            this.testView = new ComposeTestView({'model': this.model});
            this.testView.showPageHandler();
        }
    });
    return EachTestCaseView;
});
