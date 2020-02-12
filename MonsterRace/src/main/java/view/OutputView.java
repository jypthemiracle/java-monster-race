package view;

import java.util.List;
import model.Monsters;
import utils.DragonUtils;

public class OutputView {

  public void printOutput(List<Monsters> monsters) {
    alertGameStart();
    printResults(monsters);
    printWinner(monsters);
  }

  private void alertGameStart(){
    System.out.println("<경기 시작>");
  }

  private void printWinner(List<Monsters> monsters){
    List<Monsters> sortedMonsters = DragonUtils.sortByPositionDesc(monsters);
    System.out.println(sortedMonsters.get(0).getName() + " is a winner.");
  }

  private void printResults(List<Monsters> monsters){
    StringBuilder builder = new StringBuilder();
    for (Monsters monster: monsters){
      builder.append(monster.toString());
    }
    System.out.println(builder.toString());
  }
}