<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        #dlgBook{
            padding: 10px
        }
    </style>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">

    <script src="/resources/wangEditor.min.js"></script>


    <script type="text/html" id="toolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" id="btnAdd" onclick="showCreate()">添加</button>
        </div>
    </script>

</head>
<body>


<div class="layui-container">
    <blockquote class="layui-elem-quote">圖書列表</blockquote>
    <!-- 資料表格 -->
    <table id="grdBook" lay-filter="grdBook"></table>
</div>
<!--表單內容-->
<div id="dialog" style="padding: 10px;display: none">
    <form class="layui-form" >
        <div class="layui-form-item">
            <!-- 圖書類別 -->
            <select id="categoryId" name="categoryId" lay-verify="required" lay-filter=
            "categoryId">
                <option value=""></option>
                <option value="1">前端</option>
                <option value="2">後端</option>
                <option value="3">測試</option>
                <option value="4">產品</option>
            </select>

        </div>
        <div class="layui-form-item">
            <!-- 書名 -->
            <input type="text" id="bookName" name="bookName" required lay-verify="required" placeholder="請輸入書名"
                   autocomplete="off" class="layui-input">
        </div>


        <div class="layui-form-item">
            <!-- 子標題 -->
            <input type="text" id="subTitle" name="subTitle" required lay-verify="required" placeholder="請輸入子標題"
                   autocomplete="off" class="layui-input">
        </div>

        <div class="layui-form-item">
            <!-- 作者 -->
            <input type="text" id="author" name="author" required lay-verify="required" placeholder="請輸入作者資訊"
                   autocomplete="off" class="layui-input">
        </div>

        <div style="margin-top: 30px;font-size: 130%">圖書介紹(默認第一圖將作為圖書封面)</div>
        <div class="layui-form-item" >
            <!-- wangEditor編輯器 -->
            <div id="editor" style="width: 100%">

            </div>
        </div>
        <!-- 圖書編號 -->
        <input id="bookId" type="hidden">
        <!-- 當前表單操作類型,create代表新增 update代表修改 -->
        <input id="optype"  type="hidden">
        <div class="layui-form-item" style="text-align: center">
            <!-- 提交按鈕 -->
            <button class="layui-btn" lay-submit="" lay-filter="btnSubmit">立即提交</button>
        </div>
    </form>
