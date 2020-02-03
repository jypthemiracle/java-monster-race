package com.monstertest.step01;

import com.monstergame.step01.Monster;

import com.monstergame.step01.MonsterInput;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MonsterInputTest {

  Monster monster = new Monster();
  MonsterInput asker = mock(MonsterInput.class);

  @Test
  public void monsterNumTest() {
    when(asker.monsterNum()).thenReturn(3);
    assertEquals(3, asker.monsterNum());
  }

  @Test
  public void moveNumTest() {
    when(asker.moveNum()).thenReturn(5);
    assertEquals(5, asker.moveNum());
  }
}