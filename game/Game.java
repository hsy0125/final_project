package io.mobile.finalproject.game;

public class Game {
    private String serverId;
    private int connCount;
    private String companyName;
    private java.sql.Date inspectionDate;
    private int maintenanceCost;

    public Game(String serverId, int connCount, String companyName, java.sql.Date inspectionDate, int maintenanceCost) {
        this.serverId = serverId;
        this.connCount = connCount;
        this.companyName = companyName;
        this.inspectionDate = inspectionDate;
        this.maintenanceCost = maintenanceCost;
    }

    // Getters and Setters
    public String getServerId() { return serverId; }
    public void setServerId(String serverId) { this.serverId = serverId; }
    public int getConnCount() { return connCount; }
    public void setConnCount(int connCount) { this.connCount = connCount; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public java.sql.Date getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(java.sql.Date inspectionDate) { this.inspectionDate = inspectionDate; }
    public int getMaintenanceCost() { return maintenanceCost; }
    public void setMaintenanceCost(int maintenanceCost) { this.maintenanceCost = maintenanceCost; }

    @Override
    public String toString() {
        return "Game{" +
                "serverId='" + serverId + '\'' +
                ", connCount=" + connCount +
                ", companyName='" + companyName + '\'' +
                ", inspectionDate=" + inspectionDate +
                ", maintenanceCost=" + maintenanceCost +
                '}';
    }
}