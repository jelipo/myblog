<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="//res.springmarker.com/blog/res/css/my-mdui.min.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">
</head>

<body class="mdui-drawer-body-left ">

<i onclick="toggle()" class="mdui-icon material-icons"
   style="position:fixed;left: 5px;top: 10px;z-index: 1024;">menu</i>


<%@include file="WEB-INF/jsp/webParts/drawer.jsp" %>


<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px">

    </div>
    <div class="mainPage">

        <div class="mdui-row mdui-row-margin">
            <div id="top_card_left" class="mdui-col-xs-7">
                <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor">
                    <div class="mdui-card-media top-card-left-img">
                        <img src="res/img/top.jpg"/>
                        <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                            <div class="mdui-card-primary ">
                                <div class="MY-card-tilte">宁静致远</div>
                            </div>
                        </div>
                    </div>
                    <div class="mdui-card-actions smallNone">
                        <img src="res/img/head.jpg" class="mdui-img-circle">
                        <div class="top-card-left-text">Springmarker</div>
                    </div>
                </div>
            </div>

            <div id="top_card_right" class="mdui-col-xs-5">
                <button onclick="" style="z-index: 1000"
                        class="mdui-fab mdui-ripple mdui-color-teal rippleButton">
                    <i class="mdui-icon material-icons">attach_file</i>
                </button>
                <div id="top-right-text" style="position:absolute;z-index: 1;"
                     class="mdui-card-top-right mdui-hoverable secondColorAndBackgroundColor ">
                    <div class="top-card-right" style="padding: 20px;box-sizing:border-box;">
                        <div style="width: 100%;height: 100%;color:#9E9E9E">
                            <div style="font-size: 35px;">欢迎</div>
                            <div style="font-size: 15px;line-height:25px;padding-top: 20px">
                                　　此站主要用于我个人的学习记录使用，充其量算半个Blog，如果其中的内容正好能帮到您，那会是我莫大的荣幸。<br>
                                　　此站大部分为原创内容，也会有一定量的转载内容，转载内容版权归原创者所有。<br>
                            </div>
                            <div style="font-size: 14px;line-height:24px;padding-top: 7px">
                                　　本站服务器由于性能较小，所以做了一些限制,所以可能会对您正常的浏览造成影响，一般正常浏览是没有问题的。
                            </div>
                        </div>
                    </div>
                </div>
                <div id="top-right-img"  style="position:absolute;z-index: 2;overflow: hidden"
                     class="mdui-card-top-right mdui-hoverable secondColorAndBackgroundColor top-card-rght-img">
                    <div class="top-card-right" style="box-sizing:border-box;">
                        <img id="rightImg" class="" src="res/img/rightImg.jpg" width="100%">
                    </div>
                </div>
            </div>

        </div>


    </div>
</div>

<%@include file="WEB-INF/jsp/webParts/footer.html" %>
<div style="display: none">
    <!-- 单个item-->
    <div id="word" class="mdui-row mdui-row-margin" style="display: none">
        <div class="mdui-col-xs-12">
            <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor">
                <a class="MY-mdui-card-media">
                    <img class="MY-card-img" src="res/img/first.jpg"/>
                    <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                        <div class="mdui-card-primary ">
                            <div class="MY-card-tilte">宁静致远</div>
                        </div>
                    </div>
                </a>
                <a href="toWord.do?id=" class="mdui-card-content mdui-ripple secondColorAndBackgroundColor href"
                   style="display: block;text-decoration:none;">
                    card简介
                </a>
                <div class="mdui-divider" style="margin-top:1px"></div>
                <div class="mdui-card-actions">
                    <img src="res/img/head.jpg">
                    <span class="writer-name">Springmarker</span>
                    <i mdui-menu="{target: '#example-attr'}"
                       class="mdui-ripple mdui-icon material-icons MY-card-more">more_vert</i>
                    <div class="MY-card-data">11月25,2016</div>
                </div>
            </div>
        </div>
    </div>
    <!-- 单个item-->

    <!--底部信息“查看更多”-->
    <a href="moreWords.do?pageNum=1" id="lookMore" class="mdui-row mdui-row-margin mdui-text-center"
       style="display: block;color: #919191;font-size: 16px;">
        查看更多
    </a>
    <!--底部信息“查看更多”-->

</div>
</body>
<script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="//res.springmarker.com/other/js/mdui.min.js"></script>
<script type="text/javascript" src="res/js/main.js"></script>
<script>
    $(function () {
        indexAppendWord(getAjaxData("getWord.do?pageNum=1&getBlogNum=10&type=0"));
    });
    function indexAppendWord(list) {
        for (var i = 0; i < list.length; i++) {
            var copyHtml = $('#word').clone();
            copyHtml.attr("id", "word" + (i + 1));
            copyHtml.find(".MY-card-img").attr("src", list[i].backgroundImage);
            copyHtml.find('.MY-card-tilte').html(list[i].title);
            copyHtml.find('.mdui-card-content').html(list[i].summary);
            copyHtml.find('.writer-name').html(list[i].writer);
            copyHtml.find('.MY-card-data').html(list[i].date);
            var href = copyHtml.find('.href').attr('href') + list[i].id;
            copyHtml.find('.href').attr('href', href);
            copyHtml.find('.MY-mdui-card-media').attr('href', href);
            $(".mainPage").append(copyHtml);
            copyHtml.fadeIn(1000);
        }
        $(".mainPage").append($('#lookMore').clone());
        return list.length;
    }
</script>

</html>
