package com.monstergame.step02;

import java.util.Scanner;

public class MonsterInput {
  Scanner scanner = new Scanner(System.in);
  //private PrintStream out;
  //private InputStream in;

  public int monsterNum(){
    System.out.println("몬스터는 모두 몇 마리인가요?");
    return scanner.nextInt();
  }

  public int moveNum(){
    System.out.println("시도할 회수는 몇 회인가요?");
    return scanner.nextInt();
  }
}