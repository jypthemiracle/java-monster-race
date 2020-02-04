package com.monstergame.step01;
import java.io.IOException;
import java.util.Random;

public class Monster {

  public static void main(String[] args) throws IOException {
    System.out.println("재미없는 몬스터 게임에 어서오세요.");
    MonsterInput monsterInput = new MonsterInput();
    int monsterN = monsterInput.monsterNum();
    int moveN = monsterInput.moveNum();
    playGame(monsterN, moveN);
  }

  public static StringBuilder playGame(int monsterN, int moveN) {
    Random random = new Random();
    int randomNum = 0;
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < monsterN; i++){
      for (int j = 0; j < moveN; j++){
        randomNum = random.nextInt(10);
        if (randomNum >= 4){
          result.append("-");
        }
      }
      result.append("\n");
    }
    System.out.println(result);
    return result;
  }
}
