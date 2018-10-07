<!--侧边栏-->
<i onclick="toggle()" class="mdui-icon material-icons"
   style="position:fixed;left: 5px;top: 10px;z-index: 1024;">menu</i>
<div id="drawer" class="mdui-drawer leftbar secondColorAndBackgroundColor">
    <div class="leftbar-head" style="background-image: url('${request.contextPath}/static/img/first.jpg')">
        <img src="${request.contextPath}/static/img/head.jpg?v=${randomStr}" class="mdui-img-circle">
        <div class="mdui-grid-tile-actions">
            <div class="mdui-grid-tile-text">
                <div class="mdui-grid-tile-title">Springmarker</div>
            </div>
        </div>
    </div>
    <div class="leftbar-navi mdui-ripple" onclick="window.location.href='${request.contextPath}/'">
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
                <a href="${request.contextPath}/list/tech" class="leftbar-item mdui-ripple">技术</a>
                <a href="${request.contextPath}/list/other" class="leftbar-item mdui-ripple">随写</a>
            </div>
        </div>
    </div>
    <div style="margin: 5px 0 5px 0;" class="mdui-divider"></div>
    <a class="leftbar-other mdui-ripple">
        <span>关于我</span>
    </a>
    <a href="${request.contextPath}/list" class="leftbar-other mdui-ripple">
        <span>所有文章</span>
        <p></p>
    </a>
    <a href="${request.contextPath}/message.do" class="leftbar-other mdui-ripple">
        <span>留言</span>
        <p></p>
    </a>


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
                <div class="leftbar-item mdui-ripple">暂无</div>
                <div class="leftbar-item mdui-ripple">暂无</div>
            </div>
        </div>
    </div>
</div>
