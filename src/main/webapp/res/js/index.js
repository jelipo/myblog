/**
 * Created by cao on 2017/1/9.
 */

$(function () {
    var leftbar = new mdui.Drawer('#drawer');
    function toggle() {
        leftbar.toggle();
    }
    var dayOrNightNow = 'day';
    function changeColor() {
        if (dayOrNightNow == 'day') {
            var Color = '#eaeaea';
            var BackgroundColor = '#41535B';
            var mainBackground = '#263339';
            $('.secondColorAndBackgroundColor').css('color', Color);
            $('.secondColorAndBackgroundColor').css('background-color', BackgroundColor);
            $("body").css('background-color', mainBackground);
            dayOrNightNow = 'night';
        } else {
            var Color = '#696969';
            var BackgroundColor = '#ffffff';
            var mainBackground = '#e9ebec';
            $('.secondColorAndBackgroundColor').css('color', Color);
            $('.secondColorAndBackgroundColor').css('background-color', BackgroundColor);
            $("body").css('background-color', mainBackground);
            dayOrNightNow = 'day';
        }
    }


    $(window).resize(function () {
        change(false);
    });
    change(true);
    var lastWidth = 0;
    function change(isFirst) {
        var minWidth = 800;
        var width = $(this).width();
        if (isFirst || !(lastWidth < minWidth & width < minWidth) || !(lastWidth > minWidth & width > minWidth)) {
            if (width < minWidth) {
                $('#top_card_left').removeClass('mdui-col-xs-7');
                $('#top_card_left').addClass('mdui-col-xs-12');
                $('#top_card_right').removeClass('mdui-col-xs-5');
                $('#top_card_right').addClass('mdui-col-xs-12');
            }
            else {
                $('#top_card_left').removeClass('mdui-col-xs-12');
                $('#top_card_left').addClass('mdui-col-xs-7');
                $('#top_card_right').removeClass('mdui-col-xs-12');
                $('#top_card_right').addClass('mdui-col-xs-5');
            }
        }
    }



});
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg); //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

/*
 * jQuery resize event - v1.1 - 3/14/2010
 * http://benalman.com/projects/jquery-resize-plugin/
 *
 * Copyright (c) 2010 "Cowboy" Ben Alman
 * Dual licensed under the MIT and GPL licenses.
 * http://benalman.com/about/license/
 */
(function($,h,c){var a=$([]),e=$.resize=$.extend($.resize,{}),i,k="setTimeout",j="resize",d=j+"-special-event",b="delay",f="throttleWindow";e[b]=250;e[f]=true;$.event.special[j]={setup:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.add(l);$.data(this,d,{w:l.width(),h:l.height()});if(a.length===1){g()}},teardown:function(){if(!e[f]&&this[k]){return false}var l=$(this);a=a.not(l);l.removeData(d);if(!a.length){clearTimeout(i)}},add:function(l){if(!e[f]&&this[k]){return false}var n;function m(s,o,p){var q=$(this),r=$.data(this,d);r.w=o!==c?o:q.width();r.h=p!==c?p:q.height();n.apply(this,arguments)}if($.isFunction(l)){n=l;return m}else{n=l.handler;l.handler=m}}};function g(){i=h[k](function(){a.each(function(){var n=$(this),m=n.width(),l=n.height(),o=$.data(this,d);if(m!==o.w||l!==o.h){n.trigger(j,[o.w=m,o.h=l])}});g()},e[b])}})(jQuery,this);

