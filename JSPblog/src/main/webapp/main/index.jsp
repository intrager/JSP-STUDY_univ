<%--
  Created by IntelliJ IDEA 2021.1.3 x64
  User: 201712046@office.induk.ac.kr (Bruce Han)
  Date: 2021-12-09
  Time: 오전 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>index.jsp</title>
    <!-- %@ include는 컴파일 시점에 포함시켜버림 -->
    <jsp:include page="../main/header.jsp" />
</head>
<body>

<!-- Navigation-->
<jsp:include page="../main/nav.jsp" />
<!-- Page Header-->
<header class="masthead" style="background-image: url('../assets/img/hangang.jpg')">
    <div class="container position-relative px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="site-heading">
                    <h1>환영 ! </h1>
                    <span class="subheading">Blog201712046 한정수</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">

        </div>
    </div>
</div>
<!-- Footer-->
<jsp:include page="../main/footer.jsp" />
</body>
</html>
