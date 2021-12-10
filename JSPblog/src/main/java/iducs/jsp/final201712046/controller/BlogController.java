/**
 * 2021.12.09
 * 작성자 : Bruce Han
 * Created by IntelliJ IDEA 2021.1.3 x64
 */

package iducs.jsp.final201712046.controller;

import iducs.jsp.final201712046.model.Blog;
import iducs.jsp.final201712046.repository.BlogDAOImpl;
import iducs.jsp.final201712046.util.DescByBlogTitle;
import iducs.jsp.final201712046.util.Pagination;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

// controller
// 첫 슬래시는 웹 애플리케이션의 시작위치임. Application context
// urlPatterns : 다수의 url을 기술할 수 있다. servlet에서는 절대적인 경로 접근(/ 로 시작)이 더 좋음. url이 중복되지 않아야 한다.
@WebServlet(name = "post", urlPatterns = {"/blogs/post-form.do", "/blogs/post.do", "/blogs/list.do", "/blogs/sort.do", "/blogs/detail.do", "/blogs/updateForm.do", "/blogs/update.do", "/blogs/delete.do"})
public class BlogController extends HttpServlet {

    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* URI를 문자열에 저장하고, http:// ... war/가 contextPath가 됨.
            전체 URI에서 contextPath를 제거한 경로명으로 명령을 구분함.
         */
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);   // blogs/post.do, blogs/list.do 가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1);    // post.do, list.do 반환

        // 데이터베이스 처리 요청 또는 서비스 요청 코드가 추가
        BlogDAOImpl dao = new BlogDAOImpl();

        if (action.equals("post-form.do")) {
            request.getRequestDispatcher("postForm.jsp").forward(request, response);
        } else if(action.equals("post.do")) {
            Blog blog = new Blog();
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if( dao.create(blog) > 0 ) {
                request.setAttribute("msg","작성 완료");
                request.setAttribute("blog",blog);
                // 처리 결과를 view(메인, list)에 전달한다.
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // blogs/post.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("list.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>();   // 처리결과 한 개 이상의 블로그를 저장하는 객체

            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지 번호가 정수형으로 저장
            int curPageNo = (pageNo != null) ? Integer.parseInt(pageNo):1;
            int perPage = 3;    // 한 페이지에 나타나는 행의 수
            int perPagination = 3;  // 한 화면에 나타나는 페이지 번호 수
            int totalRows = dao.readTotalRows();    // dao에서 총 행의 수를 질의함

            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((blogList = (ArrayList<Blog>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("blogList", blogList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../blogs/list.jsp").forward(request, response);    // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("msg", "블로그 목록 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);   // 오류
            }
        }
        else if (action.equals("sort.do")) {
            ArrayList<Blog> blogList = new ArrayList<Blog>();   // 처리결과 한 개 이상의 블로그를 저장하는 객체

            String properties = request.getParameter("by");

            if((blogList = (ArrayList<Blog>) dao.readList()) != null) { // 한 개 이상의 블로그가 반환. JCF(Java Collection Framework) 참고
                if(properties.equals("desc,title"))
                    Collections.sort(blogList, new DescByBlogTitle());  // 블로그 제목 기준 내림차순

                request.setAttribute("blogList", blogList);
                request.getRequestDispatcher("../blogs/list.jsp").forward(request, response);    // blogs/list.jsp로 포워딩
            } else {
                request.setAttribute("msg", "블로그 목록 조회 오류!");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response); // 오류
            }
        }
        else if (action.equals("detail.do")) {
            // ?email=이메일 : queryString으로 요청한 경우, email 파라미터에 이메일이라는 문자열 값을 전달
            // System.out.println(request.getParameter("email"));   // 요청에 포함된 파라미터 중 email 파라미터 값을 접근
            Blog blog = new Blog();
            String strId = request.getParameter("id");
            long id = Long.parseLong(strId);
            blog.setId(id);     // 옛날 코드
            Blog retBlog = null;

            HttpSession session = request.getSession();

            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog);  // key -> blog  // ("키값", 값)
                session.setAttribute("blog", "로그인 정보");
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);   // 오류
            }
        } else if (action.equals("updateForm.do")) {    // update를 위한 정보 조회 폼
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));     // 옛날 코드
            Blog retBlog = null;
            if((retBlog = dao.read(blog)) != null) {
                request.setAttribute("blog", retBlog);  // key -> blog  // ("키값", 값)
                request.getRequestDispatcher("updateForm.jsp").forward(request, response);
            } else {
                request.setAttribute("errMsg", "블로그 업데이트를 폼 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);   // 오류
            }
        } else if (action.equals("update.do")) {    // dao에게 업데이트를 요청
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));
            blog.setName(request.getParameter("name"));
            blog.setEmail(request.getParameter("email"));
            blog.setTitle(request.getParameter("title"));
            blog.setContent(request.getParameter("content"));

            if(dao.update(blog) > 0) {
                request.setAttribute("msg","글 수정 완료");
                request.setAttribute("blog",blog);  // 얘는 굳이 필요한가?
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // blogs/about.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if (action.equals("delete.do")) {
            Blog blog = new Blog();
            blog.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(blog) > 0) {
                request.setAttribute("msg","글 삭제 완료");
                // 처리 결과를 view(메인, list)에 전달한다. about.jsp -> processOk.jsp
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // blogs/about.jsp로 포워딩
            } else {
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doService(request, response);
    }
}
