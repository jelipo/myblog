<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>管理</title>
    <link rel="bookmark" type="image/x-icon" href="/res/img/favicon.ico"/>
    <link rel="shortcut icon" href="/res/img/favicon.ico">
    <meta name="viewport" content="width=device-width,maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <link href="res/css/mdui.min.css" rel="stylesheet">
    <link href="res/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="res/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="res/js/mdui.min.js"></script>
    <script type="text/javascript" src="res/js/main.js"></script>
    <style>
        .form-input-half {
            width: 50%;
            float: left;
            height: 70px;
        }

        .form-input-half > input {
            width: 95%;
        }

        .form-selectFile-text {
            height: 23px;
            font-size: 16px;
            float: left;
            line-height: 23px;
        }

        .card-padding {
            padding: 30px 20px 30px 20px;
        }
    </style>
</head>

<body class="mdui-drawer-body-left ">
<%@include file="../webparts/drawer.jsp" %>


<!--主要内容-->
<div class="mdui-container">
    <div style="height: 150px"></div>
    <div class="mainPage">

        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor card-padding">
                    <form action="/uploadBlog.do" method="post" onsubmit="return check()" enctype="multipart/form-data">
                        <h3 style="width: 100%">上传文章：</h3>
                        <div class="mdui-textfield form-input-half">
                            <input id="title" name="title" class="mdui-textfield-input" type="text"
                                   placeholder="文章标题"/>
                        </div>
                        <div class="mdui-textfield form-input-half">
                            <input id="writer" name="writer" class="mdui-textfield-input" type="text"
                                   placeholder="作者"/>
                        </div>
                        <div class="mdui-textfield" style="float: left;width:100%;height: 70px;">
                            <input id="summary" name="summary" class="mdui-textfield-input" type="text"
                                   placeholder="简介"/>
                        </div>
                        <div style="width: 100%;height: 60px;float: left">
                            <div class="form-selectFile-text">选择Blog文件：</div>
                            <input id="blogFile" type="file" name="blogFile" accept="text/html">
                        </div>
                        <div style="width: 100%;height: 50px;float: left">
                            <div class="form-selectFile-text">选择背景图片：</div>
                            <input id="backgroundImage" type="file" name="backgroundImage" accept="image/*">
                        </div>
                        <div style="width:100%;height: 60px;float: left">
                            <label class="mdui-checkbox">
                                <input id="comment_switch" name="allowComment" type="checkbox"/>
                                <i class="mdui-checkbox-icon"></i>允许评论
                            </label>
                        </div>
                        <input type="submit" value="提交" class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-red">
                    </form>
                </div>
            </div>
        </div>

        <div class="mdui-row mdui-row-margin">
            <div class="mdui-col-xs-12 ">
                <div class="mdui-card mdui-shadow-20 secondColorAndBackgroundColor card-padding">
                    <form action="/uploadFile.do" method="post" onsubmit="return uploadToCDN()"
                          enctype="multipart/form-data">
                        <h2>上传文件到七牛：</h2>
                        <div class="mdui-textfield" style="float: left;width:100%;height: 70px;">
                            <input id="filename" name="filename" class="mdui-textfield-input"
                                   type="text" placeholder="输入保存后的文件名（为空时使用随机名称）"/>
                        </div>
                        <div style="width: 100%;height: 50px;float: left">
                            <div class="form-selectFile-text">选择文件：</div>
                            <input id="cdnFile" type="file" name="cdnFile">
                        </div>
                        <input type="submit" value="提交" class="mdui-btn mdui-btn-raised mdui-ripple mdui-color-red">
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

<!--部件-->
<div style="display: none">
</div>
<!--部件-->

<!-- 对话框-->
<div id="linkDialog" class="mdui-dialog">
    <div class="mdui-dialog-title">上传成功，这是外链</div>
    <div class="mdui-dialog-content" id="link"></div>
    <div class="mdui-dialog-actions">
        <button class="mdui-btn mdui-ripple">关闭</button>
    </div>
</div>
<!-- 对话框-->
<script>
    function check() {
        var title = $("#title").val();
        var writer = $("#writer").val();
        var summary = $("#summary").val();
        var blogFile = $("#blogFile").val();
        var imageFile = $("#backgroundImage").val();
        var isNull = (title == "") || (writer == "") || (summary == "") || (file == "") || (imageFile == "");
        if (isNull) {
            mdui.snackbar({message: '值为空！'});
            return false;
        } else {
            return true;
        }
    }

    //初始化对话框
    var linkDialog = new mdui.Dialog('#linkDialog');

    //文件上传
    function uploadToCDN() {
        var file = $("#cdnFile").val();
        if (file == '') {
            mdui.snackbar({message: '未选择文件'});
            return false;
        }
        var formData = new FormData();
        var filename = $("#filename").val();
        formData.append("cdnFile", $("#cdnFile")[0].files[0]);
        formData.append("filename", filename);
        mdui.snackbar({message: '正在上传。'});
        var data = ajaxUploadFile("/uploadFile.do", formData);
        var link = data.link;
        $("#link").html(link)
        linkDialog.open();
        return false;
    }
</script>

<%@include file="../webparts/footer.html" %>
</body>
</html>
