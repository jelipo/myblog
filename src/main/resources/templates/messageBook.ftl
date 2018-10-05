<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>留言 - Springmarker</title>
    <link rel="bookmark"  type="image/x-icon"  href="${request.contextPath}/static/img/favicon.ico"/>
    <link rel="shortcut icon" href="${request.contextPath}/static/img/favicon.ico">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="${request.contextPath}/static/css/my-mdui.min.css" rel="stylesheet">
    <link href="${request.contextPath}/static/css/main.css" rel="stylesheet">

    <style>
        .seconfTitle {
            font-family: Microsoft Yahei;
            font-weight: 500;
            color: #3f51b5;
        }

        .message {
            padding: 10px 0px 0px 0px;
            width: 100%;
            min-height: 50px;
            float: left;
            border-bottom: solid 1px #dbdbdb;
        }

        .message_name {
            float: left;
            color: #009688;
        }

        .message_time {
            float: left;
            padding-left: 7px;
            font-size: 12px;
            line-height: 21px;
        }

        .message_content {
            padding: 15px 10px 20px 20px;
            float: left;
            width: 100%;
            font-size: 16px;
        }
        .messageBox_buttom{
            float: right;color:#ff4081;font-size: 15px;width: 50px;margin-right: 50px;height: 35px;
        }
        .halfinput-width{
            width: 49%;
        }
        @media only screen and (max-width: 800px) {
            .halfinput-width{
                width: 100%;
            }
        }
    </style>
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
                    <div id="main-message" style="padding: 30px 20px 30px 20px;float: left;width: 100%;box-sizing:border-box;">
                        <h2 class="seconfTitle">留言：</h2>

                    </div>
                </div>
            </div>
        </div>

        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor ">
                    <div style="padding: 30px 20px 30px 20px;float: left;width: 100%;box-sizing: border-box;">
                        <h2 class="seconfTitle">留言框：</h2>
                        <form action="" method="post" onsubmit="return check(this)">
                            <div style="float: left;width: 100%">
                                <div style="float: left;" class="mdui-textfield halfinput-width">
                                    <input id="nickname" name="nickname" class="mdui-textfield-input" type="text"
                                           placeholder="请输入昵称（可选）"/>
                                </div>
                                <div style="float: right;" class="mdui-textfield halfinput-width">
                                    <input id="contactway" name="contactway" class="mdui-textfield-input" type="text"
                                           placeholder="联系方式（如果需要我联系您可填写）"/>
                                </div>
                            </div>
                            <div style="float: left;width: 100%" class="mdui-textfield">
                                <input id="message" name="message" class="mdui-textfield-input" type="text"
                                       placeholder="请输入您的留言"/>
                            </div>
                            <input type="submit" class="mdui-btn mdui-ripple messageBox_buttom" value="提交">
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<!--部件-->
<div style="display: none">
    <!--单个留言-->
    <div id="singleMessage" class="message" >
        <div style="float: left;width: 100%">
            <div class="message_name">Springmarker</div>
            <div class="message_time">10月26,2016 20:15</div>
            <div style="float: left;padding-left: 7px;">留言道：</div>
        </div>
        <div class="message_content">
            这是真的吗
        </div>
    </div>
    <!--单个留言-->

</div>
<!--部件-->

<%@include file="webparts/footer.html" %>

</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/mdui.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/main.js"></script>
<script>
    $(function () {
        var list=getAjaxData("/getMessages.do");
        for (var i = 0; i < list.length; i++) {
            var copyHtml = $('#singleMessage').clone();
            copyHtml.find(".message_name").html(list[i].messageName);
            copyHtml.find(".message_time").html(list[i].messageTime);
            copyHtml.find(".message_content").html(list[i].messageContent);
            $("#main-message").append(copyHtml);
            //copyHtml.fadeIn(1000);
        }
    });
    function check(form) {
        var nickname=$("#nickname").val();
        var contactway=$("#contactway").val();
        var message=$("#message").val();
        if (message==""){
            mdui.snackbar({message: '内容不能为空！'});
        }
        $.post("postMessage.do",{
            nickname:nickname,
            contactway:contactway,
            message:message
        },function(result){
            if ( result.resultCode!=200){
                alert(result.wrong);
                return false;
            }else {
                mdui.snackbar({message: '留言成功，可能有缓存，稍后刷新查看。'});
            }
        });
        return false;
    }
</script>

</html>