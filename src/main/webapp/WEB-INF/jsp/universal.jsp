<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="https://res.springmarker.com/blog/res/css/my-mdui.min.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">

</head>

<body class="mdui-drawer-body-left ">
<%@include file="webParts/drawer.jsp" %>
<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px"></div>
    <div class="mainPage">
        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor ">

                    <div style="padding: 30px 20px 30px 20px">
                        <h1>hello</h1>
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
<%@include file="webParts/footer.html" %>
</body>
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
<script type="text/javascript" src="res/js/main.js"></script>
</html>
