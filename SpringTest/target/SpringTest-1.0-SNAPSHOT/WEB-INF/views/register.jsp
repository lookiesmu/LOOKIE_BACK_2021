<%@ page session="false" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <link href="css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form class="user">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" name="name" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" name="email"	placeholder="Email Address">
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user" name="password" placeholder="Password">
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user" name="repeatPassword" placeholder="Repeat Password">
                                </div>
                            </div>
                            <a id="register" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </a>
                            <hr>
                            <a href="index.html" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i>
                                Register with Google
                            </a>
                            <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i>
                                Register with Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="forgot-password.html">
                                Forgot Password?
                            </a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="/">
                                Already have an account? Login!
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>



<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<script src="/js/sb-admin-2.min.js"></script>

<script>
    $("#register").click(function(){
        if(nullCheck())
            return
        else if(!($("[name='password']").val()==$("[name='repeatPassword']").val())) {
            alert("비밀번호와 비밀번호 확인이 맞지 않습니다.")
            $("[name='password']").focus()
        }
        else{
            var userVO = new Object()
            userVO.name = $("[name='name']").val()
            userVO.password = $("[name='password']").val()
            userVO.email = $("[name='email']").val()
            $.ajax({
                url:"/user",
                type:"POST",
                data:JSON.stringify(userVO),
                contentType:"application/json;charset=UTF-8",
                success:function (data) {
                    if(data.error==null)
                        location.href = "/login"
                    else if(data.error === "duplication")
                        alert("email이 중복됩니다.")
                }
            })
        }
    })
    function nullCheck() {
        if($("[name='name']").val()==""){
            alert("이름을 입력해주세요.")
            $("[name='name']").focus()
            return true
        }
        if($("[name='email']").val()==""){
            alert("email을 입력해주세요.")
            $("[name='email']").focus()
            return true
        }
        if($("[name='password']").val()==""){
            alert("비밀번호을 입력해주세요.")
            $("[name='password']").focus()
            return true
        }
        if($("[name='repeatPassword']").val()==""){
            alert("비밀번호 확인을 입력해주세요.")
            $("[name='repeatPassword']").focus()
            return true
        }
        return false
    }
</script>

</body>

</html>