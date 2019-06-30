<#-- @ftlvariable name="word" type="com.springmarker.blog.pojo.Word" -->
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>${word.title} - Springmarker</title>
    <link rel="bookmark" type="image/x-icon" href="${request.contextPath}/static/img/favicon.ico?v=${randomStr}"/>
    <link rel="shortcut icon" href="${request.contextPath}/static/img/favicon.ico?v=${randomStr}">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="${request.contextPath}/static/css/mdui.min.css?v=${randomStr}" rel="stylesheet">
    <link href="${request.contextPath}/static/css/main.css?v=${randomStr}" rel="stylesheet">
    <link href="${request.contextPath}/static/css/word.css?v=${randomStr}" rel="stylesheet">
    <link href="${request.contextPath}/static/css/marxico.css?v=${randomStr}" rel="stylesheet">
</head>

<body class="mdui-drawer-body-left ">


<!--参数-->
<input id="contextPath" value="${request.contextPath}" type="hidden">
<input id="nickTitle" value="${word.nickTitle}" type="hidden">
<input id="wordId" value="${word.id}" type="hidden">
<#--<input id="wordId" value="${wordId}" type="hidden">-->
<#--<input id="title" value="${title}" type="hidden">-->
<#--<input id="date" value="${date}" type="hidden">-->
<#--<input id="lastwordId" value="${lastWordId}" type="hidden">-->
<#--<input id="lastwordTitle" value="${lastWordTitle}" type="hidden">-->
<#--<input id="nextwordId" value="${nextWordId}" type="hidden">-->
<#--<input id="nextwordTitle" value="${nextWordTitle}" type="hidden">-->
<#--<input id="backgroundImage" value="${backgroundImage}" type="hidden">-->
    <!--参数-->


<#include "webparts/drawer.ftl" />
    <!--主要内容-->
<div class="mdui-container" style="min-height: 1200px">
    <div style="height: 150px">

    </div>
    <div class="mainPage" style="display: none;">

        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-14 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor ">
                    <a class="MY-mdui-card-media">
                        <img id="wordBackgroundImage" class="MY-card-img" src="${word.backgroundImage}"/>
                        <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                            <div class="mdui-card-primary ">
                                <div id="wordTitle" class="MY-card-tilte">${word.title}</div>
                            </div>
                        </div>
                    </a>
                    <div class="mdui-card-actions word-msg-height">
                        <img src="${request.contextPath}/static/img/head.jpg?v=${randomStr}">
                        <span>${word.writer}</span>
                        <div id="wordDate"
                             class="MY-card-data">${(word.date.year)?c}年${word.date.monthValue}月${word.date.dayOfMonth}日</div>
                    </div>
                    <div class="mdui-divider"></div>
                    <div class="card-page" style="min-height: 600px;">
                        <div class="maxaingBody">${word.html}</div>
                    </div>
                    <div class="mdui-divider"></div>
                    <div class="card-pageOther" style="height: 40px">
                    </div>
                </div>

            </div>
        </div>

        <!--翻页-->
        <div class="card-pageOther" style="height: 40px">
            <#if (lastWord??)>
                <a id="lastPage" href="${request.contextPath}/word/${lastWord.nickTitle}.html"
            mdui-tooltip="{content: '${lastWord.title}'}" class="mdui-btn mdui-btn-icon mdui-ripple">
                <i class="mdui-icon material-icons">arrow_back</i>
                </a>
            </#if>
            <#if (nextWord??)>
                <a id="nextPage" href="${request.contextPath}/word/${nextWord.nickTitle}.html"
            mdui-tooltip="{content: '${nextWord.title}'}"
                style="float:right;"
                class="mdui-btn mdui-btn-icon mdui-ripple">
                <i class="mdui-icon material-icons">arrow_forward</i>
                </a>
            </#if>
        </div>
        <!--翻页-->
        <!--评论-->
        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor ">
                    <div class="card-comment">
                        <div style="width: 100%;height: 100px;margin-bottom: 30px;">
                            <div style="font-size: 25px;font-weight: 900;color:#616161">评论：</div>
                            <div class="mdui-textfield mdui-textfield-floating-label "
                                 onclick="newMainCommentShowDialog()">
                                <label class="mdui-textfield-label">留下你的见解吧！</label>
                                <input class="mdui-textfield-input newComment" disabled/>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--评论-->

    </div>
</div>

<#include "webparts/footer.ftl" />
<div id="toReplace" style="display:none">

    <!--评论-->
    <div class="comment-mian" id="comment">
        <div class="comment-mian-msg">
            <img src="${request.contextPath}/static/img/head.jpg" class="mdui-img-circle">
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
            <span class="comment-mian-scomment-conntent">内容</span>
        </div>
    </div>
    <!--副评论-->
</div>

<!--回复对话框-->
<div class="mdui-dialog" id="dialog">
    <form onsubmit="return chaeckDialogForm(this)">
        <input id="observerName" type="hidden" name="observerName" value="">
        <input id="mainCommentId" type="hidden" name="mainCommentId" value="">
        <input id="viceCommentId" type="hidden" name="viceCommentId" value="">
        <input id="isNewMainComment" type="hidden" name="isNewMainComment" value="">
        <div class="dialog-main">
            <div class="dialog-tilte">您要向<span class="observername"></span>回复消息
                <span>（由于是游客制，所以会有所限制发言频率）</span>
            </div>
            <div>
                <div class="mdui-textfield dialog-msgInput">
                    <input id="nickname" class="mdui-textfield-input" type="text" name="nickname"
                           placeholder="请输入昵称（可选）"/>
                    <div>不填写时随机分配</div>
                </div>
                <div class="mdui-textfield dialog-msgInput">
                    <input id="email" type="email" class="mdui-textfield-input" placeholder="请输入邮箱(可选)"/>
                    <div>收到回复时，会向邮箱发送邮件</div>
                </div>
            </div>
            <div class="mdui-textfield" style="width: 100%;padding-top: 10px;">
                <textarea id="value" name="value" id="dialog_textarea" style="max-height: 80px"
                          class="mdui-textfield-input"
                          placeholder="请输入您的回复消息!"></textarea>
            </div>
            <div class="g-recaptcha" data-sitekey="6LdoOBQUAAAAAP5Q-qMbFDphzLteAvdMGmHZXzxQ"></div>
            <div class="mdui-dialog-actions">
                <input type="button" value="取消" onclick="closeDialog()" class="mdui-btn mdui-ripple">
                <input type="submit" value="确认" class="mdui-btn mdui-ripple">
            </div>
        </div>
    </form>
</div>
<!--回复对话框-->
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/mdui.min.js?v=${randomStr}"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/main.js?v=${randomStr}"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/word.js?v=${randomStr}"></script>
</html>