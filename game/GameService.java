package io.mobile.finalproject.game;

import io.mobile.finalproject.conf.Conf;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameService {

    private static Game setGame(ResultSet rs) throws SQLException {
        String serverId = rs.getString("server_id");
        int connCount = rs.getInt("conn_count");
        String companyName = rs.getString("company_name");
        java.sql.Date inspectionDate = rs.getDate("inspection_date");
        int maintenanceCost = rs.getInt("maintenance_cost");

        return new Game(serverId, connCount, companyName, inspectionDate, maintenanceCost);
    }

    public static List<Game> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Game> gameList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM game";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Game game = setGame(rs);
                gameList.add(game);
            }
            return gameList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    public static Game selectById(String serverId) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM game WHERE server_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, serverId);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                return setGame(rs);
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

    public static int insert(String serverId, int connCount, String companyName, java.sql.Date inspectionDate, int maintenanceCost) {
        String insertQuery = "INSERT INTO game (server_id, conn_count, company_name, inspection_date, maintenance_cost) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(insertQuery)) {
            psmt.setString(1, serverId);
            psmt.setInt(2, connCount);
            psmt.setString(3, companyName);
            psmt.setDate(4, inspectionDate);
            psmt.setInt(5, maintenanceCost);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int update(String serverId, int connCount, String companyName, java.sql.Date inspectionDate, int maintenanceCost) {
        String updateQuery = "UPDATE game SET conn_count = ?, company_name = ?, inspection_date = ?, maintenance_cost = ? WHERE server_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(updateQuery)) {
            psmt.setInt(1, connCount);
            psmt.setString(2, companyName);
            psmt.setDate(3, inspectionDate);
            psmt.setInt(4, maintenanceCost);
            psmt.setString(5, serverId);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int delete(String serverId) {
        String deleteQuery = "DELETE FROM game WHERE server_id = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(deleteQuery)) {
            psmt.setString(1, serverId);
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
