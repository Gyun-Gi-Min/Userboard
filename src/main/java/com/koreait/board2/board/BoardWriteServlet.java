package com.koreait.board2.board;

import com.koreait.board2.MyUtils;
import com.koreait.board2.model.BoardVO;
import com.koreait.board2.model.UserVO;
import sun.plugin.dom.core.Element;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO)session.getAttribute("loginUser");
        if(loginUser == null){
            res.sendRedirect("/user/login");
            return;

        }
        //로그인 안되어있으면 "/user/login"로 이동
        //로그인 되어 있으면 아래 소스 실행
        MyUtils.disForward(req,res,"board/write");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        HttpSession session = req.getSession();
        UserVO loginUser = (UserVO)session.getAttribute("loginUser");

        BoardVO vo = new BoardVO();

        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(loginUser.getIuser());

        // TODO : 에러 예외처리 switch?
        int result = BoardDAO.insBoard(vo);

        switch (result){
            case 1:
                res.sendRedirect("/board/list");
                break;
            case 0:
                req.setAttribute("err","글 등록 실패");
                doGet(req,res);
                break;
        }






    }
}
