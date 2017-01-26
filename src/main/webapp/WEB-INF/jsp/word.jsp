<%--
  Created by IntelliJ IDEA.
  User: cao
  Date: 2017/1/13
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>文章</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="res/css/mdui.css" rel="stylesheet">
    <link href="res/css/index.css" rel="stylesheet">
    <style>
        .card-pageOther {
            height: 100px;
            padding: 20px;

        }

        .card-turnPage-up {
            color: #366fb4;
            width: 200px;
            float: left;
            font-size: 15px;
        }

        .card-turnPage-down {
            color: #366fb4;
            width: 200px;
            float: right;
            font-size: 15px;
        }
        .word-msg-height{
            height: 55px;
        }
        @media only screen and (max-width: 800px) {
            .word-msg-height {
                height: 38px;
            }
        }
    </style>
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/index.js"></script>
</head>

<body class="mdui-drawer-body-left ">
<script>
    function changeFrameHeight(){
        var iframeHeight = $("#wordIframe").contents().find("body").height();
        $("#wordIframe").height(iframeHeight + 50);
        setTimeout("changeFrameHeight()", 2000);
    }
    $(function () {
        $('#wordIframe').attr("src",$('#htmlSrc').val());

    });
</script>
<input id="htmlSrc" value="${htmlSrc}" type="hidden">

<i onclick="toggle()" class="mdui-icon material-icons" style="position:fixed;left: 5px;top: 10px;z-index: 1024;">menu</i>
<div id="drawer" class="mdui-drawer leftbar secondColorAndBackgroundColor">
    <div class="leftbar-head" style="background-image: url('res/img/first.jpg')">
        <img src="res/img/head.jpg" class="mdui-img-circle">
        <div class="leftbar-head-msg">
            <div>Springmarker</div>
            <span>A coder</span>
        </div>
    </div>
    <div class="leftbar-navi mdui-ripple">
        <i class="mdui-icon material-icons leftbar-navi-icon">home</i>
        <span style="font-weight:bold">主页</span>
    </div>
    <div class="mdui-collapse" mdui-collapse>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-ripple">
                <div class="leftbar-navi">
                    <i class="mdui-icon material-icons leftbar-navi-icon">assignment</i>
                    <span style="font-weight:bold">文章</span>
                    <i class="leftbar-navi-zhedie mdui-icon material-icons mdui-collapse-item-arrow">expand_more</i>
                </div>
            </div>
            <div class="mdui-collapse-item-body ">
                <div class="leftbar-item mdui-ripple">技术</div>
                <div class="leftbar-item mdui-ripple">随写</div>
            </div>
        </div>
    </div>
    <div style="margin: 5px 0 5px 0;" class="mdui-divider"></div>
    <div class="leftbar-other mdui-ripple">
        <span>关于我</span>
    </div>
    <div class="leftbar-other mdui-ripple">
        <span>所有文章</span>
        <p>10</p>
    </div>
    <div class="leftbar-other mdui-ripple">
        <span>评论</span>
        <p>12</p>
    </div>
    <div style="margin: 5px 0 5px 0;" class="mdui-divider"></div>
    <div class="mdui-collapse" mdui-collapse>
        <div class="mdui-collapse-item ">
            <div class="mdui-collapse-item-header mdui-ripple">
                <div class="leftbar-navi">
                    <span style="padding-left: 8px">友情链接</span>
                    <i class="leftbar-navi-zhedie mdui-icon material-icons mdui-collapse-item-arrow">expand_more</i>
                </div>
            </div>
            <div class="mdui-collapse-item-body ">
                <div class="leftbar-item mdui-ripple">技术</div>
                <div class="leftbar-item mdui-ripple">随写</div>
            </div>
        </div>
    </div>
</div>


<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px">

    </div>
    <div class="mainPage">

        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12">
                <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor " >
                    <div class="MY-mdui-card-media">
                        <img class="MY-card-img" src="res/img/first.jpg"/>
                        <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                            <div class="mdui-card-primary ">
                                <div class="MY-card-tilte">宁静致远</div>
                            </div>
                        </div>
                    </div>

                    <div class="mdui-card-actions word-msg-height">
                        <img src="res/img/head.jpg">
                        <span>Springmarker</span>
                        <i mdui-menu="{target: '#example-attr'}"
                           class="mdui-ripple mdui-icon material-icons MY-card-more">more_vert</i>
                        <div class="MY-card-data">11月25,2016</div>
                    </div>
                    <div class="mdui-divider"></div>
                    <div class="card-page">
                        <iframe id="wordIframe" src="" scrolling="no"
                                onload="changeFrameHeight()" frameborder="0" height="100%" width="100%" style="transition: 2s">
                        </iframe>
                    </div>
                    <div class="mdui-divider"></div>
                    <div class="card-pageOther">
                        <div class="card-turnPage-up">上一篇:<span>多线程</span></div>
                        <div class="card-turnPage-down"><span>多线程的理解和使用</span>:下一篇</div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- 页脚-->
<footer style="height: 250px;margin-top: 70px" class="mdui-color-light-green">
    <p>Posted by: W3School</p>
    <p>Contact information: <a href="mailto:someone@example.com">someone@example.com</a>.</p>
</footer>

</body>
</html>