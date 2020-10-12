<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>會員註冊-慕課書評網</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0, maximum-scale=1.0,user-scalable=no">

    <link rel="stylesheet" href="./resources/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="./resources/raty/lib/jquery.raty.css">
    <script src="./resources/jquery.3.3.1.min.js"></script>
    <script src="./resources/bootstrap/bootstrap.min.js"></script>
    <style>
        .container {
            padding: 0px;
            margin: 0px;
        }

        .row {
            padding: 0px;
            margin: 0px;
        }

        .col- * {
            padding: 0px;
        }

        .description p {
            text-indent: 2em;
        }

        .description img {
            width: 100%;
        }

    </style>

</head>
<body>
<!--<div style="width: 375px;margin-left: auto;margin-right: auto;">-->
<div class="container ">
    <nav class="navbar navbar-light bg-white shadow">
        <ul class="nav">
            <li class="nav-item">
                <a href="/">
                    <img src="https://m.imooc.com/static/wap/static/common/img/logo2.png" class="mt-1"
                         style="width: 100px">
                </a>
            </li>
        </ul>
    </nav>


    <div class="container mt-2 p-2 m-0">
        <form id="frmLogin">
            <div class="passport bg-white">
                <h4 class="float-left">會員註冊</h4>
                <h6 class="float-right pt-2"><a href="/login.html">會員登錄</a></h6>
                <div class="clearfix"></div>
                <div class="alert d-none mt-2" id="tips" role="alert">

                </div>

                <div class="input-group  mt-2 ">
                    <input type="text" id="username" name="username" class="form-control p-4" placeholder="請輸入用戶名">
                </div>

                <div class="input-group  mt-4 ">
                    <input id="password" name="password" class="form-control p-4" placeholder="請輸入密碼" type="password">
                </div>

                <div class="input-group  mt-4 ">
                    <input type="text" id="nickname" name="nickname" class="form-control p-4" placeholder="請輸入用戶名"
                    >
                </div>

                <div class="input-group mt-4 ">
                    <div class="col-5 p-0">
                        <input type="text" id="verifyCode" name="vc" class="form-control p-4" placeholder="驗證碼">
                    </div>
                    <div class="col-4 p-0 pl-2 pt-0">
                        <!-- 驗證碼圖片 -->
                        <img id="imgVerifyCode" src="/verify_code"
                             style="width: 120px;height:50px;cursor: pointer">
                    </div>

                </div>

                <a id="btnSubmit" class="btn btn-success  btn-block mt-4 text-white pt-3 pb-3">注&nbsp;&nbsp;&nbsp;&nbsp;冊</a>
            </div>
        </form>

    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-body">
                您已註冊成功
            </div>
            <div class="modal-footer">
                <a href="/login.html" type="button" class="btn btn-primary">去登錄</a>
            </div>
        </div>
    </div>
</div>


<script>
    //控制錯誤資訊的顯示與隱藏
    function showTips(isShow, css, text) {
        if (isShow) {
            $("#tips").removeClass("d-none")
            $("#tips").hide();
            $("#tips").addClass(css);
            $("#tips").text(text);
            $("#tips").fadeIn(200);
        } else {
            $("#tips").text("");
            $("#tips").fadeOut(200);
            $("#tips").removeClass();
            $("#tips").addClass("alert")
        }
    }
    //重新發送請求,刷新驗證碼
    function reloadVerifyCode(){
        //請在這裡實現刷新驗證碼
        $("#imgVerifyCode").attr("src","/verify_code?ts=" + new Date().getTime() );
    }

    //點擊驗證碼圖片刷新驗證碼
    $("#imgVerifyCode").click(function () {
        reloadVerifyCode();
    });

    //點擊提交按鈕,向/registe發起ajax請求
    //提交請求包含四個參數
    //vc:前臺輸入驗證碼  username:用戶名 password:密碼 nickname:昵稱
    $("#btnSubmit").click(function () {
        //表單校驗
        var username = $.trim($("#username").val());
        var regex = /^.{6,10}$/;
        if (!regex.test(username)) {
            showTips(true, "alert-danger", "用戶名請輸入正確格式（6-10位）");
            return;
        } else {
            showTips(false);
        }

        var password = $.trim($("#password").val());

        if (!regex.test(password)) {
            showTips(true, "alert-danger", "密碼請輸入正確格式（6-10位）");
            return;
        } else {
            showTips(false);
        }

        $btnReg = $(this);

        $btnReg.text("正在處理...");
        $btnReg.attr("disabled", "disabled");

        //發送ajax請求
        $.ajax({
            url: "/registe",
            type: "post",
            dataType: "json",
            data: $("#frmLogin").serialize(),
            success: function (data) {
                //結果處理,根據伺服器返回code判斷伺服器處理狀態
                //伺服器要求返回JSON格式:
                //{"code":"0","msg":"處理消息"}
                console.info("伺服器回應:" , data);
                if (data.code == "0") {
                    //顯示註冊成功對話方塊
                    $("#exampleModalCenter").modal({});
                    $("#exampleModalCenter").modal("show");
                } else {
                    //伺服器校驗異常,提示錯誤資訊
                    showTips(true, "alert-danger", data.msg);
                    reloadVerifyCode();
                    $btnReg.text("注    冊");
                    $btnReg.removeAttr("disabled");
                }
            }
        });
        return false;
    });


</script>
</body>
</html>

