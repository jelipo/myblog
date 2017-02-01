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

        .word-msg-height {
            height: 55px;
        }

        .card-comment {
            padding: 20px;
        }

        .comment-mian {
            padding: 7px 0px 13px 0px;
            width: 100%;
            min-height: 70px;
            border-bottom: 1px solid #d0d0d0
        }

        .comment-mian-msg {
            padding: 3px 0px 3px 0px;
            width: 100%;
            height: 32px;
        }

        .comment-mian-msg > img {
            height: 100%;
            float: left;
            margin-right: 8px;
        }

        .comment-mian-msg > div {
            float: left;
        }

        .comment-mian-msg > span {
            float: right;
            margin-right: 40px;
        }

        .comment-mian-msg-lz {
            font-size: 14px;
            color: black;
            padding: 1px 0px 3px 0px;
        }

        .comment-mian-msg-time {
            font-size: 10px;
        }

        .comment-mian-content {
            padding: 8px 0px 8px 40px;
            font-size: 14px;
        }

        .comment-mian-scomment {
            padding: 0px 38px 0px 38px;
            box-sizing: border-box;
        }

        .comment-mian-scomment-main {
            width: 100%;
            display: flex;
            font-size: 13px;
            padding: 8px 0px 5px 5px;
            border-bottom: 1px solid #e1e1e1;
        }

        .comment-mian-scomment-msg {
            float: left;
        }

        .comment-mian-scomment-msg > a {
            float: left;
            padding-left: 4px;
            color: #4986ea;
        }

        .comment-mian-scomment-msg > div {
            float: left;
            padding-left: 4px;
        }

        .comment-mian-scomment-conntent {
            flex: 1;
            float: left;
            padding-left: 5px;
            line-height: 18px;
        }

        @media only screen and (max-width: 800px) {
            .card-comment {
                padding: 20px 10px 20px 10px;
            }

            .word-msg-height {
                height: 38px;
            }

            .comment-mian-scomment-main {
                display: block;
            }

        }
    </style>
    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/index.js"></script>
</head>

<body class="mdui-drawer-body-left ">
<script>
    function changeFrameHeight() {
        var iframeHeight = $("#wordIframe").contents().find("body").height();
        $("#wordIframe").height(iframeHeight + 50);
        setTimeout("changeFrameHeight()", 2000);
    }
    var dialog;
    $(function () {
        $('#wordIframe').attr("src", $('#htmlSrc').val());
        dialog = new mdui.Dialog('#dialog');

        var dialogTextareaHeight = $("#dialog_textarea").height();
        $("#dialog_textarea").resize(function () {
                var addHeight = $("#dialog_textarea").height() - dialogTextareaHeight
                var dialogJq = $("#dialog");
                dialogJq.css("height", dialogJq.height() + addHeight);
                dialogTextareaHeight = $("#dialog_textarea").height();
            }
        );
        getCemments();
    });
    function getCemments() {
        var id=getUrlParam("id");
        $.ajax({
            url: "getComments.do?id="+id, success: function (result) {
                var comments = result.data;
                var i=1;
                for (var key in comments) {
                    var comment=comments[key];
                    var copyHtml = $('#comment').clone();
                    copyHtml.attr("id", "comment" + i);
                    copyHtml.find(".comment-mian-msg-lz").html(comment.observername);
                    copyHtml.find(".comment-mian-msg-time").html(comment.date);
                    copyHtml.find(".comment-mian-content").html(comment.value);
                    if(comment.viceComment!=null){
                        var viceComments=comment.viceComment;
                        for (var a=0;a<viceComments.length;a++){
                            var viceComment=$("#viceComment").clone();
                            viceComment.attr("id","");
                            viceComment.find(".form").html(viceComments[a].observername);
                            viceComment.find(".to").html(viceComments[a].toobservername);
                            viceComment.find(".comment-mian-scomment-conntent").html(viceComments[a].value);
                            copyHtml.append(viceComment);
                        }
                    }
                    $(".card-comment").append(copyHtml);
                    i++;
                }
            }
        });
    }
    function showDialog(dialogg) {
        dialog.open();
    }

</script>

<input id="htmlSrc" value="${htmlSrc}" type="hidden">

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
                <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor ">
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
                                onload="changeFrameHeight()" frameborder="0" height="100%" width="100%">
                        </iframe>
                    </div>
                    <div class="mdui-divider"></div>
                    <div class="card-pageOther">
                        <div class="card-turnPage-up">上一篇:<span>多线程</span></div>
                        <div class="card-turnPage-down"><span>多线程的理解和使用</span>:下一篇</div>
                    </div>

                    <div class="mdui-divider"></div>
                    <div class="card-comment">
                        <div style="width: 100%;height: 30px;">
                            评论：
                        </div>


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
<div id="toReplace" style="display:none">

    <!--评论-->
    <div class="comment-mian" id="comment">
        <div class="comment-mian-msg">
            <img src="res/img/head.jpg" class="mdui-img-circle">
            <div>
                <div class="comment-mian-msg-lz">Springmarker</div>
                <div class="comment-mian-msg-time">1楼 11月12,2017 18:32:54</div>
            </div>
            <span>
                <button onclick="showDialog(this)" class="mdui-btn mdui-btn-icon" mdui-tooltip="{content: '回复',position: 'top'}">
                    <i style="font-size: 18px;" class="mdui-icon material-icons">textsms</i>
                </button>
            </span>
        </div>
        <div class="comment-mian-content">
            做的还不错。
        </div>

    </div>
    <!--评论-->


    <!--副评论-->
    <div class="comment-mian-scomment" id="viceComment">
        <div class="comment-mian-scomment-main mdui-ripple">
            <div class="comment-mian-scomment-msg">
                <a class="form">张三</a>
                <div>回复:</div>
                <a class="to">李四(层主)</a>
                <div>：</div>
            </div>
            <span class="comment-mian-scomment-conntent">这不对啊。</span>
        </div>
    </div>
    <!--副评论-->
</div>

<!--回复对话框-->
<div style="height: auto;" class="mdui-dialog" id="dialog">
    <div style="width: 100%;padding: 10px;box-sizing:border-box;">
        <div class="mdui-textfield">
                <textarea id="dialog_textarea" style="height: 80px" class="mdui-textfield-input"
                          placeholder="请输入您的回复消息!"></textarea>
        </div>
        <div class="mdui-dialog-actions">
            <button class="mdui-btn mdui-ripple" mdui-dialog-cancel>取消</button>
            <button class="mdui-btn mdui-ripple" mdui-dialog-confirm>发送</button>
        </div>
    </div>
</div>
<!--回复对话框-->

</body>


</html>