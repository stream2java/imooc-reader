
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>慕課書評網資料管理系統</title>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">
</head>

<body class="layui-layout-body">
<!-- Layui後臺佈局CSS -->
<div class="layui-layout layui-layout-admin">
    <!--頭部巡覽列-->
    <div class="layui-header">
        <!--系統標題-->
        <div class="layui-logo" style="font-size:18px">慕課書評網資料管理系統</div>
        <!--右側當前使用者資訊-->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:void(0)">
                    <!--圖示-->
                    <span class="layui-icon layui-icon-user" style="font-size: 20px">
                    </span>
                    <!--使用者資訊-->
                    admin
                </a>
            </li>
            <!--登出按鈕-->
            <li class="layui-nav-item"><a href="/management/logout">註銷</a></li>
        </ul>
    </div>
    <!--左側功能表列-->
    <div class="layui-side layui-bg-black">
        <!--可捲動功能表-->
        <div class="layui-side-scroll">
            <!--可折疊巡覽列-->
            <ul class="layui-nav layui-nav-tree">


                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:void(0)">資料管理</a>
                    <dl class="layui-nav-child module" data-node-id="xxx">
                        <dd><a href="/management/book/index.html" target="ifmMain">圖書管理</a></dd>
                        <dd><a href="短評管理.html" target="ifmMain">短評管理
                            </a></dd>
                    </dl>
                </li>



            </ul>
        </div>
    </div>
    <!--主體部分採用iframe嵌入其他頁面-->
    <div class="layui-body" style="overflow-y: hidden">
        <iframe name="ifmMain" style="border: 0px;width: 100%;height: 100%" src="/management/book/index.html"></iframe>
    </div>
    <!--版權資訊-->
    <div class="layui-footer">
        Copyright © imooc. All Rights Reserved.
    </div>
</div>
<!--LayUI JS文件-->
<script src="/resources/layui/layui.all.js"></script>
<script>
    //將所有功能根據parent_id移動到指定模組下
    layui.$(".function").each(function () {
        var func = layui.$(this);
        var parentId = func.data("parent-id");
        layui.$("dl[data-node-id=" + parentId + "]").append(func);
    });
    //刷新折疊菜單
    layui.element.render('nav');
</script>
</body>
</html>

