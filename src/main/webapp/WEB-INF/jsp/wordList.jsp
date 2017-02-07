<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="res/css/mdui.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/main.js"></script>
</head>

<body class="mdui-drawer-body-left ">

<!--参数-->
<input id="pageNum" value="${pageNum}" type="hidden">
<!--参数-->

<i onclick="toggle()" class="mdui-icon material-icons"
   style="position:fixed;left: 5px;top: 10px;z-index: 1024;">menu</i>


<div id="drawer" class="mdui-drawer leftbar secondColorAndBackgroundColor">
    <div class="leftbar-head" style="background-image: url('res/img/first.jpg')">
        <img src="res/img/head.jpg" class="mdui-img-circle">
        <div class="leftbar-head-msg">
            <div>Springmarker</div>
            <span>A coder</span>
        </div>
    </div>
    <div class="leftbar-navi mdui-ripple" onclick="window.location.href='/'">
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


    </div>
</div>
<!-- 页脚-->
<footer class="MY-footer">
    <div class="mdui-center  mdui-container footer-main">
        <div class="mdui-col-xs-4 footer-other">
            <div>导 航 ：</div>
            <a href="/">网站主页</a>
            <a href="https://github.com/springmarker/MyBlog" target="_blank">此站GitHub</a>
        </div>
        <div class="mdui-col-xs-4 footer-other">
            <div>联 系 ：</div>
            <a href="mailto:springmarker@163.com">发送邮件</a>
            <a>向我留言</a>
        </div>
        <div class="mdui-col-xs-4 footer-other">
            <a href="https://github.com/springmarker" target="_blank">
                <img width="80px" src="res/img/github.png">
            </a>
        </div>
    </div>
    <div class="mdui-center mdui-text-center footer-bottom">
        <a>©2016 Cao. All rights reserved.</a>
        <a href="http://www.miitbeian.gov.cn" target="_blank">备案号：鲁ICP备16035555号-2</a>
    </div>
</footer>
<!-- 页脚-->

<!--控件库-->
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

</div>
<!--控件库-->

<script>
    $(function () {
        var pageNumNow = parseInt($("#pageNum").val());
        appendWord(getAjaxData("getWord.do?pageNum=" + pageNumNow + "&getBlogNum=10"));


        //监听滚动条到底部
        var appendFlag = true;
        $(window).scroll(function () {
            if (appendFlag && ($(window).scrollTop() + 20 > $(document).height() - $(window).height())) {
                appendFlag = false;
                var getWordNum = appendWord(getAjaxData("getWord.do?pageNum=" + (pageNumNow + 1) + "&getBlogNum=10"));
                if (getWordNum == 0) {
                    appendNoMore();
                    return;
                }
                pageNumNow = pageNumNow + 1;
                appendFlag = true;
            }
        });


    });
    function appendWord(list) {
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
            copyHtml.show();
        }
        return list.length;
    }
    function appendNoMore() {
        var copyHtml = $('#noMore').clone();
        $(".mainPage").append(copyHtml);
    }
</script>
</body>
</html>
