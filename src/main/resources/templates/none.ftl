<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>空白 - Springmarker</title>
    <link rel="bookmark"  type="image/x-icon"  href="${request.contextPath}/static/img/favicon.ico"/>
    <link rel="shortcut icon" href="${request.contextPath}/static/img/favicon.ico">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="${request.contextPath}/static/css/my-mdui.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/main.css" rel="stylesheet">

</head>

<body class="mdui-drawer-body-left ">
<%@include file="webparts/drawer.jsp" %>
<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px"></div>
    <div class="mainPage">
        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor ">

                    <div style="padding: 30px 20px 30px 20px">
                        <h1>he llo</h1>
                    </div>


                </div>
            </div>
        </div>
    </div>
</div>

<!--部件-->
<div style="display: none">


</div>
<!--部件-->

<%@include file="webparts/footer.html" %>
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/mdui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/main.js"></script>

</html>
