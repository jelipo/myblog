<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>更多 - Jelipo</title>
    <link rel="bookmark" type="image/x-icon" href="${request.contextPath}/static/img/favicon.ico?v=${randomStr}"/>
    <link rel="shortcut icon" href="${request.contextPath}/static/img/favicon.ico?v=${randomStr}">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="${request.contextPath}/static/css/mdui.min.css?v=${randomStr}" rel="stylesheet">
    <link href="${request.contextPath}/static/css/main.css?v=${randomStr}" rel="stylesheet">
    <link href="${request.contextPath}/static/css/iconfont.css" rel="stylesheet">

</head>

<body class="mdui-drawer-body-left ">
<input id="contextPath" value="${request.contextPath}" type="hidden">
<input id="type" value="${type!"all"}" type="hidden">
<input id="page" value="${page!"1"}" type="hidden">
<#include "webparts/drawer.ftlh"/>
<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px"></div>
    <div class="word-list">
        <#list list as word>
            <#include "webparts/item/normal.ftlh"/>
        </#list>
    </div>
</div>

<!--部件-->
<div style="display: none">
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
<#include "webparts/footer.ftlh" />
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/mdui.min.js?v=${randomStr}"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/main.js?v=${randomStr}"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/wordList.js?v=${randomStr}"></script>
</html>