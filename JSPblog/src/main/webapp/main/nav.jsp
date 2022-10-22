<%--
  Created by IntelliJ IDEA.
  User: 201712046@office.induk.ac.kr (Bruce Han)
  Date: 2021-12-09
  Time: 오전 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%-- JSTL core library 사용을 위한 선언 --%>
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="../main/index.jsp">Hanjs's Blog</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <!-- / : Application Context를 의미함 -->
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/list.do?pn=1">List</a></li>
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/sort.do?by=desc,title">DescTitle</a></li>
                <c:choose>
                    <c:when test="${sessionScope.logined == null}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/login-form.do">Login</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/register-form.do">register</a></li>
                    </c:when>
                    <c:when test="${sessionScope.logined != null && sessionScope.logined.email.equals('admin@induk.ac.kr')}">
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/sort.do?by=desc,name">DescBloggerName</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/list.do?pn=1">writerList</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/detail.do?email=${sessionScope.logined.email}">Detail</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/logout.do">Logout</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../blogs/post-form.do">Post</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/detail.do?email=${sessionScope.logined.email}">Detail</a></li>
                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="../writers/logout.do">Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>