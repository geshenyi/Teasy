/**
 * Created by ssge on 2015/11/16.
 */

define(['jquery', 'backbone', 'underscore', 'text!../../../template/test-result.html'], function ($, Backbone, _, TestResultHtml) {
    var TestResultView = Backbone.View.extend({
        el: '#result-dialog',
        template: _.template(TestResultHtml),
        events: {
            'click #test-result-closer': 'closeTestResultDialog'
        },
        initialize: function () {
            var that = this;
            console.log(this.model);
            var viewModel = this.convertToViewModel(this.model);
            this.render(viewModel);
        },
        render: function (viewModel) {
            this.$el.empty();
            this.$el.append(this.template(viewModel));
            this.$el.addClass('show-result-dialog');
            var that = this;
        },
        convertToViewModel: function (dataModel) {
            var viewModel = {};
            viewModel['status'] = dataModel.status;
            viewModel['statistic'] = dataModel.statistic;
            viewModel['name'] = "Stubhub UI Test";
            viewModel['owner'] = "Chuck Ge";
            viewModel['steps'] = this.assembleSteps(dataModel.steps);
            viewModel['results'] = this.assembleResults(dataModel);
            return viewModel;
        },
        assembleSteps: function (stepsArray) {
            var steps = '';
            stepsArray.forEach(function (eachStep) {
                steps += '<pre>' + eachStep + '</pre>';
            });
            return steps;
        },
        assembleResults: function (dataModel) {
            var results = '';
            dataModel.executedSteps.forEach(function (eachStep) {
                eachStep.stepLog.forEach(function (eachStepLog) {
                    results += '<pre>' + eachStepLog + '</pre>';
                });
                if (eachStep.exception != null) {
                    results += '<pre>' + eachStep.exception + '</pre>';
                }
            });
            if(dataModel.exception != null) {
                results += '<pre>' + dataModel.exception + '</pre>';
            }
            return results;
        },
        closeTestResultDialog: function(){
            this.$el.empty().removeClass('show-result-dialog');
        }

    });
    return TestResultView;
});