<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%-- JSTL core library 사용을 위한 선언 --%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Clean Blog - Start Bootstrap Theme</title>
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
                    <h1>Writer List</h1>
                    <span class="subheading">writer List</span>
                </div>
            </div>
        </div>
    </div>
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-10 col-lg-8 col-xl-7">
            <c:forEach items="${requestScope.writerList}" var="writer">
                <!-- Post preview-->
                <div class="post-preview">
                    <!-- EL은 속성값 전문 코드, "손쉬운 접근" / $ {writer.id} 이런 게 el 방식-->
                    <a href="../writers/detail.do?email=${writer.email}">
                        <h2 class="post-title">${writer.name}</h2>
                        <h3 class="post-subtitle">${writer.email}</h3>
                    </a>
                    <p class="post-meta">
                        phone number : ${writer.phone}
                    </p>
                </div>
                <!-- Divider-->
                <hr class="my-4" />
            </c:forEach>
            <!-- Pager-->
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <c:if test="${pagination.beginPageNo > pagination.perPagination }">
                        <li class="page-item">
                            <a class="page-link" href="../writers/list.do?pn=${pagination.beginPageNo - 1}" tabindex="-1" aria-disabled="true">Prev</a>
                        </li>
                    </c:if>

                    <c:forEach var="pageNo" begin="${pagination.beginPageNo}" end="${pagination.endPageNo}">
                        <c:choose>
                            <c:when test="${pageNo == pagination.curPageNo}">
                                <li class="page-item active"><a class="page-link" href="../writers/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="../writers/list.do?pn=${pageNo}">${pageNo}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${pagination.endPageNo < pagination.totalPages}">
                        <li class="page-item">
                            <a class="page-link" href="../writers/list.do?pn=${pagination.endPageNo + 1}" tabindex="-1" aria-disabled="true">Next</a>
                        </li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>
</div>
<!-- Footer-->
<jsp:include page="../main/footer.jsp" />
</body>
</html>
