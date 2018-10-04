
<!-- 单个item-->
<div id="word" class="mdui-row mdui-row-margin">
    <div class="mdui-col-xs-12">
        <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor">
            <a class="MY-mdui-card-media">
                <img class="MY-card-img" src="${request.contextPath}/static/img/first.jpg"/>
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
                <img src="${request.contextPath}/static/img/head.jpg">
                <span class="writer-name">Springmarker</span>
                <i mdui-menu="{target: '#example-attr'}"
                   class="mdui-ripple mdui-icon material-icons MY-card-more">more_vert</i>
                <div class="MY-card-data">11月25,2016</div>
            </div>
        </div>
    </div>
</div>
    <!-- 单个item-->