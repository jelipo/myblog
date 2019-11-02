<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>Jelipo</title>
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
<#include "webparts/drawer.ftl"/>
<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px"></div>
    <div class="word-list">

        <#include "webparts/top.ftl" />
        <#list list as word>
            <#include "webparts/item/normal.ftl"/>
        </#list>
        <div class="mdui-row mdui-row-margin mdui-text-center" style="color: #919191;font-size: 16px;">
            <a href="${request.contextPath}/list" style="color:#919191">首页只显示最近10条 点击查看更多</a>
        </div>
    </div>
</div>

<!--部件-->
<#include "webparts/footer.ftl" />
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/mdui.min.js?v=${randomStr}"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/main.js?v=${randomStr}"></script>
</html>
