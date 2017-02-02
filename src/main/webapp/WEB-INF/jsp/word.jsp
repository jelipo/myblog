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
    <link href="res/css/word.css" rel="stylesheet">

    <script type="text/javascript" src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://res.springmarker.com/other/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/index.js"></script>
    <script type="text/javascript" src="res/js/word.js"></script>
</head>

<body class="mdui-drawer-body-left ">
<script>

</script>
<input id="wordId" value="${wordId}" type="hidden">
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
                        <div style="width: 100%;height: 100px;">
                            <div style="font-size: 25px;font-weight: 900;color:#616161">评论：</div>
                            <div class="mdui-textfield mdui-textfield-floating-label " onclick="newMainCommentShowDialog()">
                                <label class="mdui-textfield-label">留下你的见解吧！</label>
                                <input class="mdui-textfield-input newComment"  disabled/>
                            </div>
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
                <button onclick="mainCommentShowDialog(this)" class="mdui-btn mdui-btn-icon replyButton"
                        mdui-tooltip="{content: '回复',position: 'top'}">
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
        <div class="comment-mian-scomment-main mdui-ripple" onclick="viceCommentShowDialog(this)">
            <div class="comment-mian-scomment-msg">
                <a class="from">张三</a>
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
<div class="mdui-dialog" id="dialog">
    <form  onsubmit="return chaeckDialogForm(this)">
        <input id="observername" type="hidden" name="observername" value="" >
        <input id="mainCommentId" type="hidden" name="mainCommentId" value="" >
        <input id="viceCommentId" type="hidden" name="viceCommentId" value="" >
        <input id="isNewMainComment" type="hidden" name="isNewMainComment" value="" >
        <div class="dialog-main">
            <div class="dialog-tilte">您要向<span class="observername"></span>回复消息
                <span>（由于是游客制，所以会有所限制发言频率）</span>
            </div>
            <div>
                <div class="mdui-textfield dialog-msgInput">
                    <input id="nickname"  class="mdui-textfield-input" type="text" name="nickname" placeholder="请输入昵称（可选）"/>
                    <div>不填写时随机分配</div>
                </div>
                <div class="mdui-textfield dialog-msgInput">
                    <input id="email" type="email" class="mdui-textfield-input"  placeholder="请输入邮箱(可选)"/>
                    <div>收到回复时，会向邮箱发送邮件</div>
                </div>
            </div>
            <div class="mdui-textfield" style="width: 100%;padding-top: 10px;">
                <textarea id="value" name="value" id="dialog_textarea" style="max-height: 80px" class="mdui-textfield-input"
                          placeholder="请输入您的回复消息!"></textarea>
            </div>
            <div class="mdui-dialog-actions">
                <input type="button" value="取消" onclick="closeDialog()" class="mdui-btn mdui-ripple" >
                <input type="submit" value="确认"  class="mdui-btn mdui-ripple" >
            </div>
        </div>
    </form>
</div>
<!--回复对话框-->

</body>


</html>