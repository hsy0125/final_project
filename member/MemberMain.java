package io.mobile.finalproject.member;

import java.util.List;
import java.util.Scanner;

public class MemberMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 회원 추가 여부 확인
        System.out.println("회원 정보를 추가하시겠습니까? (예/아니오): ");
        String addAnswer = scanner.nextLine();
        if (addAnswer.equalsIgnoreCase("예")) {
            System.out.println("회원 추가:");
            System.out.println("다음 형식으로 회원 정보를 입력하세요: 회원ID,비밀번호,이름,나이,이메일,전화번호");
            System.out.print("입력: ");
            String input = scanner.nextLine();
            String[] memberData = input.split(",");

            if (memberData.length == 6) {
                String memberId = memberData[0].trim();
                String password = memberData[1].trim();
                String name = memberData[2].trim();
                int age = Integer.parseInt(memberData[3].trim());
                String email = memberData[4].trim();
                String phoneNumber = memberData[5].trim();

                int result = MemberService.insert(memberId, password, name, age, email, phoneNumber);
                System.out.println(result > 0 ? "추가 성공!" : "추가 실패!");
            } else {
                System.out.println("잘못된 입력 형식입니다. 다시 시도하세요.");
            }
        } else {
            System.out.println("회원 추가를 건너뜁니다.");
        }

        // 2. 특정 회원 조회
        System.out.println("\n특정 회원 조회:");
        Member member = MemberService.selectById("009");
        System.out.println(member != null ? member : "회원 정보 없음");

        // 3. 모든 회원 조회
        System.out.println("\n모든 회원 조회:");
        List<Member> members = MemberService.selectAll();
        members.forEach(System.out::println);

        // 4. 회원 정보 수정 여부 확인
        System.out.println("\n정보를 수정하시겠습니까? (예/아니오): ");
        String updateAnswer = scanner.nextLine();
        if (updateAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n회원 정보 수정:");
            System.out.print("수정할 회원 ID: ");
            String updateId = scanner.nextLine();
            System.out.print("새 비밀번호: ");
            String newPassword = scanner.nextLine();
            System.out.print("새 이름: ");
            String newName = scanner.nextLine();
            System.out.print("새 나이: ");
            int newAge = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기
            System.out.print("새 이메일: ");
            String newEmail = scanner.nextLine();
            System.out.print("새 전화번호: ");
            String newPhoneNumber = scanner.nextLine();

            int updateResult = MemberService.update(updateId, newPassword, newName, newAge, newEmail, newPhoneNumber);
            System.out.println(updateResult > 0 ? "수정 성공!" : "수정 실패!");
        } else {
            System.out.println("정보 수정을 건너뜁니다.");
        }

        // 5. 회원 삭제 여부 확인
        System.out.println("\n정보를 삭제하시겠습니까? (예/아니오): ");
        String deleteAnswer = scanner.nextLine();
        if (deleteAnswer.equalsIgnoreCase("예")) {
            System.out.println("\n회원 삭제:");
            System.out.print("삭제할 회원 ID: ");
            String deleteId = scanner.nextLine();
            int deleteResult = MemberService.delete(deleteId);
            System.out.println(deleteResult > 0 ? "삭제 성공!" : "삭제 실패!");
        } else {
            System.out.println("정보 삭제를 건너뜁니다.");
        }

        scanner.close();
    }
}
