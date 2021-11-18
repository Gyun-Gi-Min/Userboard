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

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String striboard = req.getParameter("iboard");
        int iboard = Integer.parseInt(striboard);

        BoardVO vo = new BoardVO();
        vo.setIboard(iboard);

        BoardVO uu = BoardDAO.selDetail(vo);
        req.setAttribute("up",uu);



        MyUtils.disForward(req,res,"board/update");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String title = req.getParameter("title");
        String ctnt = req.getParameter("ctnt");


        BoardVO vo = new BoardVO();

        vo.setTitle(title);
        vo.setCtnt(ctnt);

        int result = BoardDAO.upBoard(vo);
        switch (result) {
            case 1:
                res.sendRedirect("/board/list");
                break;
            case 0:
                res.sendRedirect("/board/update");
                break;
        }
    }
}
