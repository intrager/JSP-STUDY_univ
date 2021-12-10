/**
 * 2021.12.09
 * 작성자 : Bruce Han
 * Created by IntelliJ IDEA 2021.1.3 x64
 */

package iducs.jsp.final201712046.controller;

import iducs.jsp.final201712046.model.Writer;
import iducs.jsp.final201712046.repository.WriterDAOImpl;
import iducs.jsp.final201712046.util.DescByWriterName;
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
// name이 충돌날 수 있으므로 controller 마다 다르게 해주는 게 좋을듯. 사실 name은 없어도 됨.
@WebServlet(name = "writers", urlPatterns = {"/writers/update-form.do", "/writers/update.do",
        "/writers/delete.do", "/writers/list.do", "/writers/sort.do", "/writers/detail.do",
        "/writers/login-form.do", "/writers/login.do", "/writers/logout.do", "/writers/register-form.do", "/writers/register.do"})
public class WriterController extends HttpServlet {

    public void doService(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* URI를 문자열에 저장하고, http:// ... war/가 contextPath가 됨.
            전체 URI에서 contextPath를 제거한 경로명으로 명령을 구분함.
         */
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = uri.substring(contextPath.length() + 1);   // writers/post.do, writers/list.do 가 반환됨
        String action = command.substring(command.lastIndexOf("/") + 1);    // post.do, list.do 반환

        // 데이터베이스 처리 요청 또는 서비스 요청 코드가 추가
        WriterDAOImpl dao = new WriterDAOImpl();
        HttpSession session = request.getSession();

        if(action.equals("register.do")) {
            Writer writer = new Writer();
            writer.setEmail(request.getParameter("email"));
            writer.setPw(request.getParameter("pw"));
            writer.setName(request.getParameter("name"));
            writer.setPhone(request.getParameter("phone"));
            writer.setAddress(request.getParameter("address"));

            if( dao.create(writer) > 0 ) {
                request.setAttribute("msg","성공 : 회원이 정상적으로 등록되었습니다.");
                request.setAttribute("writer", writer);
                // 처리 결과를 view에 전달한다.
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // writers/post.jsp로 포워딩
            } else {
                request.setAttribute("msg","실패 : 회원 등록 오류");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("register-form.do")) {
            request.getRequestDispatcher("../writers/register-form.jsp").forward(request, response);
        }
        else if (action.equals("login-form.do")) {
            request.getRequestDispatcher("../writers/login-form.jsp").forward(request, response);
        }
        else if (action.equals("login.do")) {
            Writer writer = new Writer();
            writer.setEmail(request.getParameter("email"));
            writer.setPw(request.getParameter("pw"));

            Writer retWriter = null;

            if((retWriter = dao.read(writer)) != null) {  // readByEmail
                // request.setAttribute("writer", retwriter);  // key -> writer  // ("키값", 값)
                session.setAttribute("logined", retWriter);  // ${sessionScope.writer}
                // session.setAttribute("writer", writer);  // ${sessionScope.writer.email}
                request.getRequestDispatcher("../main/index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "로그인 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("logout.do")) {
            session.invalidate();   // session 객체를 무효화 (메모리에 존재하지 않으므로 접근 안됨)
            request.getRequestDispatcher("../main/index.jsp").forward(request, response);
        }
        else if (action.equals("detail.do")) {
            Writer writer = new Writer();
            writer.setEmail(request.getParameter("email"));

            Writer retWriter = null;

            if((retWriter = dao.readByEmail(writer)) != null) {  // readByEmail
                request.setAttribute("writer", retWriter);   // email로 조회한 회원 정보 객체를 writer라는 키로 request에 attribute로써 저장
                // ${requestScope.writer}
                request.getRequestDispatcher("../writers/detail-form.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "회원 상세 페이지 조회 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("update-form.do")) {    // update를 위한 정보 조회 폼
            Writer writer = new Writer();

            writer.setEmail(request.getParameter("email"));
            Writer retWriter = null;
            if((retWriter = dao.readByEmail(writer)) != null) {
                request.setAttribute("writer", retWriter);  // key -> writer  // ("키값", 값)
                request.getRequestDispatcher("../writers/update-form.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "업데이트 폼 불러오기가 불가합니다");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("update.do")) {    // dao에게 업데이트를 요청
            Writer writer = new Writer();
            writer.setId(Long.parseLong(request.getParameter("id")));
            writer.setName(request.getParameter("name"));
            writer.setPw(request.getParameter("pw"));
            writer.setEmail(request.getParameter("email"));
            writer.setPhone(request.getParameter("phone"));
            writer.setAddress(request.getParameter("address"));

            if(dao.update(writer) > 0) {
                request.setAttribute("msg","회원 업데이트 완료");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // writers/about.jsp로 포워딩
            } else {
                request.setAttribute("msg", "회원 업데이트 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        } else if (action.equals("delete.do")) {
            Writer writer = new Writer();
            writer.setId(Long.parseLong(request.getParameter("id")));

            if(dao.delete(writer) > 0) {
                request.setAttribute("msg","회원 삭제완료");
                request.getRequestDispatcher("../status/message.jsp").forward(request, response);    // writers/about.jsp로 포워딩
            } else {
                request.setAttribute("msg", "삭제 실패");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("list.do")) {
            ArrayList<Writer> writerList = new ArrayList<Writer>(); // 처리결과 한 개 이상의 블로그를 저장하는 객체

            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지 번호가 정수형으로 저장
            int curPageNo = (pageNo != null) ? Integer.parseInt(pageNo):1;
            int perPage = 3;    // 한 페이지에 나타나는 행의 수
            int perPagination = 3;  // 한 화면에 나타나는 페이지 번호 수
            int totalRows = dao.readTotalRows();    // dao에서 총 행의 수를 질의함

            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((writerList = (ArrayList<Writer>) dao.readListPagination(pagination)) != null) { // 한 개 이상의 맴버가 반환. JCF(Java Collection Framework)에 대한 이해
                request.setAttribute("writerList", writerList);
                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../writers/writer-list-view.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", "실패 : 회원 목록 조회가 불가합니다");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response);
            }
        }
        else if (action.equals("sort.do")) {
            ArrayList<Writer> writerList = new ArrayList<Writer>();   // 처리결과 한 개 이상의 멤버를 저장하는 객체

            String properties = request.getParameter("by");
//            String pageNo = request.getParameter("pn"); // 매개변수로 전달된 현재 페이지 번호가 정수형으로 저장
//
//            int curPageNo = (pageNo != null) ? Integer.parseInt(pageNo):1;
//            int perPage = 3;    // 한 페이지에 나타나는 행의 수
//            int perPagination = 3;  // 한 화면에 나타나는 페이지 번호 수
//            int totalRows = dao.readTotalRows();    // dao에서 총 행의 수를 질의함
//
//            Pagination pagination = new Pagination(curPageNo, perPage, perPagination, totalRows);

            if((writerList = (ArrayList<Writer>) dao.readList()) != null) { // 한 개 이상의 멤버가 반환. JCF(Java Collection Framework) 참고
                if(properties.equals("desc,name"))
                    Collections.sort(writerList, new DescByWriterName());  // 멤버 이름 기준 내림차순

                request.setAttribute("writerList", writerList);
//                request.setAttribute("pagination", pagination);
                request.getRequestDispatcher("../writers/writer-list-view.jsp").forward(request, response);    // writers-list-view.jsp로 포워딩
            } else {
                request.setAttribute("msg", "회원 목록 조회 오류!");
                request.getRequestDispatcher("../status/error.jsp").forward(request, response); // 오류
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
