import model.Flier;
import model.Monsters;
import model.Psychic;
import model.Runner;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import utils.DragonUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MonsterTest {

  Psychic psychic = new Psychic("Psychic");
  Runner runner = new Runner("Runner");
  Flier flier = new Flier("Flier");

  @Test
  public void WHETHER_RANDOMNUMBER_BETWEEN_0_AND_9() {
    for (int i = 0; i < 999; i++) {
      int random = DragonUtils.getRandomNumber();
      assertTrue(random >= 0 && random < 10);
    }
  }

  @Test
  public void TO_MOVE_WHEN_UPPERTHAN_RANDOMTHRESHOLD() {
    psychic.updateResults(9);
    flier.updateResults(6);
    runner.updateResults(4);

    assertEquals(3, flier.getMoveNum());
    assertEquals(1, runner.getMoveNum());
    assertTrue(psychic.getMoveNum() < 100 && psychic.getMoveNum() > 0);
  }

  @Test
  public void SORTING_BY_POSITION_DESC() {
    psychic.setMove(9);
    flier.setMove(6);
    runner.setMove(4);

    List<Monsters> monstersArrayList = new ArrayList<>();

    monstersArrayList.add(psychic);
    monstersArrayList.add(flier);
    monstersArrayList.add(runner);

    List<Monsters> sortedMonsters = DragonUtils.sortByPositionDesc(monstersArrayList);

    assertEquals("Psychic", sortedMonsters.get(0).getName());
    assertEquals("Flier", sortedMonsters.get(1).getName());
    assertEquals("Runner", sortedMonsters.get(2).getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void SPLIT_WITH_DELIMITER_TEST() {
    DragonUtils.splitWithDelimiter(",", ""); //performs adequately
    DragonUtils.splitWithDelimiter(null, ""); //expected to throw exception
  }
}