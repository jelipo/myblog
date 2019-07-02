<!--侧边栏-->

<i onclick="toggle()" class="mdui-icon material-icons"
   style="position:fixed;left: 5px;top: 10px;z-index: 1024;">menu</i>
<div id="drawer" class="mdui-drawer leftbar secondColorAndBackgroundColor">
    <div class="leftbar-head"
         style="background-image: url('${request.contextPath}/static/img/first.jpg?v=${randomStr}');
                 background-position:0 -48px;">
        <div style="height:85px;">
            <img src="${request.contextPath}/static/img/head.jpg?v=${randomStr}"
                 class="mdui-img-circle leftbar-head-img" style="float: left;">
            <div class="leftbar-head-icon">
                <a href="https://github.com/springmarker" target="_blank" mdui-tooltip="{content: 'GitHub'}">
                    <span class="iconfont" style="font-size: 27px">&#xe718;</span>
                </a>
                <a href="mailto:admin@springmarker.com" mdui-tooltip="{content: '邮件'}">
                    <span class="iconfont" style="font-size: 26px">&#xe61b;</span>
                </a>
            </div>
        </div>
        <div style="padding-left: 10px;color: #fff;clear: both;font-size: 17px;">
            我的小站 - Springmarker
            <div></div>
        </div>
        <div class="leftbar-head-tag" style="margin-left: 10px;">站长</div>
        <div class="leftbar-head-tag">全干</div>
        <div class="leftbar-head-tag">Java</div>
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
    <a href="${request.contextPath}/messages" class="leftbar-other mdui-ripple">
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

    <a href="https://www.upyun.com/?utm_source=lianmeng&utm_medium=referral" target="_blank" class="draw-cdn-img">
        <img alt="由又拍云提供加速度服务" src="${request.contextPath}/static/img/upyun_logo5.png?v=${randomStr}">
    </a>
</div>
