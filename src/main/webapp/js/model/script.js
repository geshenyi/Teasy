/**
 * Created by ssge on 2015/11/13.
 */

define(['jquery', 'backbone', 'underscore'], function ($, Backbone, _) {
    var ScriptModel = Backbone.Model.extend({
        defaults: function () {
            return {
                text: ''
            }
        }
    });
    return ScriptModel;
});