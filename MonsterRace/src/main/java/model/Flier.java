package model;

public class Flier extends Monsters implements Move {
  private static final int MOVE_THRESHOLD = 6;
  private static final int MOVE_TURN_AMOUNT = 3;

  public Flier(String name) {
    super(name);
  }

  @Override
  public boolean isMove(Integer randomNumber) {
    return randomNumber >= MOVE_THRESHOLD;
  }

  @Override
  public void updateResults(Integer randomNumber) {
    if (isMove(randomNumber)){
      this.move += MOVE_TURN_AMOUNT;
    }
  }

  @Override
  public void printResults() {
    System.out.println(this.toString());
  }
}
