/**
 * Created by ssge on 2015/11/3.
 */
require.config({
    packages: [
        {
            "name": "codemirror",
            "location": "../bower_components/codemirror",
            "main": "lib/codemirror"
        }
    ],
    shim: {
        underscore: {
            exports: '_'
        },
        backbone: {
            deps: [
                'underscore',
                'jquery'
            ],
            exports: 'Backbone'
        },
        sockjs: {
            exports: 'SockJS'
        },
        stompjs: {
            exports: 'Stomp'
        },
        uuid: {
            exports: 'UUIDjs'
        },
        'bootmetro-charms': {
            deps: [
                'jquery'
            ],
            exports: 'BootmetroCharms'
        },
        'bootmetro-panorama': {
            deps: [
                'jquery'
            ],
            exports: 'BootmetroPanorama'
        },
        'bootmetro-pivot': {
            deps: [
                'jquery'
            ],
            exports: 'BootmetroPivot'
        },
        'jquery-touchswipe': {
            deps: [
                'jquery'
            ],
            exports: 'jQueryTouchSwipe'
        },
        'jquery-mousewheel': {
            deps: [
                'jquery'
            ],
            exports: 'jQueryMouseWheel'
        },
        'bootstrap': {
            deps: [
                'jquery'
            ],
            exports: 'Bootstrap'
        }

    },
    paths: {
        jquery: '../bower_components/jquery/dist/jquery',
        underscore: '../bower_components/lodash/lodash',
        backbone: '../bower_components/backbone/backbone',
        sockjs: '../bower_components/sockjs-client/dist/sockjs-1.0.3',
        stompjs: '../bower_components/stomp-websocket/lib/stomp',
        'bootmetro-charms': 'bootmetro/dist/assets/js/bootmetro-charms',
        'bootmetro-panorama': 'bootmetro/dist/assets/js/bootmetro-panorama',
        'bootmetro-pivot': 'bootmetro/dist/assets/js/bootmetro-pivot',
        'jquery-mousewheel': 'bootmetro/dist/assets/js/jquery.mousewheel',
        'jquery-touchswipe': 'bootmetro/dist/assets/js/jquery.touchSwipe',
        'bootstrap': 'bootmetro/dist/assets/js/bootstrap',
        'text': 'text/text'
    }
});

require(['backbone', 'views/app','bootstrap','bootmetro-charms','bootmetro-panorama','bootmetro-pivot','jquery-mousewheel','jquery-touchswipe'],function(Backbone, AppView){
    new AppView();


//    $('.panorama').panorama({
//        showscrollbuttons: true,
//        keyboard: true,
//        parallax: true
//    });

//      $(".panorama").perfectScrollbar();

//    $('#pivot').pivot();



});

//$(document).ready(function(){
//    var appendWSContent = function(frame){
//        console.log(frame);
//        $('#wscontent').append('<div>'+frame.body+'</div>');
//    }
//    var socket = new SockJS("/mindtest/ws");
//    var stompClient = Stomp.over(socket);
//    var connectCallback = function() {
//        stompClient.subscribe('/topic/price', appendWSContent);
//    };
//    var errorCallback = function(error) {
//        alert(error.headers.message);
//    };
//    stompClient.connect("guest", "guest", connectCallback, errorCallback);
//
//    buttonClicked = function(){
//        $.ajax({
//            type: "GET",
//            url: "http://10.249.74.134:8080/mindtest/test",
//            contentType: "application/json",
//            success: function(){
//                console.log("success");
//            }
//        })
//    }
//
//    inputClicked = function(){
//        $.ajax({
//            type: "POST",
//            url: "http://10.249.74.134:8080/mindtest/chat",
//            contentType: "text/plain",
//            data: $("#chatinput").val(),
//            success: function(){
//                console.log("success");
//            }
//        })
//    }
//
//
//});