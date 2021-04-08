<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="kr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create Post!</h1>
                        </div>
                        <c:choose>
                            <c:when test="${post!=null}">
                                <form action="/post" method="post" class="user">
                                    <input type="number" name="id" value="${post.id}" hidden>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="title"
                                               placeholder="Title" readonly value="${post.title}">
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control form-control-user" name="content" readonly>${post.content}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="name" placeholder="name" readonly value="${post.name}">
                                    </div>
                                    <c:choose>
                                        <c:when test="${!isModify}">
                                            <a id="modify" href="/post/${post.id}?isModify=true" class="btn btn-primary btn-user btn-block">
                                                Modify
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a id="modifySubmit" class="btn btn-primary btn-user btn-block">
                                                Submit
                                            </a>
                                        </c:otherwise>
                                    </c:choose>

                                    <a id="delete" class="btn btn-danger btn-user btn-block">
                                        Delete
                                    </a>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/post" method="post" class="user">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="title"
                                               placeholder="Title">
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control form-control-user" name="content"/></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user"
                                               name="name" placeholder="name">
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-user btn-block">
                                        Register Post
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<!-- Bootstrap core JavaScript-->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/js/sb-admin-2.min.js"></script>

<!-- 글 수정 스크립트-->
<c:if test="${isModify}">
    <script>
        $("input").removeAttr("readonly")
        $("textarea").removeAttr("readonly")
        $("#modifySubmit").click(function (){
            const postVO =new  Object()
            postVO.id = $("[name='id']").val()
            postVO.title = $("[name='title']").val()
            postVO.content= $("[name='content']").val()
            postVO.name = $("[name='name']").val()

            $.ajax({
                type:"PUT",
                url:"/post",
                contentType:"application/json; charset=utf-8",
                data:JSON.stringify(postVO),
                success:function (data) {
                    if(data)
                        location.href="/post/"+postVO.id
                }
            })
        })
    </script>
</c:if>

<!-- 글 삭제 스크립트-->
<script>
    $("#delete").click(function(){
        const chk = confirm("정말로 삭제하시겠습니까?")
        if(chk) {
            $.ajax({
                type: "DELETE",
                url: "/post/" + $("[name='id']").val(),
                success: function (data){
                    if (data)
                        location.href = "/board"
                }
            })
        }
    })
</script>
</body>

</html>