</div>
<script src="/resources/layui/layui.all.js"></script>
<script>

    var table = layui.table; //table資料表格對象
    var $ = layui.$; //jQuery
    var editor = null; //wangEditor富文字編輯器對象
    //初始化圖書列表
    table.render({
        elem: '#grdBook'  //指定div
        , id : "bookList" //資料表格id
        , toolbar: "#toolbar" //指定工具列,包含新增添加
        , url: "/management/book/list" //資料介面
        , page: true //開啟分頁
        , cols: [[ //表頭
            {field: 'bookName', title: '書名', width: '300'}
            , {field: 'subTitle', title: '子標題', width: '200'}
            , {field: 'author', title: '作者', width: '200'}
            , {type: 'space', title: '操作', width: '200' , templet : function(d){
                    //為每一行表格資料生成"修改"與"刪除"按鈕,並附加data-id屬性代表圖書編號
                    return "<button class='layui-btn layui-btn-sm btn-update'  data-id='" + d.bookId + "' data-type='update' onclick='showUpdate(this)'>修改</button>" +
                        "<button class='layui-btn layui-btn-sm btn-delete'  data-id='" + d.bookId + "'   onclick='showDelete(this)'>刪除</button>";
                }
            }
        ]]
    });
    //顯示更新圖書對話方塊
    //obj對應點擊的"修改"按鈕物件
    function showUpdate(obj){
        //彈出"編輯圖書"對話方塊
        layui.layer.open({
            id: "dlgBook", //指定div
            title: "編輯圖書", //標題
            type: 1,
            content: $('#dialog').html(), //設置對話方塊內容,複製自dialog DIV
            area: ['820px', '730px'], //設置對話方塊寬度高度
            resize: false //是否允許調整尺寸
        })

        var bookId = $(obj).data("id"); //獲取"修改"按鈕附帶的圖書編號
        $("#dlgBook #bookId").val(bookId); //為表單隱藏欄位賦值,提交表單時用到

        editor = new wangEditor('#dlgBook #editor'); //初始化富文字編輯器
        editor.customConfig.uploadImgServer = '/management/book/upload' //設置圖片上傳路徑
        editor.customConfig.uploadFileName = 'img'; //圖片上傳時的參數名
        editor.create(); //創建wangEditor
        $("#dlgBook #optype").val("update"); //設置當前表單提交時提交至"update"更新位址

        //發送ajax請求,獲取對應圖書資訊
        $.get("/management/book/id/" + bookId , {} , function(json){
            //文字方塊回填已有數據
            $("#dlgBook #bookName").val(json.data.bookName);//書名
            $("#dlgBook #subTitle").val(json.data.subTitle); //子標題
            $("#dlgBook #author").val(json.data.author);//作者
            $("#dlgBook #categoryId").val(json.data.categoryId); //分類選項
            editor.txt.html(json.data.description); //設置圖文內容
            layui.form.render();//重新渲染LayUI表單
        } , "json")



    }
    //顯示新增圖書對話方塊
    function showCreate(){
        //彈出"新增圖書"對話方塊
        layui.layer.open({
            id: "dlgBook",
            title: "新增圖書",
            type: 1,
            content: $('#dialog').html(),
            area: ['820px', '730px'],
            resize: false
        })
        //初始化wangEditor
        editor = new wangEditor('#dlgBook #editor');
        editor.customConfig.uploadImgServer = '/management/book/upload';//設置圖片上傳位址
        editor.customConfig.uploadFileName = 'img';//設置圖片上傳參數
        editor.create();//創建wangEditor

        layui.form.render(); //LayUI表單重新
        $("#dlgBook #optype").val("create");//設置當前表單提交時提交至"create"新增地址

    };

    //對話方塊表單提交
    layui.form.on('submit(btnSubmit)', function(data){
        //獲取表單數據
        var formData = data.field;

        //判斷是否包含至少一副圖片,預設第一圖作為封面顯示
        var description = editor.txt.html();
        if(description.indexOf("img") == -1){
            layui.layer.msg('請放置一副圖片作為封面');
            return false;
        }
        //獲取當前表單要提交的地址
        //如果是新增資料則提交至create
        //如果是更新資料則提交至update
        var optype = $("#dlgBook #optype").val();

        if(optype == "update"){
            //更新資料時,提交時需要附加圖書編號
            formData.bookId=$("#dlgBook #bookId").val();
        }
        //附加圖書詳細描述的圖文html
        formData.description = description;
        //向伺服器發送請求
        $.post("/management/book/" + optype , formData , function(json){
            if(json.code=="0"){
                //處理成功,關閉對話方塊,刷新列表,提示操作成功
                layui.layer.closeAll();
                table.reload('bookList');
                layui.layer.msg('資料操作成功,圖書清單已刷新');
            }else{
                //處理失敗,提示錯誤資訊
                layui.layer.msg(json.msg);
            }
        } ,"json")
        return false;
    });
    //刪除圖書
    function showDelete(obj){
        //獲取當前點擊的刪除按鈕中包含的圖書編號
        var bookId = $(obj).data("id");
        //利用layui的詢問對話方塊進行確認
        layui.layer.confirm('確定要執行刪除操作嗎?', {icon: 3, title:'提示'}, function(index){

            //確認按鈕後發送ajax請求,包含圖書編號
            $.get("/management/book/delete/" + bookId, {}, function (json) {
                if(json.code=="0"){
                    //刪除成功刷新表格
                    table.reload('bookList');
                    //提示操作成功
                    layui.layer.msg('資料操作成功,圖書清單已刷新');
                    //關閉對話方塊
                    layui.layer.close(index);
                }else{
                    //處理失敗,提示錯誤資訊
                    layui.layer.msg(json.msg);
                }
            }, "json");

        });

    }

</script>
</body>
</html>

