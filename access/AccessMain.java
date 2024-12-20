package io.mobile.finalproject.access;

import io.mobile.finalproject.game.Game;
import io.mobile.finalproject.game.GameService; // GameService도 함께 추가
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AccessMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Access 추가
        System.out.println("Access 추가:");
        System.out.println("다음 형식으로 Access 정보를 입력하세요: 회원ID,서버ID,닉네임,가입날짜(YYYY-MM-DD),등급,게임머니");
        System.out.print("입력: ");
        String input = scanner.nextLine();
        String[] accessData = input.split(",");

        if (accessData.length == 6) {
            String memberId = accessData[0].trim();
            String serverId = accessData[1].trim();
            String nickname = accessData[2].trim();
            Date joinDate = Date.valueOf(accessData[3].trim());
            String grade = accessData[4].trim();
            int gameMoney = Integer.parseInt(accessData[5].trim());

            // 서버의 현재 접속 회원 수 확인
            Game server = GameService.selectById(serverId);
            if (server != null && server.getConnCount() >= 110) {
                System.out.println("서버 " + serverId + "의 접속 회원 수가 110명을 초과하여 접속할 수 없습니다.");
            } else {
                int result = AccessService.insert(memberId, serverId, nickname, joinDate, grade, gameMoney);
                System.out.println(result > 0 ? "Access 추가 성공!" : "Access 추가 실패!");
            }
        } else {
            System.out.println("잘못된 입력 형식입니다. 다시 시도하세요.");
        }

//        // 2. 특정 Access 조회
//        System.out.println("\n특정 Access 조회:");
//        Access access = AccessService.selectByMemberAndServer("009", "서버1");
//        System.out.println(access != null ? access : "Access 정보 없음");

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
