<%--
  Created by IntelliJ IDEA.
  User: cao
  Date: 2017/1/9
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>主页</title>
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no" />
    <link href="res/css/mdui.css" rel="stylesheet">
    <link href="res/css/index.css" rel="stylesheet">
    <style>

    </style>
</head>

<body class="mdui-drawer-body-left ">

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
        <button onclick="toggle()" id="toggle" class="mdui-btn mdui-color-pink-accent mdui-m-a-1">toggle</button>
    </div>
    <div class="mainPage">

        <div class="mdui-row mdui-row-margin">
            <div id="top_card_left" class="mdui-col-xs-7">
                <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor">
                    <div class="mdui-card-media top-card-left-img">
                        <img src="res/img/first.jpg"/>
                        <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                            <div class="mdui-card-primary ">
                                <div class="MY-card-tilte">宁静致远</div>
                            </div>
                        </div>
                    </div>
                    <div class="mdui-card-actions smallNone" >
                        <img src="res/img/head.jpg" class="mdui-img-circle">
                        <div class="top-card-left-text">Springmarker</div>
                    </div>
                </div>
            </div>

            <div id="top_card_right" class="mdui-col-xs-5">
                <div class="mdui-card-top-right mdui-hoverable secondColorAndBackgroundColor">
                    <div class="top-card-right">
                        <button onclick="changeColor()" class="mdui-fab mdui-ripple mdui-color-teal rippleButton">
                            <i class="mdui-icon material-icons ">&#xe145;</i>
                        </button>
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

<div id="word" class="mdui-row mdui-row-margin" style="display: none">
    <div class="mdui-col-xs-12" >
        <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor" >
            <div class="MY-mdui-card-media" >
                <img class="MY-card-img" src="res/img/first.jpg"/>
                <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                    <div class="mdui-card-primary ">
                        <div class="MY-card-tilte">宁静致远</div>
                    </div>
                </div>
            </div>
            <a  href="toWord.do?id=" class="mdui-card-content mdui-ripple secondColorAndBackgroundColor href" style="display: block;text-decoration:none;">
                card简介
            </a>
            <div class="mdui-divider" style="margin-top:1px"></div>
            <div class="mdui-card-actions">
                <img  src="res/img/head.jpg">
                <span class="writer-name">Springmarker</span>
                <i mdui-menu="{target: '#example-attr'}"
                   class="mdui-ripple mdui-icon material-icons MY-card-more">more_vert</i>
                <div class="MY-card-data">11月25,2016</div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="res/js/mdui.js"></script>
<script type="text/javascript" src="res/js/index.js"></script>
<script>
    $(function(){
        init();
        function init() {
            $.ajax({url:"getWord.do?pageNum=1&getBlogNum=10",success:function(result){
                if (result.resultCode==200){
                    var list=result.data;
                    for(var i=0;i<list.length;i++){
                        var copyHtml=$('#word').clone();
                        copyHtml.attr("id","word"+(i+1));
                        copyHtml.find(".MY-card-img").attr("src",list[i].backgroundImage);
                        copyHtml.find('.MY-card-tilte').html(list[i].title);
                        copyHtml.find('.mdui-card-content').html(list[i].summary);
                        copyHtml.find('.writer-name').html(list[i].writer);
                        var href=copyHtml.find('.href').attr('href')+list[i].id;
                        copyHtml.find('.href').attr('href',href);
                        $(".mainPage").append(copyHtml);
                        copyHtml.show();
                    }
                }else{
                    console.log("服务器出现错误，错误代码",result.resultCode+',错误内容'+result.worng);
                }
            }});
        }
    });


</script>
</body>
</html>
