package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import model.Flier;
import model.Psychic;
import model.Runner;
import utils.DragonUtils;
import view.InputView;
import view.OutputView;

public class Controller {

  protected static InputView inputview = new InputView();
  protected static OutputView outputview = new OutputView();
  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;
  private Scanner scanner;

  public void startController() throws IOException {
    System.out.println("재미없는 몬스터 게임 경주가 시작했어요.");
    createScanner();

    System.out.println("몇 명의 몬스터가 경주하나요?");
    int num = getMonstersNum();

    System.out.println("경주할 몬스터 이름과 종류를 입력하세요 (쉼표(,)를 기준으로 구분).");
    for (int index = 0; index < num; index ++){
      getCandidatesType();
    }

    System.out.println("시도할 횟수는 몇 번인가요?");
    int attemptTry = getAttemptTry();
    controllMove(attemptTry);

    doActualMove();
    printMove();
  }

  public void createScanner() {
    this.scanner = new Scanner(System.in);
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
  }

  private int getMonstersNum() throws IOException {
    return inputview.getMonstersNum(bufferedReader.readLine());
  }

  private void getCandidatesType() throws IOException {
    String[] input = inputview.getMonstersName(bufferedReader.readLine());
    String name = input[0];
    String type = input[1].trim();
    if (getCandidatesTypeInvalidNameCheck(type)) {
      createMonstersType(name, type);
      return;
    }
    bufferedWriter.write("다시 입력해주세요.");
    bufferedWriter.flush();
    //int MonstersNum을 파라미터로 해서 원래는 재귀함수로 만들고 싶었음..
  }

  private boolean getCandidatesTypeInvalidNameCheck(String type) {
    return type.equals("달리기") || type.equals("비행") || type.equals("에스퍼");
  }

  private void createMonstersType(String name, String type) {
    switch (type) {
      case "달리기":
        Database.monstersData.add(new Runner(name));
        break;
      case "비행":
        Database.monstersData.add(new Flier(name));
        break;
      case "에스퍼":
        Database.monstersData.add(new Psychic(name));
        break;
    }
  }

  private int getAttemptTry() throws IOException {
    int attemptCount = inputview.getAttemptNum(bufferedReader.readLine());
    if (attemptCount <= 0) {
      throw new IllegalArgumentException("잘못 입력하셨습니다.");
    }
    return attemptCount;
  }

  private void controllMove(int attemptCount) {
    for (int currentCount = 0; currentCount < attemptCount; currentCount++) {
      doActualMove();
    }
  }

  private void doActualMove() {
    for (int currentIndex = 0; currentIndex < Database.monstersData.size(); currentIndex++) {
      //interface로 선언하면 implements로 구현한 메소드는 어떻게 접근해야 할까?
      Database.monstersData.get(currentIndex)
          .updateResults(DragonUtils.getRandomNumber());
    }
  }

  private void printMove() {
    outputview.printOutput(Database.monstersData);
  }
}