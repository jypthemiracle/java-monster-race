package com.monstertest.step02;

import com.monstergame.step02.Monster;

import com.monstergame.step02.MonsterInput;
import java.io.IOException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MonsterInputTest {

  Monster monster = new Monster();
  MonsterInput asker = mock(MonsterInput.class);

  @Test
  public void monsterNumTest() throws IOException {
    when(asker.monsterNum()).thenReturn(3);
    assertEquals(3, asker.monsterNum());
  }

  @Test
  public void moveNumTest() throws IOException {
    when(asker.moveNum()).thenReturn(5);
    assertEquals(5, asker.moveNum());
  }
}