package com.koreait.board2.board;

import com.koreait.board2.DbUtils;
import com.koreait.board2.model.BoardVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    public static int insBoard(BoardVO param){
        Connection con =null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO t_board (title,ctnt,writer) VALUES (?,?,?) ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1, param.getTitle());
            ps.setString(2,param.getCtnt());
            ps.setInt(3,param.getWriter());
            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }return 0;


    }

    public static List<BoardVO> selList(){
        List<BoardVO> list = new ArrayList();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT A.iboard, A.title, A.rdt, B.nm AS writerNm "+
                " FROM t_board A " +
                " INNER JOIN t_user B " +
                " ON A.writer = B.iuser " +
                " ORDER BY iboard DESC ";

        try {
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                BoardVO vo = new BoardVO();
                vo.setIboard(rs.getInt("iboard"));
                vo.setTitle(rs.getString("title"));
                vo.setRdt(rs.getString("rdt"));
                vo.setWriterNm(rs.getString("writerNm"));
                list.add(vo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps,rs);
        }
        return list;


    }

    public static BoardVO selDetail(BoardVO param){

       Connection con =null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       String sql = "SELECT A.iboard, A .title, A.writer, B.nm AS writerNm, A.rdt, A.ctnt "+
               " FROM t_board A " +
               " INNER JOIN t_user B " +
               " ON A.writer = B.iuser " +
               " WHERE iboard = ? ";


       try{
           con = DbUtils.getCon();
           ps = con.prepareStatement(sql);
           ps.setInt(1,param.getIboard());
           rs = ps.executeQuery();
           if(rs.next()){
               BoardVO vo = new BoardVO();
               vo.setIboard(param.getIboard());
               vo.setTitle(rs.getString("title"));
               vo.setWriter(rs.getInt("writer"));
               vo.setWriterNm(rs.getString("writerNm"));
               vo.setRdt(rs.getString("rdt"));
               vo.setCtnt(rs.getString("ctnt"));
               return vo;
           }

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           DbUtils.close(con,ps,rs);
       }return null;

    }
    public static int upBoard(BoardVO param){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_board SET title=? , ctnt=? WHERE iboard =? ";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setString(1,param.getTitle());
            ps.setString(2,param.getCtnt());
            ps.setInt(3,param.getIboard());
            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        } return 0;

    }

    public static int delBoard(BoardVO param){

        Connection con = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM t_board WHERE iboard = ? AND writer = ?";

        try{
            con = DbUtils.getCon();
            ps = con.prepareStatement(sql);
            ps.setInt(1,param.getIboard());
            ps.setInt(2,param.getWriter());
            return ps.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtils.close(con,ps);
        }return 0;



    }






}
