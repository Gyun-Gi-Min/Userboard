package com.koreait.board2.user;

import com.koreait.board2.DbUtils;
import com.koreait.board2.model.UserVO;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDAO {

    //회원가입 성공 > 1, 실패 > 0
    public static int join(UserVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = " INSERT INTO t_user (uid, upw, nm, gender) "
                + " VALUES (?, ?, ?, ?) ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            ps.setString(2, param.getUpw());
            ps.setString(3, param.getNm());
            ps.setInt(4, param.getGender());
            return  ps.executeUpdate(); //영향 받은행 :1  성공!
            } catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }return 0;  //실패



    }

    public static int login(UserVO param){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql= "SELECT iuser, upw, nm FROM t_user WHERE uid = ? ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getUid());
            rs = ps.executeQuery();
            if(rs.next()){
                String dbUpw = rs.getString("upw");
                if(dbUpw.equals(param.getUpw())){
                    param.setIuser(rs.getInt("iuser"));
                    param.setNm(rs.getString("nm"));
                    return 1; //로그인 성공
                } else{
                    return 3; //비번틀림
                }
            }
            else {
                return 2; //아이디없음
            }
        } catch (Exception e){
                e.printStackTrace();
        } finally {
          DbUtils.close(con,ps,rs);
        }
        return 0; //에러
    }



}
