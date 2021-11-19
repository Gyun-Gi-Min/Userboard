package com.koreait.board2;

import com.koreait.board2.model.UserVO;
import com.koreait.board2.user.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyUtils {

    public static void disForward(HttpServletRequest req, HttpServletResponse res,String jsp) throws ServletException, IOException {
        String path ="/WEB-INF/view/"+ jsp + ".jsp";
        req.getRequestDispatcher(path).forward(req,res);
    }


    //str = "12" > 12
    //str = "1a2" > defVal
    public static int parseStringToInt(String str){
        return parseStringToInt(str,0);
    }

    public static int parseStringToInt(String str,int defVal){
        try{
            return Integer.parseInt(str);
        }catch (Exception e){}
            return defVal;

    }

    public static int getParameterInt(HttpServletRequest req,String abc,int defVal){
        return parseStringToInt(req.getParameter(abc),defVal);
    }


    public static int getParameterInt(HttpServletRequest req,String abc){
        /*
        String strVal = req.getParameter(abc);
        int intVal = parseStringToInt(strVal);
        return intVal;
        */
        return  parseStringToInt(req.getParameter(abc));

    }


    //로그인 했으면 return pk값, 로그아웃이면 return 0;
    public static int getLoginUserPk(HttpServletRequest req){
        UserVO loginUser = getLoginUser(req);
        return loginUser == null ? 0 : loginUser.getIuser();
        }

    //로그인 했으면 return true ; 되어있으면 UserVO 객체주소값
    public static boolean isLogin(HttpServletRequest req){
            return getLoginUser(req) !=null;
    }

    public static boolean isLogout(HttpServletRequest req){
        return getLoginUser(req) == null;
    }

    //로그인 >> UserVO 객체 주소값  안되어있다 ? >> null
    public static UserVO getLoginUser(HttpServletRequest req){

        HttpSession session = req.getSession();
       return (UserVO) session.getAttribute("loginUser");
    }





}
