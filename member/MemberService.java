package io.mobile.finalproject.member;

import io.mobile.finalproject.conf.Conf;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {

    private static Member setMember(ResultSet rs) throws SQLException {
        String memberId = rs.getString("member_id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String email = rs.getString("email");
        String phoneNumber = rs.getString("phonenum");

        return new Member(memberId, password, name, age, email, phoneNumber);
    }

    public static List<Member> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Member> memberList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM member";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Member member = setMember(rs);
                memberList.add(member);
            }
            return memberList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    public static Member selectById(String memberId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM member WHERE member_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, memberId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                return setMember(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    public static int insert(String memberId, String password, String name, int age, String email, String phoneNumber) {
        String insertQuery = "INSERT INTO member (member_id, password, name, age, email, phonenum) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(insertQuery)) {
            psmt.setString(1, memberId);
            psmt.setString(2, password);
            psmt.setString(3, name);
            psmt.setInt(4, age);
            psmt.setString(5, email);
            psmt.setString(6, phoneNumber);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int update(String memberId, String password, String name, int age, String email, String phoneNumber) {
        String updateQuery = "UPDATE member SET password = ?, name = ?, age = ?, email = ?, phonenum = ? WHERE member_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(updateQuery)) {
            psmt.setString(1, password);
            psmt.setString(2, name);
            psmt.setInt(3, age);
            psmt.setString(4, email);
            psmt.setString(5, phoneNumber);
            psmt.setString(6, memberId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int delete(String memberId) {
        String deleteQuery = "DELETE FROM member WHERE member_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(deleteQuery)) {
            psmt.setString(1, memberId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void closeResources(PreparedStatement psmtQuery, ResultSet rs) {
        try {
            if (psmtQuery != null) psmtQuery.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
