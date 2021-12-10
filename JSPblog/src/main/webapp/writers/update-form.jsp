<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
    <jsp:include page="../main/header.jsp" />

    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary">
    <!-- Navigation-->
    <jsp:include page="../main/nav.jsp" />
    <!-- Page Header-->
    <header class="masthead" style="background-image: url('../assets/img/hangang.jpg')">
        <div class="container position-relative px-4 px-lg-5">
            <div class="row gx-4 gx-lg-5 justify-content-center">
                <div class="col-md-10 col-lg-8 col-xl-7">
                    <div class="site-heading">
                        <h1>회원 정보 상세</h1>
                        <span class="subheading">Detail</span>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                            </div>
                            <form class="user" action="../writers/update.do" method="post">
                                <div class="form-group row">
                                    <input type="hidden" name="id" value="${requestScope.writer.id}">
                                    <div class="form-group">
                                        <input name="email" value="${requestScope.writer.email}" type="email" class="form-control form-control-user" id="exampleInputEmail"
                                               placeholder="Email" readonly>
                                    </div>
                                    <div class="form-group">
                                        <input name="pw" type="password" value="${requestScope.writer.pw}" class="form-control form-control-user" id="exampleFirstName"
                                               placeholder="Password" required>
                                    </div>
                                    <div class="form-group">
                                        <input name="name" type="text" value="${requestScope.writer.name}" class="form-control form-control-user" id="exampleLastName"
                                               placeholder="Name" required>
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <div class="form-group">
                                        <input name="phone" type="text" value="${requestScope.writer.phone}" class="form-control form-control-user" id="exampleInputPassword"
                                               placeholder="Phone" required>
                                    </div>
                                    <div class="form-group">
                                        <input name="address" type="address" value="${requestScope.writer.address}" class="form-control form-control-user" id="exampleRepeatPassword"
                                               placeholder="address" required>
                                    </div>
                                </div>
                                <button class="btn btn-primary btn-user btn-block" type="submit">
                                    Update!
                                </button>
<%--                                <hr>--%>
<%--                                <a href="index.html" class="btn btn-google btn-user btn-block">--%>
<%--                                    <i class="fab fa-google fa-fw"></i> Register with Google--%>
<%--                                </a>--%>
<%--                                <a href="index.html" class="btn btn-facebook btn-user btn-block">--%>
<%--                                    <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook--%>
<%--                                </a>--%>
                            </form>
                            <hr>

                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../js/sb-admin-2.min.js"></script>

</body>

</html>