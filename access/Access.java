package io.mobile.finalproject.access;

import java.sql.Date;

public class Access {
    private String memberId;
    private String serverId;
    private String nickname;
    private Date joinDate;
    private String grade;
    private int gameMoney;

    public Access(String memberId, String serverId, String nickname, Date joinDate, String grade, int gameMoney) {
        this.memberId = memberId;
        this.serverId = serverId;
        this.nickname = nickname;
        this.joinDate = joinDate;
        this.grade = grade;
        this.gameMoney = gameMoney;
    }

    // Getters and Setters
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public String getServerId() { return serverId; }
    public void setServerId(String serverId) { this.serverId = serverId; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public int getGameMoney() { return gameMoney; }
    public void setGameMoney(int gameMoney) { this.gameMoney = gameMoney; }

    @Override
    public String toString() {
        return "Access{" +
                "memberId='" + memberId + '\'' +
                ", serverId='" + serverId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", joinDate=" + joinDate +
                ", grade='" + grade + '\'' +
                ", gameMoney=" + gameMoney +
                '}';
    }
}
