package io.mobile.finalproject.access;

import io.mobile.finalproject.conf.Conf;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class AccessService {

    private static Access setAccess(ResultSet rs) throws SQLException {
        String memberId = rs.getString("member_id");
        String serverId = rs.getString("server_id");
        String nickname = rs.getString("nickname");
        Date joinDate = rs.getDate("join_date");
        String grade = calculateGrade(joinDate.toLocalDate());  // 등급 자동 계산
        int gameMoney = rs.getInt("game_money");

        return new Access(memberId, serverId, nickname, joinDate, grade, gameMoney);
    }

    // 등급 계산 로직
    private static String calculateGrade(LocalDate joinDate) {
        long years = ChronoUnit.YEARS.between(joinDate, LocalDate.now());
        if (years < 1) {
            return "브론즈";
        } else if (years >= 1 && years < 5) {
            return "실버";
        } else if (years >= 10) {
            return "플래티넘";
        } else {
            return "골드";
        }
    }

    public static List<Access> selectAll() {
        String query = "SELECT * FROM access";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query);
             ResultSet rs = psmt.executeQuery()) {
            List<Access> accesses = new ArrayList<>();
            while (rs.next()) {
                accesses.add(setAccess(rs));
            }
            return accesses;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Access selectByMemberAndServer(String memberId, String serverId) {
        String query = "SELECT * FROM access WHERE member_id = ? AND server_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, memberId);
            psmt.setString(2, serverId);
            try (ResultSet rs = psmt.executeQuery()) {
                if (rs.next()) {
                    return setAccess(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int insert(String memberId, String serverId, String nickname, Date joinDate, String grade, int gameMoney) {
        String query = "INSERT INTO access (member_id, server_id, nickname, join_date, grade, game_money) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, memberId);
            psmt.setString(2, serverId);
            psmt.setString(3, nickname);
            psmt.setDate(4, joinDate);
            psmt.setString(5, calculateGrade(joinDate.toLocalDate()));  // 자동 계산된 등급 삽입
            psmt.setInt(6, gameMoney);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int update(String memberId, String serverId, String nickname, String grade, int gameMoney) {
        String query = "UPDATE access SET nickname = ?, grade = ?, game_money = ? WHERE member_id = ? AND server_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, nickname);
            psmt.setString(2, grade);  // 등급 추가
            psmt.setInt(3, gameMoney);
            psmt.setString(4, memberId);
            psmt.setString(5, serverId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static int delete(String memberId, String serverId) {
        String query = "DELETE FROM access WHERE member_id = ? AND server_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(query)) {
            psmt.setString(1, memberId);
            psmt.setString(2, serverId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
