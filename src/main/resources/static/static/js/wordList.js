$(function () {
    var pageNumNow = parseInt($("#page").val());
    var type = $("#type").val();

    //监听滚动条到底部
    var appendFlag = true;
    $(window).scroll(function () {
        if (appendFlag && ($(window).scrollTop() + 20 > $(document).height() - $(window).height())) {
            appendFlag = false;
            var getWordNum = appendWord(getAjaxData(getContenxtPath() + "/list-api/" + type + "/?page=" + (pageNumNow + 1)));
            if (getWordNum < 10) {
                appendNoMore();
                return;
            }
            pageNumNow = pageNumNow + 1;
            appendFlag = true;
        }
    });

});

function appendWord(html) {
    $(".word-list").append(html);
    // for (var i = 0; i < list.length; i++) {
    //     var copyHtml = $('#word').clone();
    //     copyHtml.attr("id", "word" + (i + 1));
    //     copyHtml.find(".MY-card-img").attr("src", list[i].backgroundImage);
    //     copyHtml.find('.MY-card-tilte').html(list[i].title);
    //     copyHtml.find('.mdui-card-content').html(list[i].summary);
    //     copyHtml.find('.writer-name').html(list[i].writer);
    //     copyHtml.find('.MY-card-data').html(list[i].date);
    //     var href = copyHtml.find('.href').attr('href') + list[i].id;
    //     copyHtml.find('.href').attr('href', href);
    //     copyHtml.find('.MY-mdui-card-media').attr('href', href);
    //     $(".mainPage").append(copyHtml);
    //     copyHtml.fadeIn(1000);
    // }
    return html.replace(' ', '').length;
}

function appendNoMore() {
    var copyHtml = $('#noMore').clone();
    $(".word-list").append(copyHtml);
}

function getContenxtPath() {
    return $("#contextPath").val();
}