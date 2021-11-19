package com.koreait.board2.board;

import com.koreait.board2.MyUtils;
import com.koreait.board2.model.BoardVO;
import com.koreait.board2.model.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/board/mod")
public class BoardModServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if(MyUtils.isLogout(req)){
            res.sendRedirect("/user/login");
            return;
        }
        //if(!MyUtils.isLogin(req)){
        //            res.sendRedirect("/user/login");
        //            return;
        //        }

        String striboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(striboard);

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);

        BoardVO uu = BoardDAO.selDetail(vo);
        req.setAttribute("up",uu);

        MyUtils.disForward(req,res,"board/mod");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        String Siboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(Siboard);

        //int iboard = MyUtils.getParameter(req,"iboard");

        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);
        vo.setTitle(title);
        vo.setCtnt(ctnt);
        vo.setWriter(MyUtils.getLoginUserPk(req));



//        BoardVO aa = BoardDAO.upBoard(vo);
//        req.setAttribute("wri",aa);


        int result = BoardDAO.upBoard(vo);
        switch (result) {
            case 1:
                res.sendRedirect("/board/detail?iboard=" + iboard);
                break;
            case 0:
                req.setAttribute("err","수정에 실패했습니다");
                req.setAttribute("data",vo);
                doGet(req,res);
                break;
        }
    }
}
