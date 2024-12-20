package io.mobile.finalproject.access;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AccessMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Access 추가
        System.out.println("Access 추가:");
        int result = AccessService.insert("009", "서버1", "PlayerOne", Date.valueOf("2022-07-11"), null, 500);
        System.out.println(result > 0 ? "추가 성공!" : "추가 실패!");

        // 2. 특정 Access 조회
        System.out.println("\n특정 Access 조회:");
        Access access = AccessService.selectByMemberAndServer("009", "서버1");
        System.out.println(access != null ? access : "Access 정보 없음");

        // 3. 모든 Access 조회
        System.out.println("\n모든 Access 조회:");
        List<Access> accesses = AccessService.selectAll();
        accesses.forEach(System.out::println);

        // 4. Access 정보 수정 여부 확인
        System.out.println("\nAccess 정보를 수정하시겠습니까? (예/아니오): ");
        String updateAnswer = scanner.nextLine();
        if (updateAnswer.equalsIgnoreCase("예")) {
            System.out.println("\nAccess 정보 수정:");
            System.out.print("수정할 회원 ID: ");
            String updateMemberId = scanner.nextLine();
            System.out.print("수정할 서버 ID: ");
            String updateServerId = scanner.nextLine();
            System.out.print("새 닉네임: ");
            String newNickname = scanner.nextLine();
            System.out.print("새 등급: ");
            String newGrade = scanner.nextLine();
            System.out.print("새 게임머니: ");
            int newGameMoney = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            int updateResult = AccessService.update(updateMemberId, updateServerId, newNickname, newGrade, newGameMoney);
            System.out.println(updateResult > 0 ? "수정 성공!" : "수정 실패!");
        } else {
            System.out.println("Access 수정을 건너뜁니다.");
        }

        // 5. Access 삭제 여부 확인
        System.out.println("\nAccess 정보를 삭제하시겠습니까? (예/아니오): ");
        String deleteAnswer = scanner.nextLine();
        if (deleteAnswer.equalsIgnoreCase("예")) {
            System.out.println("\nAccess 삭제:");
            System.out.print("삭제할 회원 ID: ");
            String deleteMemberId = scanner.nextLine();
            System.out.print("삭제할 서버명: ");
            String deleteServerId = scanner.nextLine();

            int deleteResult = AccessService.delete(deleteMemberId, deleteServerId);
            System.out.println(deleteResult > 0 ? "삭제 성공!" : "삭제 실패!");
        } else {
            System.out.println("Access 삭제를 건너뜁니다.");
        }

        scanner.close();
    }
}
