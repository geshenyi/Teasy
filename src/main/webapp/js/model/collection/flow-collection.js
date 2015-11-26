/**
 * Created by ssge on 2015/11/24.
 */

define(['jquery', 'backbone', 'underscore','model/test'], function ($, Backbone, _, TestModel){
    var FlowCollection = Backbone.Collection.extend({
        model: TestModel,
        url: '/mindtest/flow'

    });
    return FlowCollection;
});