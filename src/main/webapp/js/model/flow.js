/**
 * Created by ssge on 2015/11/24.
 */

define(['jquery','backbone','underscore'], function($, Backbone, _){
   'use strict';
    var FlowModel = Backbone.Model.extend({
        url: '/mindtest/flow',
        defaults: function () {
            return {
                script: '',
                name: '',
                owner: ''
            }
        }
    });
    return FlowModel;
});