/**
 * Created by ssge on 2015/11/3.
 */

$(document).ready(function(){
    var appendWSContent = function(frame){
        console.log(frame);
        $('#wscontent').append('<div>'+frame.body+'</div>');
    }
    var socket = new SockJS("/mindtest/ws");
    var stompClient = Stomp.over(socket);
    var connectCallback = function() {
        stompClient.subscribe('/topic/price', appendWSContent);
    };
    var errorCallback = function(error) {
        alert(error.headers.message);
    };
    stompClient.connect("guest", "guest", connectCallback, errorCallback);

    buttonClicked = function(){
        $.ajax({
            type: "GET",
            url: "http://10.249.74.134:8080/mindtest/test",
            contentType: "application/json",
            success: function(){
                console.log("success");
            }
        })
    }

    inputClicked = function(){
        $.ajax({
            type: "POST",
            url: "http://10.249.74.134:8080/mindtest/chat",
            contentType: "text/plain",
            data: $("#chatinput").val(),
            success: function(){
                console.log("success");
            }
        })
    }
});