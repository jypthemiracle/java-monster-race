package view;

import java.util.Scanner;

public class InputView {

  public static int getCandidatesNum(Scanner sc) {
    System.out.println("몇 명의 몬스터가 경주하나요?");
    return sc.nextInt();
  }

  public static String getCandidatesNames(Scanner sc) {
    System.out.println("경주할 몬스터 이름과 종류를 입력하세요 (쉼표(,)를 기준으로 구분).");
    return sc.nextLine();
  }

  public static int getAttemptNum(Scanner sc){
    System.out.println("시도할 횟수는 몇 번인가요?");
    return sc.nextInt();
  }
}