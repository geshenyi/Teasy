/**
 * Created by ssge on 2015/11/13.
 */

define(['jquery', 'backbone', 'underscore','model/test'], function ($, Backbone, _, TestModel){
    var TestCollection = Backbone.Collection.extend({
        model: TestModel,
        url: '/mindtest/tests'

    });
    return TestCollection;
});