/**
 * Created by ssge on 2015/11/13.
 */

define(['jquery', 'backbone', 'underscore'], function ($, Backbone, _) {
    var TestModel = Backbone.Model.extend({
//        idAttribute: "_id",
        url: '/mindtest/tests',
        defaults: function () {
            return {
                script: '',
                name: '',
                owner: ''
            }
        }
    });
    return TestModel;
});