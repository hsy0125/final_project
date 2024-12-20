package io.mobile.finalproject.company;

import java.util.List;
import java.util.Scanner;

public class CompanyMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 새로운 회사 추가 여부 확인
        System.out.println("회사를 추가하시겠습니까? (예/아니오): ");
        String addAnswer = scanner.nextLine();
        if (addAnswer.equalsIgnoreCase("예")) {
            System.out.println("새로운 회사 추가:");
            System.out.println("다음 형식으로 회사 정보를 입력하세요: 회사명,위치,대표자명,전화번호");
            System.out.print("입력: ");
            String input = scanner.nextLine();
            String[] companyData = input.split(",");

            if (companyData.length == 4) {
                String companyName = companyData[0].trim();
                String location = companyData[1].trim();
                String ceoName = companyData[2].trim();
                String phoneNumber = companyData[3].trim();

                int result = CompanyService.insert(companyName, location, ceoName, phoneNumber);
                System.out.println(result > 0 ? "회사가 성공적으로 추가되었습니다." : "회사가 추가되지 않았습니다.");
            } else {
                System.out.println("잘못된 입력 형식입니다. 다시 시도하세요.");
            }
        } else {
            System.out.println("회사 추가를 건너뜁니다.");
        }

        // 2. 회사 정보 조회 (특정 회사 조회)
        System.out.println("\n2. 회사 정보 조회:");
        Company company = CompanyService.selectByName("한밭테크");
        if (company != null) {
            System.out.println("회사를 찾았습니다: " + company);
        } else {
            System.out.println("회사를 찾을 수 없습니다.");
        }

        // 3. 모든 회사 정보 조회
        System.out.println("\n3. 모든 회사 정보 조회:");
        List<Company> companyList = CompanyService.selectAll();
        if (companyList != null && !companyList.isEmpty()) {
            for (Company c : companyList) {
                System.out.println(c);
            }
        } else {
            System.out.println("등록된 회사가 없습니다.");
        }

        // 4. 회사 정보 수정 여부 확인
        System.out.println("\n회사 정보를 수정하시겠습니까? (예/아니오): ");
        String updateAnswer = scanner.nextLine();
        if (updateAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n회사 정보 수정:");
            System.out.print("수정할 회사명: ");
            String updateName = scanner.nextLine();
            System.out.print("새 위치: ");
            String newLocation = scanner.nextLine();
            System.out.print("새 대표자명: ");
            String newCeoName = scanner.nextLine();
            System.out.print("새 전화번호: ");
            String newPhoneNumber = scanner.nextLine();

            int updateResult = CompanyService.update(updateName, newLocation, newCeoName, newPhoneNumber);
            System.out.println(updateResult > 0 ? "회사가 성공적으로 수정되었습니다." : "회사가 수정되지 않았습니다.");
        } else {
            System.out.println("회사 정보 수정을 건너뜁니다.");
        }

        // 5. 회사 삭제 여부 확인
        System.out.println("\n회사정보를 삭제하시겠습니까? (예/아니오): ");
        String deleteAnswer = scanner.nextLine();
        if (deleteAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n회사 삭제:");
            System.out.print("삭제할 회사명: ");
            String deleteName = scanner.nextLine();

            int deleteResult = CompanyService.delete(deleteName);
            System.out.println(deleteResult > 0 ? "회사정보가 성공적으로 삭제되었습니다." : "회사가 삭제되지 않았습니다.");
        } else {
            System.out.println("회사정보 삭제를 건너뜁니다.");
        }

        scanner.close();
    }
}
