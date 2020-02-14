import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import controller.Controller;
import controller.Database;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import model.Flier;
import model.Monsters;
import model.Psychic;
import model.Runner;
import org.junit.jupiter.api.Test;
import utils.DragonUtils;
import view.OutputView;

public class MonsterTest {

  private Monsters psychic;
  private Monsters flier;
  private Monsters runner;

  static void setup() {
    Psychic psychic = new Psychic("Javajigi");
    Runner runner = new Runner("Jason");
    Flier flier = new Flier("Brian");
  }

  @Test
  public void loadGameTest()
      throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Controller controller = mock(Controller.class);
    controller.loadGame(1); //User가 1을 입력했다고 가정합니다.
    verify(controller).startGame(); //startGame이 로딩되었는지 확인합니다.

    controller.loadGame(2); //User가 2를 입력했다고 가정합니다.
    verify(controller).loadGameCRUDMenu(3); //loadGameCRUDMenu이 로딩되었는지 확인합니다.
  }

  @Test
  public void loadGameCRUDMenuTest()
      throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
    Controller controller = mock(Controller.class);
    String testName = "honux";
    String changeName = "JK";
    String changeType = "Flier";
    String[] modifyInfo = new String[]{testName, changeName, changeType};

    controller.loadGameCRUDMenu(1);
    verify(controller).showAllMonstersInfo();

    controller.loadGameCRUDMenu(2);
    verify(controller).modifyMonstersInfo(modifyInfo);

    controller.loadGameCRUDMenu(3);
    verify(controller).addNewMonstersInfo();

    controller.loadGameCRUDMenu(4);
    verify(controller).deleteMonstersInfo(testName);

    controller.loadGameCRUDMenu(5);
    verify(controller).loadGame(1); //1번 실행해보는 것으로 지정
  }

  @Test
  public void showAllMonstersInfoTest() {
    Controller controller = mock(Controller.class);
    OutputView outputview = mock(OutputView.class);

    controller.showAllMonstersInfo();
    verify(outputview).printAllMonsters();

    int size = Database.monstersData.size();
    for (int i = 0; i < size; i++) {
      assertTrue(outputview.printAllMonsters().contains(Database.monstersData.get(i).getName()));
      assertTrue(outputview.printAllMonsters().contains(Database.monstersData.get(i).getMoveNum()));
    }
  }

  @Test
  public void modifyMonsterInfoTest()
      throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

    Controller controller = mock(Controller.class);
    String testName = "honux";
    String changeName = "JK";
    String changeType = "Flier";
    String[] modifyInfo = new String[]{testName, changeName, changeType};

    Database.monstersData.add(new Runner(testName));
    controller.modifyMonstersInfo(modifyInfo);
    assertEquals(changeName, Database.monstersData.get(0).getName()); //이름이 바뀌었는가?
    assertEquals(changeType, Database.monstersData.get(0).getClass().toString()); //타입이 바뀌었는가?
    //String toGenericString() : 타입 매개변수를 포함한 해당 메소드의 정보를 리턴
  }

  @Test
  public void deleteMonsterTest() {
    Controller controller = mock(Controller.class);
    String deleteName = "honux";
    controller.deleteMonstersInfo(deleteName);
    for (int i = 0; i < Database.monstersData.size(); i++) {
      assertNotEquals(Database.monstersData.get(i).getName(), deleteName);
    }
  }

  @Test
  public void WHETHER_RANDOM_NUMBER_BETWEEN_0_AND_9() {
    for (int i = 0; i < 999; i++) {
      int random = DragonUtils.getRandomNumber();
      assertTrue(random >= 0 && random < 10);
    }
  }

  @Test
  public void TO_MOVE_WHEN_UPPERTHAN_RANDOMTHRESHOLD() {
    setup();
    psychic.updateResults(9);
    flier.updateResults(6);
    runner.updateResults(4);

    assertEquals(3, Integer.parseInt(flier.getMoveNum().toString()));
    assertEquals(1, Integer.parseInt(runner.getMoveNum().toString()));
    assertTrue(Integer.parseInt(psychic.getMoveNum().toString()) < 100
        && Integer.parseInt(psychic.getMoveNum().toString()) > 0);
  }

  @Test
  public void SORTING_BY_POSITION_DESC() {
    setup();
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

  @Test
  public void TEST_TOSTRING() {
    setup();
    psychic.setMove(9);
    flier.setMove(6);
    runner.setMove(4);

    assertEquals("Psychic 9---------" + "\n", psychic.toString());
    assertEquals("Flier 6------" + "\n", flier.toString());
    assertEquals("Runner 4----" + "\n", runner.toString());
  }

//  //@Test(expected = IllegalArgumentException.class)
//  public void SPLIT_WITH_DELIMITER_TEST() {
//    DragonUtils.splitWithDelimiter(",", ""); //performs adequately
//    DragonUtils.splitWithDelimiter(null, ""); //expected to throw exception
//  }
}