package model;

public abstract class Monsters {

  protected String name;
  protected int move;

  public Monsters(String name) {
    this.name = name;
    this.move = 0;
  }

  public boolean isMove(Integer randomNumber) {
    return false;
  }

  public void updateResults(Integer randomNumber) {
  }

  public void setMove(int moveNum){
    this.move = moveNum;
  }

  public int getMoveNum(){
    return this.move;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getName()).append(" ").append(getMoveNum());
    for (int i = 0; i < this.move; i++) {
      builder.append("-");
    }
    builder.append("\n");
    return builder.toString();
  }
}