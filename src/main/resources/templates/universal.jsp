<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>主页 - Springmarker</title>
    <link rel="bookmark"  type="image/x-icon"  href="/res/img/favicon.ico"/>
    <link rel="shortcut icon" href="/res/img/favicon.ico">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="https://res.springmarker.com/blog/res/css/my-mdui.min.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">
    <link href="res/css/video-js.min.css" rel="stylesheet">
    <script type="text/javascript" src="res/js/video.min.js"></script>
    <script type="text/javascript" src="res/js/videojs-contrib-hls.min.js"></script>

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
                        <video id=example-video width=600 height=400 class="video-js vjs-default-skin" controls>
                            <source src="http://hls.springmarker.com/getM3u8.do" type="application/x-mpegURL">
                        </video>
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
<script type="text/javascript" src="res/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="res/js/mdui.min.js"></script>
<script type="text/javascript" src="res/js/main.js"></script>
</html>
