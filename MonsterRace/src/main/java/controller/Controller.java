package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Flier;
import model.Monsters;
import model.Psychic;
import model.Runner;
import utils.DragonUtils;
import utils.Log;
import view.InputView;
import view.OutputView;

public class Controller {

  protected static InputView inputview = new InputView();
  protected static OutputView outputview = new OutputView();

  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;
  private Scanner scanner;

  public Controller()
      throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    System.out.println("재미없는 몬스터 게임 경주가 시작했어요.");
    createScanner();
    loadGame(inputview.getMonstersMenu(bufferedReader.readLine()));
  }

  public void loadGame(int userLoadChoose)
      throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    switch (userLoadChoose) {
      case 1:
        startGame();
      case 2:
        loadGameCRUDMenu(inputview.getMonstersMenu(bufferedReader.readLine()));
      default:
        System.out.println("THE NUMBER IS NOT VALID!");
        break;
    }
  }

  public void loadGameCRUDMenu(int userLoadChoose)
      throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String[] getInput = inputview.modifyMonsterNameInput(bufferedReader.readLine());
    String deleteName = inputview.deleteMonsterNameInput(bufferedReader.readLine());
    int getMenuChoice = inputview.getMonstersMenu(bufferedReader.readLine());

    switch (userLoadChoose) {
      case 1:
        showAllMonstersInfo();
        break;
      case 2:
        modifyMonstersInfo(getInput);
        break;
      case 3:
        addNewMonstersInfo();
        break;
      case 4:
        deleteMonstersInfo(deleteName);
        break;
      case 5:
        loadGame(getMenuChoice);
        break;
    }
  }

  public void showAllMonstersInfo() {
    //input your code here
  }

  public void deleteMonstersInfo(String deleteName) throws IndexOutOfBoundsException {
    if (Database.monstersData.isEmpty()) {
      throw new IndexOutOfBoundsException();
    }
    Database.monstersData.removeIf(monstersDatum -> deleteName.equals(monstersDatum.getName()));
  }

  public void addNewMonstersInfo() throws IOException {
    createMonstersType(getCandidatesType());
  }

  public void modifyMonstersInfo(String[] modifyInfo)
      throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
      InvocationTargetException, InstantiationException {

    String beforeName = modifyInfo[0];
    String afterName = modifyInfo[1];
    String afterType = modifyInfo[2];

    for (Monsters monstersDatum : Database.monstersData) {
      if (beforeName.equals(monstersDatum.getName())) {
        Object newClass = Class.forName(afterType).getDeclaredConstructor().newInstance();
        ((Monsters) newClass).setName(afterName);
        Database.monstersData.add((Monsters) newClass);
      }
      Database.monstersData.remove(monstersDatum);
    }
  }

  public void startGame() throws IOException {
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

  private String[] getCandidatesType() throws IOException {
    String[] input = inputview.getMonstersName(bufferedReader.readLine());
    //String name = input[0];
    String type = input[1].trim();
    if (!getCandidatesTypeInvalidNameCheck(type)) {
      throw new IllegalArgumentException();
    }
    return input;
  }

  private boolean getCandidatesTypeInvalidNameCheck(String type) {
    return type.equals("달리기") || type.equals("비행") || type.equals("에스퍼");
  }

  private void createMonstersType(String[] input) {
    String name = input[0];
    String type = input[1].trim();

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

  public void printMonsterLog() {
    if (Database.monstersData.size() < 1) {
      throw new NoSuchElementException("no monsters viable.");
    }
    Log log = Log.getInstance();
    Database.monstersData.stream()
        .forEach(monster -> log.write(monster.getName(), monster.getClass().toString()));
    //자바 람다 학습.. https://madplay.github.io/post/java-streams-intermediate-operations
  }
}