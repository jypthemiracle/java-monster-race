package model;
import utils.DragonUtils;

public class Psychic extends Monsters implements Move {
  private static final int MOVE_THRESHOLD = 9;

  public Psychic(String name) {
    super(name);
  }

  @Override
  public boolean isMove(Integer randomNumber) {
    return randomNumber >= MOVE_THRESHOLD;
  }

  @Override
  public void updateResults(Integer randomNumber) {
    if (isMove(randomNumber)) {
      this.move += DragonUtils.getRandomPsychicMoveAmount();
    }
  }

  @Override
  public void printResults() {
    System.out.println(this.toString());
  }
}