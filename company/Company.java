package io.mobile.finalproject.company;

public class Company {
    private String companyName;
    private String location;
    private String ceoName;
    private String phoneNumber;

    // 생성자
    public Company(String companyName, String location, String ceoName, String phoneNumber) {
        this.companyName = companyName;
        this.location = location;
        this.ceoName = ceoName;
        this.phoneNumber = phoneNumber;
    }

    // Getter and Setter
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", location='" + location + '\'' +
                ", ceoName='" + ceoName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

