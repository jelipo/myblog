<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>更多</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="res/css/mdui.min.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/main.js"></script>
</head>

<body class="mdui-drawer-body-left ">

<!--参数-->
<input id="pageNum" value="${pageNum}" type="hidden">
<!--参数-->
<%@include file="webParts/drawer.html" %>
<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px">

    </div>
    <div class="mainPage">


    </div>
</div>

<%@include file="webParts/footer.html" %>
<!--部件-->
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

    <!--底部信息“没有更多了”-->
    <div id="noMore" class="mdui-row mdui-row-margin mdui-text-center" style="color: #919191;font-size: 16px;">
        没有更多了
    </div>
    <!--底部信息“查看更多”-->

    <!--底部信息“翻页”-->
    <div id="turnPage" class="mdui-row mdui-row-margin mdui-text-center" style="color: #919191;font-size: 16px;">
        翻页
    </div>
    <!--底部信息“翻页”-->

</div>
<!--部件-->

<script type="text/javascript" src="res/js/wordList.js"></script>
</body>

</html>
