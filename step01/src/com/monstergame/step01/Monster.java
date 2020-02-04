package com.monstergame.step01;
import com.monstergame.step01.MonsterInput;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Monster {

  public static void main(String[] args) {
    MonsterInput monsterInput = new MonsterInput();
    int monsterN = monsterInput.monsterNum();
    int moveN = monsterInput.moveNum();
    playGame(monsterN, moveN);
  }

  public static void playGame(int monsterN, int moveN) {
    //input your code here
  }
}
