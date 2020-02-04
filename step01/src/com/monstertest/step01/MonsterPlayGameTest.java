package com.monstertest.step01;

import static org.mockito.Mockito.mock;

import com.monstergame.step01.Monster;
import com.monstergame.step01.MonsterInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Test;

public class MonsterPlayGameTest {

  Monster monster = new Monster();
  static MonsterInput asker = mock(MonsterInput.class);
  private PrintStream sysOut;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  int monsterN = 3;
  int moveN = 5;

  @Test
  public void playGameTest(){
    StringBuilder result = Monster.playGame(monsterN, moveN);
    Assert.assertTrue(result.toString().contains("-"));
  }
}