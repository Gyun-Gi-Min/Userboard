package com.koreait.board2.user;

import com.koreait.board2.MyUtils;
import com.koreait.board2.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override  //화면띄우는 담당
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /*
        String jsp = "/WEB-INF/view/user/login.jsp";
        req.getRequestDispatcher(jsp).forward(req,res);
        */

        MyUtils.disForward(req, res,"user/login");

    }

    @Override //로그인 처리 담당
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        UserVO param = new UserVO();
        param.setUid(uid);
        param.setUpw(upw);

        //0 : 로그인 실패, 1: 로그인 성공 2: 아이디 없음 3: 비번틀림
        int result = UserDAO.login(param);
        if(result == 1){
            //세션로그인 처리
            param.setUpw(null);

            HttpSession session = req.getSession();
            session.setAttribute("loginUser",param);
            //param엔 iuser , nm, uid 있다.

        res.sendRedirect("/board/list");
        return; //중요함. 없으면 같이 실행되어버림.

        }
        String err = null;
        switch (result){
            case 0:
                err = "로그인에 실패했습니다";
                break;
            case 2:
                err = "아이디가 틀렸습니다";
                break;
            case 3:
                err = "비밀번호를 확인해 주세요";
                break;
        }
        req.setAttribute("err",err);

        doGet(req,res); //로그인 페이지로 이동



    }
}
