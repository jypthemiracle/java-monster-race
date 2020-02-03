package com.monstergame.step01;

//import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MonsterInput {
  private Scanner scanner;
  private PrintStream out;
  //private InputStream in;

  public int monsterNum(){
    out.println("몬스터는 모두 몇 마리인가요?");
    return scanner.nextInt();
  }

  public int moveNum(){
    out.println("시도할 회수는 몇 회인가요?");
    return scanner.nextInt();
  }
}