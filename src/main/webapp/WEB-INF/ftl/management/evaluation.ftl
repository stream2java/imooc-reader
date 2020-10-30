<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>短評管理</title>
    <style>
        #dlgBook{
            padding: 10px
        }
    </style>
    <link rel="stylesheet" href="/resources/layui/css/layui.css">


</head>
<body>


<div class="layui-container">
    <blockquote class="layui-elem-quote">近期短評列表</blockquote>
    <table id="grdEvaluation" lay-filter="grdEvaluation"></table>
</div>

<script src="/resources/layui/layui.all.js"></script>
<script>

    var table = layui.table;
    var $ = layui.$;
    var editor = null;
    //第一個實例
    table.render({
        elem: '#grdEvaluation'
        , id : "evaluationList"
        , url: "/management/evaluation/list" //資料介面
        , page: true //開啟分頁
        , limit :20
        , cols: [[ //表頭
            {field:"createTime" , title: '發佈時間', width: '200', templet: function (d) {
        return layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss");
    }}
            , {field: 'content', title: '短評', width: '400'}
            , {type:"space" ,title: '圖書', width: '200', templet: function (d) {
                    return d.book.bookName;
                }}
            , {type: "space" , title: '用戶名', width: '100', templet: function (d) {
                    console.info(d);
                    return d.member.nickname;
                }}
            , {type: 'space', title: '操作', width: '100' , templet : function(d){
                    if(d.state=="enable") {
                        return "<button class='layui-btn layui-btn-sm '  data-id='" + d.evaluationId + "' onclick='disableEvaluation(this)'>禁用</button>";
                    }else if(d.state =="disable"){
                        return "已禁用";
                    }
                }
            }
        ]]
    });
    function disableEvaluation(obj){
        var evaluationId = $(obj).data("id");
        layui.layer.prompt({
            title: '請輸入禁用原因',
        },function(value, index, elem){
            $.post("/management/evaluation/disable" , {
                evaluationId : evaluationId , reason:value
            },function(json){
                if(json.code=="0"){
                    layui.layer.close(index);
                    layui.layer.msg("短評已禁用")
                    table.reload('evaluationList');
                }
            } , "json")

        });
    }
</script>
</body>
</html>
