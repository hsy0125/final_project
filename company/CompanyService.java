package io.mobile.finalproject.company;

import io.mobile.finalproject.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyService {

    // ResultSet을 통해 Company 객체를 설정하는 메서드
    private static Company setCompany(ResultSet rs) throws SQLException {
        String companyName = rs.getString("company_name");
        String location = rs.getString("location");
        String ceoName = rs.getString("ceo_name");
        String phoneNumber = rs.getString("phoneNumber");

        return new Company(companyName, location, ceoName, phoneNumber);
    }

    // 모든 회사 조회
    public static List<Company> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Company> companyList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM company";
            psmtQuery = conn.prepareStatement(query);
            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Company company = setCompany(rs);
                companyList.add(company);
            }
            return companyList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    // 회사명으로 특정 회사 조회
    public static Company selectByName(String companyName) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM company WHERE company_name = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, companyName);
            rs = psmtQuery.executeQuery();

            if (rs.next()) {
                return setCompany(rs);
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

    // 회사 삽입
    public static int insert(String companyName, String location, String ceoName, String phoneNumber) {
        String insertQuery = "INSERT INTO company (company_name, location, ceo_name, phoneNumber) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(insertQuery)) {
            psmt.setString(1, companyName);
            psmt.setString(2, location);
            psmt.setString(3, ceoName);
            psmt.setString(4, phoneNumber);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 회사 수정
    public static int update(String companyName, String location, String ceoName, String phoneNumber) {
        String updateQuery = "UPDATE company SET location = ?, ceo_name = ?, phoneNumber = ? WHERE company_name = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(updateQuery)) {
            psmt.setString(1, location);
            psmt.setString(2, ceoName);
            psmt.setString(3, phoneNumber);
            psmt.setString(4, companyName);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 회사 삭제
    public static int delete(String companyName) {
        String deleteQuery = "DELETE FROM company WHERE company_name = ?";
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD);
             PreparedStatement psmt = conn.prepareStatement(deleteQuery)) {
            psmt.setString(1, companyName);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Resource 종료 메서드
    private static void closeResources(PreparedStatement psmtQuery, ResultSet rs) {
        try {
            if (psmtQuery != null) psmtQuery.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
