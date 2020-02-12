package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import model.Monsters;

public class DragonUtils {

  private static final int RANDOM_NUMBER_BOUNDARY = 9;
  private static final int PSYCHIC_MOVE_AMOUNT_BOUNDARY = 100;

  public static Integer getRandomNumber() {
    return new Random().nextInt(RANDOM_NUMBER_BOUNDARY);
  }

  public static Integer getRandomPsychicMoveAmount() {
    return new Random().nextInt(PSYCHIC_MOVE_AMOUNT_BOUNDARY);
  }

  public static List<Monsters> sortByPositionDesc(List<Monsters> monstersArrayList) {
    List<Monsters> sortedMonsters = new ArrayList<>(monstersArrayList);
    sortedMonsters.sort(Comparator.comparing(Monsters::getMoveNum).reversed());
    return sortedMonsters;
  }

  public static String[] splitWithDelimiter(String target, String delimiter) {
    if (target == null || target.isEmpty()){
      throw new IllegalArgumentException();
    }
    return target.split(delimiter);
  }
}