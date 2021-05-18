<%@ page import="vo.UserVO" %>
<%@ page import="vo.PostVO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page session="false" pageEncoding="UTF-8"%>


<html lang="kr" >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <link href="/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <link href="/css/sb-admin-2.min.css" rel="stylesheet">

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
                            <h1 class="h4 text-gray-900 mb-4">Create Post!</h1>
                        </div>

                        <c:choose>
                            <c:when test="${post!=null}">
                                <form id="modifyForm" action="/post" method="PUT" class="user">
                                    <input type="number" name="id" value="${post.id}" hidden>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="title" placeholder="Title" value="${post.title}" readonly>
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control form-control-user" name="content" readonly>${post.content}</textarea>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="name" placeholder="name" value="${post.user.name}" readonly>
                                    </div>
                                    <c:if test="${post.fileList.size() != 0}">
                                        <c:forEach var="file" items="${post.fileList}">
                                            <a href="/${post.id}/file/${file.id}" class="btn btn-primary btn-user btn-block">
                                                    ${file.name}
                                            </a>
                                        </c:forEach>
                                    </c:if>

                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/post" method="post" class="user">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" name="title" placeholder="Title">
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control form-control-user" name="content"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <input type="file" id="files" multiple="multiple">
                                    </div>
                                    <a id="register" class="btn btn-primary btn-user btn-block">
                                        Register Post
                                    </a>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<script src="/vendor/jquery/jquery.min.js"></script>

<c:if test="${isModify}">
    <script>
        $("[name='title']").removeAttr("readonly")
        $("textarea").removeAttr("readonly")
        $("#modifySubmit").click(function (){
            var postVO =new  Object()
            postVO.id = $("[name='id']").val()
            postVO.title = $("[name='title']").val()
            postVO.content= $("[name='content']").val()
            $.ajax({
                type:"PUT",
                url:"/post",
                contentType:"application/json; charset=utf-8",
                data: JSON.stringify(postVO),
                success:function(data) {
                    if(data) {
                        location.href = "/post/"+ postVO.id
                    }
                }
            })
        })
    </script>
</c:if>

<script>
    $("#delete").click(function(){
        var chk = confirm("정말로 삭제하시겠습니까?")
        if(chk) {
            $.ajax({
                type: "DELETE",
                url: "/post/" + $("[name='id']").val(),
                success: function (data) {
                    if (data)
                        location.href = "/board"
                }
            })
        }
    })
</script>

<script>
    $("#register").click(function (){
        var postVO = new Object()
        var formData = new FormData()
        var files = $('#files')[0].files
        var postId

        postVO.title = $("[name='title']").val()
        postVO.content = $("[name='content']").val()

        $.ajax({
            method:"POST",
            url:"/post",
            data: JSON.stringify(postVO),
            contentType: "application/json; charset=UTF-8",
            success: function (data){
                postId = data

                if(files.length != 0){
                    for(var i=0; i<files.length; i++)
                        formData.append('files',files[i])

                    $.ajax({
                        method:"POST",
                        enctype: 'multipart/form-data',
                        url:"/"+postId+"/file",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success:function(){
                            location.href='/board'
                        },
                        error: function (e){console.log(e)}
                    })
                }
                else
                    location.href='/board'
            }
        })
    })
</script>

<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="/vendor/jquery-easing/jquery.easing.min.js"></script>

<script src="/js/sb-admin-2.min.js"></script>


</body>

</html>