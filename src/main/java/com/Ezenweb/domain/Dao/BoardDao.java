package com.Ezenweb.domain.Dao;

import com.Ezenweb.domain.Dto.BoardDto;

import java.sql.*;

public class BoardDao {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public  BoardDao(){
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/springweb",
                    "root",
                    "1234");
        }catch (Exception e) {System.out.println(e);}
    }
    // 1. 게시물 등록 SQL
    public boolean setboard(BoardDto boardDto) {
        String sql = "insert into board (btitle , bcontent) values (? , ? )";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBcontent());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {System.out.println(e);} return false;
    }
}