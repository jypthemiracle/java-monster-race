package com.monstergame.step02;
import java.io.IOException;
import java.util.Random;

public class Monster {
  static final int RANDOM_NUM_BOUND = 10;
  static final int RANDOM_RACE_THRESHOLD = 4;
  static StringBuilder result = new StringBuilder();

  public static void main(String[] args) throws IOException {
    System.out.println("재미없는 몬스터 게임에 어서오세요.");
    MonsterInput monsterInput = new MonsterInput();
    int monsterN = monsterInput.monsterNum();
    int moveN = monsterInput.moveNum();
    playGame(monsterN, moveN);
  }

  public static void raceEachMonster(int moveN, int[] monsters, int nowIndex){
    int runLength;
    for (int nowMoveLength = 0; nowMoveLength < moveN; nowMoveLength++){
      runLength = (int) (Math.random() * RANDOM_NUM_BOUND);
      if (runLength >= RANDOM_RACE_THRESHOLD){
        result.append("-");
        monsters[nowIndex] = runLength;
      }
    }
    result.append("\n");
  }

  public static StringBuilder playGame(int monsterN, int moveN) {
    int[] monsters = new int[monsterN];
    for (int nowIndex = 0; nowIndex < monsterN; nowIndex++){
      raceEachMonster(moveN, monsters, nowIndex);
    }
    System.out.println(result);
    return result;
  }
}