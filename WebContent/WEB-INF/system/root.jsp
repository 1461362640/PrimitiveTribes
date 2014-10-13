<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<title>原始部落后台</title>

 ${extends("../source/common/easyui_js_css") }
 
  <!--导入首页启动时需要的相应资源文件(首页相应功能的 js 库、css样式以及渲染首页界面的 js 文件)-->
    <script src="./source/easyui_1.36/common/index.js" type="text/javascript"></script>
    <link href="./source/easyui_1.36/common/index.css" rel="stylesheet" />
    <script src="./source/easyui_1.36/common/index-startup.js"></script>
    
</head>

<body>
	 <div id="maskContainer">
        <div class="datagrid-mask" style="display: block;"></div>
        <div class="datagrid-mask-msg" style="display: block; left: 50%; margin-left: -52.5px;">
          	  正在加载...
        </div>
    </div>
    <div id="mainLayout" class="easyui-layout hidden" data-options="fit: true">
        <div id="northPanel" data-options="region: 'north', border: false" style="height: 80px; overflow: hidden;">
            <div id="topbar" class="top-bar">
                <div class="top-bar-left">
                    <h1 style="margin-left: 10px; margin-top: 10px;">原始部落后台管理</h1>
                </div>
                <div class="top-bar-right">
                    <div id="timerSpan"></div>
                    <div id="themeSpan">
                        <span>更换皮肤风格：</span>
                        <select id="themeSelector"></select>
                        <a id="btnHideNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-up'"></a>
                    </div>
                </div>
            </div>
            <div id="toolbar" class="panel-header panel-header-noborder top-toolbar">
                <div id="infobar">
                    <span class="icon-hamburg-user" style="padding-left: 25px; background-position: left center;">
                      	 <!--  此处可以放置登录用户账户信息 -->
                      	 ${user },欢迎您！
                    </span>
                </div>
                <div id="searchbar">
                    <input id="topSearchbox" name="topSearchbox" class="easyui-searchbox" data-options="width: 350, height: 26, prompt: '请输入您要查找的内容关键词', menu: '#topSearchboxMenu'" />
                    <div id="topSearchboxMenu" style="width: 85px;">
                        <div data-options="name:'0', iconCls: 'icon-hamburg-zoom'">查询类型</div>
                       <!--  <div data-options="name:'1'">测试类型1</div>
                        <div data-options="name:'2'">测试类型2</div>
                        <div data-options="name:'3'">测试类型3</div>
                        <div data-options="name:'4'">测试类型4</div> -->
                    </div>
                </div>
                <div id="buttonbar">
                    <a id="btnFullScreen" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-arrow-inout'">全屏切换</a>
                    <a id="btnExit" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-sign-out'">退出系统</a>
                    <a id="btnShowNorth" class="easyui-linkbutton" data-options="plain: true, iconCls: 'layout-button-down'" style="display: none;"></a>
                </div>
            </div>
        </div>

        <div data-options="region: 'west', title: '菜单导航栏', iconCls: 'icon-standard-map', split: true, minWidth: 250, maxWidth: 500" style="width: 250px; padding: 1px;">
            <div id="navTabs_tools" class="tabs-tool">
                <table>
                    <tr>
                        <td><a id="navMenu_refresh" class="easyui-linkbutton easyui-tooltip" title="刷新该选项卡及其导航菜单" data-options="plain: true, iconCls: 'icon-hamburg-refresh'"></a></td>
                    </tr>
                </table>
            </div>
            <div id="navTabs" class="easyui-tabs" data-options="fit: true, border: true, tools: '#navTabs_tools'">
                <div data-options="title: '导航菜单', iconCls: 'icon-standard-application-view-tile', refreshable: false, selected: true">
                    <div id="westLayout" class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'center', border: false" style="border-bottom-width: 1px;">
                            <div id="westCenterLayout" class="easyui-layout" data-options="fit: true">
                                <div data-options="region: 'north', split: false, border: false" style="height: 33px;">
                                    <div class="easyui-toolbar">
                                        <a id="navMenu_expand" class="easyui-splitbutton" data-options="iconCls: 'icon-metro-expand2', menu: '#navMenu_toggleMenu'">展开</a>
                                        <a id="navMenu_Favo" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-feed-add'">收藏</a>
                                        <a id="navMenu_Rename" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-pencil'">重命名</a>
                                        <div id="navMenu_toggleMenu" class="easyui-menu">
                                            <div id="navMenu_collapse" data-options="iconCls: 'icon-metro-contract2'">折叠当前</div>
                                            <div class="menu-sep"></div>
                                            <div id="navMenu_collapseCurrentAll" data-options="iconCls: 'icon-metro-expand'">展开当前所有</div>
                                            <div id="navMenu_expandCurrentAll" data-options="iconCls: 'icon-metro-contract'">折叠当前所有</div>
                                            <div class="menu-sep"></div>
                                            <div id="navMenu_collapseAll" data-options="iconCls: 'icon-standard-arrow-out'">展开所有</div>
                                            <div id="navMenu_expandAll" data-options="iconCls: 'icon-standard-arrow-in'">折叠所有</div>
                                        </div>
                                    </div>
                                </div>
                                <div data-options="region: 'center', border: false">
                                    <ul id="navMenu_Tree" style="padding-top: 2px; padding-bottom: 2px;"></ul>
                                </div>
                            </div>
                        </div>
                        <div id="westSouthPanel" data-options="region: 'south', border: false, split: true, minHeight: 32, maxHeight: 275" style="height: 275px; border-top-width: 1px;">
                            <ul id="navMenu_list"></ul>
                        </div>
                    </div>
                </div>
                <div data-options="title: '个人收藏', iconCls: 'icon-hamburg-star', refreshable: false">
                    <div id="westFavoLayout" class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'north', split: false, border: false" style="height: 33px;">
                            <div class="easyui-toolbar">
                                <a id="favoMenu_expand" class="easyui-splitbutton" data-options="iconCls: 'icon-metro-expand2', menu: '#favoMenu_toggleMenu'">展开</a>
                                <a id="favoMenu_Favo" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-standard-feed-delete'">取消收藏</a>
                                <a id="favoMenu_Rename" class="easyui-linkbutton" data-options="plain: true, iconCls: 'icon-hamburg-pencil'">重命名</a>
                                <div id="favoMenu_toggleMenu" class="easyui-menu">
                                    <div id="favoMenu_collapse" data-options="iconCls: 'icon-metro-contract2'">折叠当前</div>
                                    <div class="menu-sep"></div>
                                    <div id="favoMenu_collapseCurrentAll" data-options="iconCls: 'icon-metro-expand'">展开当前所有</div>
                                    <div id="favoMenu_expandCurrentAll" data-options="iconCls: 'icon-metro-contract'">折叠当前所有</div>
                                    <div class="menu-sep"></div>
                                    <div id="favoMenu_collapseAll" data-options="iconCls: 'icon-standard-arrow-out'">展开所有</div>
                                    <div id="favoMenu_expandAll" data-options="iconCls: 'icon-standard-arrow-in'">折叠所有</div>
                                </div>
                            </div>
                        </div>
                        <div data-options="region: 'center', border: false">
                            <ul id="favoMenu_Tree" style="padding-top: 2px; padding-bottom: 2px;"></ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div data-options="region: 'center'" style="padding: 1px;">
            <div id="mainTabs_tools" class="tabs-tool">
                <table>
                    <tr>
                        <td><a id="mainTabs_jumpHome" class="easyui-linkbutton easyui-tooltip" title="跳转至主页选项卡" data-options="plain: true, iconCls: 'icon-hamburg-home'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_toggleAll" class="easyui-linkbutton easyui-tooltip" title="展开/折叠面板使选项卡最大化" data-options="plain: true, iconCls: 'icon-standard-arrow-inout'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_jumpTab" class="easyui-linkbutton easyui-tooltip" title="在新页面中打开该选项卡" data-options="plain: true, iconCls: 'icon-standard-shape-move-forwards'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeTab" class="easyui-linkbutton easyui-tooltip" title="关闭当前选中的选项卡" data-options="plain: true, iconCls: 'icon-standard-application-form-delete'"></a></td>
                        <td><a id="mainTabs_closeOther" class="easyui-linkbutton easyui-tooltip" title="关闭除当前选中外的其他所有选项卡" data-options="plain: true, iconCls: 'icon-standard-cancel'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeLeft" class="easyui-linkbutton easyui-tooltip" title="关闭左侧所有选项卡" data-options="plain: true, iconCls: 'icon-standard-tab-close-left'"></a></td>
                        <td><a id="mainTabs_closeRight" class="easyui-linkbutton easyui-tooltip" title="关闭右侧所有选项卡" data-options="plain: true, iconCls: 'icon-standard-tab-close-right'"></a></td>
                        <td><div class="datagrid-btn-separator"></div></td>
                        <td><a id="mainTabs_closeAll" class="easyui-linkbutton easyui-tooltip" title="关闭所有选项卡" data-options="plain: true, iconCls: 'icon-standard-cross'"></a></td>
                    </tr>
                </table>
            </div>
            <div id="mainTabs" class="easyui-tabs" data-options="fit: true, border: true, showOption: true, enableNewTabMenu: true, tools: '#mainTabs_tools', enableJumpTabMenu: true, onSelect: function (title, index) { window.mainpage.mainTabs.updateHash(index); }">
                <div id="homePanel" data-options="title: '主页', iconCls: 'icon-hamburg-home'">
                    <div class="easyui-layout" data-options="fit: true">
                        <div data-options="region: 'north', split: false, border: false" style="height: 33px;">
                            <div class="easyui-toolbar">
                            <!-- 功能菜单栏 -->
                             </div>
                        </div>
                        <div data-options="region: 'center', border: false" style="overflow: hidden;">
                            <div id="portal" class="easyui-portal" data-options="fit: true, border: false">
                                <div style="width: 33%;">
                                    <div data-options="title: '项目信息', height: 260, collapsible: true, closable: true">
                                      	 原始部落整合发布
                                    </div>
                                    <div data-options="title: '功能简介', height: 370, collapsible: true, closable: true">
                                         	原始部落整合发布
                                    </div>
                                </div>
                                <div style="width: 34%;">
                                    <div data-options="title: 'LICENSE', height: 310,  collapsible: true, closable: true"></div>
                                    <div data-options="title: '更新日志', height: 320,  collapsible: true, closable: true"></div>
                                </div>
                                <div style="width: 33%;">
                                    <div data-options="title: '关于原始部落整合平台', height: 300, collapsible: true, closable: true" style="padding: 10px;">
                                        	原始部落整合平台
                                    </div>
                                    <div id="donatePanel" data-options="title: '原始部落', height: 330, closable: true, collapsible: true, closable: true, tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { window.donate.reload(); } }]" style="padding: 10px;">
                                    		   平台版本号
                                        <ul id="donateList" class="portlet-list">操作系统：</ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div data-options="region: 'east', title: '日历', iconCls: 'icon-standard-date', split: false, minWidth: 200, maxWidth: 500" style="width: 220px; padding: 1px; border-left-width: 0px;">
            <div id="eastLayout" class="easyui-layout" data-options="fit: true">
                <div data-options="region: 'north', split: false, border: false" style="height: 220px;">
                    <div class="easyui-calendar" data-options="fit: true"></div>
                </div>
                <div id="linkPanel" data-options="region: 'center', title: '系统日志', iconCls: 'icon-hamburg-link', tools: [{ iconCls: 'icon-hamburg-refresh', handler: function () { window.link.reload(); } }]">
                    <ul id="linkList" class="portlet-list link-list"></ul>
                </div>
            </div>
        </div>

        <div data-options="region: 'south', title: '关于...', iconCls: 'icon-standard-information', collapsed: true, border: false" style="height: 70px;">
            <div style="color: #4e5766; padding: 6px 0px 0px 0px; margin: 0px auto; text-align: center; font-size:12px; font-family:微软雅黑;">
                @2012-2014 Copyright: ChenJianwei Personal.&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="#" target="_blank" style="text-decoration: none;">原始部落整合平台</a><br />
                建议使用&nbsp;
                <a href="http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home" target="_blank" style="text-decoration: none;">IE(Version 9/10/11)</a>/
                <a href="https://www.google.com/intl/zh-CN/chrome/browser/" target="_blank" style="text-decoration: none;">Chrome</a>/
                <a href="http://firefox.com.cn/download/" target="_blank" style="text-decoration: none;">Firefox</a>
                &nbsp;系列浏览器。
                <script type="text/javascript">
                    var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
                    document.write(unescape("%3Cspan id='cnzz_stat_icon_5654850'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s9.cnzz.com/stat.php%3Fid%3D5654850%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
                </script>
            </div>
        </div>


    </div> 

</body>
</html>