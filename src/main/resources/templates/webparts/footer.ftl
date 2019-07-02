<!-- 页脚-->
<footer class="MY-footer">
    <div class="mdui-center  mdui-container footer-main">
        <div class="mdui-col-xs-4 footer-other">
            <div>导 航 ：</div>
            <a class="foot-href" href="/">网站主页</a>
            <a href="https://github.com/springmarker/MyBlog" target="_blank">此站GitHub</a>
        </div>
        <div class="mdui-col-xs-4 footer-other">
            <div>联 系 ：</div>
            <a href="mailto:admin@springmarker.com">发送邮件</a>
            <a>向我留言</a>
        </div>
        <div class="mdui-col-xs-4 footer-other">
            <a href="https://github.com/springmarker" target="_blank">
                <img width="80px" src="${request.contextPath}/static/img/github.png?v=${randomStr}">
            </a>
        </div>
    </div>
    <div class="mdui-center mdui-text-center footer-bottom">
        <a>©2019 Springmarker. All rights reserved.</a>
        <a href="http://www.miitbeian.gov.cn" target="_blank">备案号：鲁ICP备16035555号-2</a>
    </div>
</footer>
<!-- 页脚-->
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
    ga('create', 'UA-92012528-1', 'auto');
    ga('send', 'pageview');
</script>