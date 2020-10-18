<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- 引入wangEditor -->
    <script src="/resources/wangEditor.min.js"></script>
</head>
<body>
    <div>
        <button id="btnRead">讀取內容</button>
        <button id="btnWrite">寫入內容</button>
    </div>
<div id="divEditor" style="width: 800px;height:600px"></div>
<script>
    var E = window.wangEditor;
    var editor = new E("#divEditor");
    editor.create();
    document.getElementById("btnRead").onclick=function () {
        var content = editor.txt.html();//獲取html內容
        alert(content);
    }
</script>
</body>
</html>