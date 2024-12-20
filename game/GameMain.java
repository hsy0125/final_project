package io.mobile.finalproject.game;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class GameMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 새로운 서버 추가
        System.out.println("1. 새로운 서버 추가:");
        int result = GameService.insert("서버20", 150, "한밭테크", Date.valueOf("2024-01-01"), 200000);
        System.out.println(result > 0 ? "서버가 성공적으로 추가되었습니다." : "서버 추가에 실패했습니다.");

        // 2. 특정 서버 조회
        System.out.println("\n2. 특정 서버 조회:");
        Game game = GameService.selectById("서버20");
        if (game != null) {
            System.out.println("서버를 찾았습니다: " + game);
        } else {
            System.out.println("서버를 찾을 수 없습니다.");
        }

        // 3. 모든 서버 조회
        System.out.println("\n3. 모든 서버 조회:");
        List<Game> gameList = GameService.selectAll();
        if (gameList != null && !gameList.isEmpty()) {
            for (Game g : gameList) {
                System.out.println(g);
            }
        } else {
            System.out.println("등록된 서버가 없습니다.");
        }

        // 4. 서버 정보 수정 여부 확인
        System.out.println("\n서버 정보를 수정하시겠습니까? (예/아니오): ");
        String updateAnswer = scanner.nextLine();
        if (updateAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n서버 정보 수정:");
            System.out.print("수정할 서버 ID: ");
            String updateServerId = scanner.nextLine();
            System.out.print("새 접속 회원 수: ");
            int newConnCount = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            System.out.print("새 회사명: ");
            String newCompanyName = scanner.nextLine();
            System.out.print("새 점검일자 (YYYY-MM-DD): ");
            Date newInspectionDate = Date.valueOf(scanner.nextLine());
            System.out.print("새 유지 비용: ");
            int newMaintenanceCost = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            int updateResult = GameService.update(updateServerId, newConnCount, newCompanyName, newInspectionDate, newMaintenanceCost);
            System.out.println(updateResult > 0 ? "서버 정보가 성공적으로 수정되었습니다." : "서버 정보 수정에 실패했습니다.");
        } else {
            System.out.println("서버 정보 수정을 건너뜁니다.");
        }

        // 5. 서버 삭제 여부 확인
        System.out.println("\n서버정보를 삭제하시겠습니까? (예/아니오): ");
        String deleteAnswer = scanner.nextLine();
        if (deleteAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n서버 삭제:");
            System.out.print("삭제할 서버명: ");
            String deleteServerId = scanner.nextLine();

            int deleteResult = GameService.delete(deleteServerId);
            System.out.println(deleteResult > 0 ? "서버정보가 성공적으로 삭제되었습니다." : "서버 삭제에 실패했습니다.");
        } else {
            System.out.println("서버정보 삭제를 건너뜁니다.");
        }

        scanner.close();
    }
}
