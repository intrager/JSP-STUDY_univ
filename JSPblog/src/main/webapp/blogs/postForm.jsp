<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Clean Blog - Start Bootstrap Theme</title>
        <%@ include file="../main/header.jsp"%>
    </head>
    <body>
        <!-- Navigation-->
        <%@ include file="../main/nav.jsp" %>
        <!-- Page Header-->
        <header class="masthead" style="background-image: url('../assets/img/hangang.jpg')">
            <div class="container position-relative px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <div class="page-heading">
                            <h1>postForm</h1>
                            <span class="subheading">글 작성 페이지</span>
                            <span class="subheading">등록에 성공하면 message로 갑니다</span>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Main Content-->
        <main class="mb-4">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <p>Want to get in touch? Fill out the form below to send me a message and I will get back to you as soon as possible!</p>
                        <div class="my-5">
                            <!-- * * * * * * * * * * * * * * *-->
                            <!-- * * SB Forms Contact Form * *-->
                            <!-- * * * * * * * * * * * * * * *-->
                            <!-- This form is pre-integrated with SB Forms.-->
                            <!-- To make this form functional, sign up at-->
                            <!-- https://startbootstrap.com/solution/contact-forms-->
                            <!-- to get an API token!-->

                            <!-- blogs/post.do -->
                            <form id="contactForm" action="post.do" method="post">
                                <div class="form-floating">
                                    <input class="form-control" id="name" name="name" value="${sessionScope.logined.name}" type="text" placeholder="Enter your name..." data-sb-validations="required,name" readonly/>
                                    <label for="name">Name</label>
                                    <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                </div>
                                <div class="form-floating">
                                    <input class="form-control" id="email" name="email" value="${sessionScope.logined.email}" type="email" placeholder="Enter your email..." data-sb-validations="required,email" readonly/>
                                    <label for="email">Email address</label>
                                    <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                                    <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                                </div>
                                <div class="form-floating">
                                    <input class="form-control" id="phone" name="title" type="title" placeholder="Enter your phone number..." data-sb-validations="required" />
                                    <label for="phone">title</label>
                                    <div class="invalid-feedback" data-sb-feedback="phone:required">A phone number is required.</div>
                                </div>
                                <div class="form-floating">
                                    <textarea class="form-control" id="message" name="content" placeholder="Enter your message here..." style="height: 12rem" data-sb-validations="required"></textarea>
                                    <label for="message">content</label>
                                    <div class="invalid-feedback" data-sb-feedback="message:required">A message is required.</div>
                                </div>
                                <br />

                                <!-- Submit Button-->
                                <button class="btn btn-primary text-uppercase" id="submitButton" name ="submitButton" type="submit">Send</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
<%--            <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>--%>
<%--            <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />--%>
<%--            <div id="editor"></div>--%>
<%--            <script>--%>
<%--                const Editor = toastui.Editor;--%>

<%--                const editor = new Editor({--%>
<%--                    el: document.querySelector('#editor'),--%>
<%--                    height: '500px',--%>
<%--                    initialEditType: 'markdown',--%>
<%--                    previewStyle: 'vertical'--%>
<%--                });--%>

<%--                editor.getMarkdown();--%>
<%--            </script>--%>
        </main>
        <!-- Footer-->
        <%@ include file="../main/footer.jsp" %>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
