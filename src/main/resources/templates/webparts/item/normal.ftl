<#-- @ftlvariable name="word" type="com.springmarker.blog.pojo.Word" -->

<!-- 单个item-->
<div id="word" class="mdui-row mdui-row-margin">
    <div class="mdui-col-xs-12">
        <div class="mdui-card mdui-hoverable secondColorAndBackgroundColor">
            <a class="MY-mdui-card-media" href="${request.contextPath}/word/${word.nickTitle}.html">
                <img class="MY-card-img" src="${word.backgroundImage}"/>
                <div class="mdui-card-media-covered mdui-card-media-covered-transparent">
                    <div class="mdui-card-primary ">
                        <div class="MY-card-tilte">${word.title}</div>
                    </div>
                </div>
            </a>
            <a href="${request.contextPath}/word/${word.nickTitle}.html"
               class="mdui-card-content mdui-ripple secondColorAndBackgroundColor href"
               style="display: block;text-decoration:none;">
            ${word.summary}
            </a>
            <div class="mdui-divider" style="margin-top:1px"></div>
            <div class="mdui-card-actions">
                <img src="${request.contextPath}/static/img/head.jpg">
                <span class="writer-name">${word.writer}</span>
                <i mdui-menu="{target: '#example-attr'}"
                   class="mdui-ripple mdui-icon material-icons MY-card-more">more_vert</i>
                <div class="MY-card-data">${word.date.monthValue}月${word.date.dayOfMonth},${(word.date.year)?c}</div>
            </div>
        </div>
    </div>
</div>
<!-- 单个item-->