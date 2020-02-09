package model;

public interface Move {
  boolean isMove(Integer randomNumber);
  void updateResults(Integer randomNumber);
  void printResults();
}