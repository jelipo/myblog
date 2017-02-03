function changeFrameHeight() {
    var iframeHeight = $("#wordIframe").contents().find("body").height();
    $("#wordIframe").height(iframeHeight + 50);
    setTimeout("changeFrameHeight()", 2000);
}
var dialog;
$(function () {
    $('#wordIframe').attr("src", $('#htmlSrc').val());
    dialog = new mdui.Dialog('#dialog');


    getCemments();
});
function getCemments() {
    var id = getUrlParam("id");
    $.ajax({
        url: "getComments.do?id=" + id, success: function (result) {
            var comments = result.data;
            var i = 1;
            for (var key in comments) {
                var comment = comments[key];
                var copyHtml = $('#comment').clone();
                copyHtml.attr("id", "comment" + i);
                copyHtml.find(".comment-mian-msg-lz").html(comment.observername);
                copyHtml.find(".comment-mian-msg-time").html(i+"楼 "+comment.date);
                copyHtml.find(".comment-mian-content").html(comment.value);
                copyHtml.find(".replyButton").attr("commentid",comment.id);
                if (comment.viceComment != null) {
                    var viceComments = comment.viceComment;
                    for (var a = 0; a < viceComments.length; a++) {
                        var viceComment = $("#viceComment").clone();
                        viceComment.attr("id", "");
                        viceComment.find(".from").html(viceComments[a].observername);
                        viceComment.find(".to").html(viceComments[a].toobservername);
                        viceComment.find(".comment-mian-scomment-conntent").html(viceComments[a].value);
                        viceComment.find(".comment-mian-scomment-main").attr("commentid",viceComments[a].id);
                        copyHtml.append(viceComment);
                    }
                }
                $(".card-comment").append(copyHtml);
                i++;
            }
        }
    });
}

function mainCommentShowDialog(openButton) {
    var button=$(openButton);
    var name=button.parent().parent().find(".comment-mian-msg-lz").html();
    var mainCommentId=button.attr("commentid");
    realShowDialog(mainCommentId,"",name,"2");
}
function viceCommentShowDialog(openButton) {
    var button=$(openButton);
    var name=button.parent().parent().find(".from").html();
    var mainCommentId=button.parent().parent().find(".replyButton").attr("commentid");
    var viceCommentId=button.attr("commentid");
    realShowDialog(mainCommentId,viceCommentId,name,"2");

}
function newMainCommentShowDialog() {
    realShowDialog("","","作者","1");
}
function realShowDialog(mainCommentId,viceCommentId,name,isNewMainComment) {
    $("#dialog").find(".observername").html(name);
    $("#observername").val(name);
    $("#mainCommentId").val(mainCommentId);
    $("#viceCommentId").val(viceCommentId);
    $("#isNewMainComment").val(isNewMainComment);
    dialog.open();
}
function closeDialog() {
    dialog.close();

}
function chaeckDialogForm(form) {

    var wordId=$("#wordId").val();
    var toObservername=$("#observername").val();
    var mainCommentId=$("#mainCommentId").val();
    var viceCommentId=$("#viceCommentId").val();
    var nickname=$("#nickname").val();
    var email=$("#email").val();
    var value=$("#value").val();
    var isNewMainComment=$("#isNewMainComment").val();
    if (value==null||value==""){
        alert("内容不可为空");
        return false;
    }
    $.post("postComment.do",{
        wordId:wordId, //文章的id
        toObservername:toObservername, // 向谁回复 （创建新主评论时为空）
        mainCommentId:mainCommentId,// 主评论的id（创建新主评论时为空）
        viceCommentId:viceCommentId,// 副评论的id（当回复给主评论时为空）
        nickname:nickname,// 昵称/回复者的名字（可为空）
        email:email, // 邮箱（可为空）
        value:value,  // 评论主要内容
        isNewMainComment:isNewMainComment //是否是新主评论
    },function(result){
        dialog.close();
        if ( result.resultCode!=200){
            alert(result.wrong);
            return ;
        }
        var data = result.data;
    });
    return false;
}

