package com.monstertest.step01;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.monstergame.step01.Monster;
import com.monstergame.step01.MonsterInput;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;

public class MonsterPlayGameTest {

  Monster monster = new Monster();
  static MonsterInput asker = mock(MonsterInput.class);
  private PrintStream sysOut;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  int monsterN = 0;
  int moveN = 0;

  @BeforeClass
  public static void mockMonsterInput() {
    OngoingStubbing<Integer> monsterN = when(asker.monsterNum()).thenReturn(3);
    OngoingStubbing<Integer> moveN = when(asker.moveNum()).thenReturn(5);
  }

  @Before
  public void setUpStreams(){
    sysOut = System.out;
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void revertStreams(){
    System.setOut(sysOut);
  }

  @Test
  public void playGameTest(){
    Monster.playGame(monsterN, moveN);
    String expectedOutput = ("---\n"
        + "-----------\n"
        + "------");
    assertEquals(expectedOutput, outContent.toString());
  }
}

//  @Test
//  public void playGameTest() {
//    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    //When you do, System.setOut(OutputStream), whatever the application writes to the console (using System.out.printX()) statements, instead get written to the outputStream you pass.
//    System.setOut(new PrintStream(outContent));
//    monster.playGame(monsterN, moveN);
//    String expectedOutput = ("---\n"
//        + "-----------\n"
//        + "------");
//    assertEquals(expectedOutput, outContent.toString());
//  }
