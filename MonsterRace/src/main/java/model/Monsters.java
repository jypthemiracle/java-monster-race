package model;

public abstract class Monsters {

  protected String name;
  protected int move;

  public Monsters(String name) {
    this.name = name;
    this.move = 0;
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
    for (int i = 0; i < this.move; i++) {
      builder.append("-");
    }
    return builder.toString();
  }
}