package view;

import java.util.Optional;
import utils.DragonUtils;

public class InputView {

  public int getMonstersNum(String sc) {
    //System.out.println("몇 명의 몬스터가 경주하나요?");
    return Optional.of(Integer.parseInt(sc)).orElse(0);
  }

  public String[] getMonstersName(String sc) {
    //System.out.println("경주할 몬스터 이름과 종류를 입력하세요 (쉼표(,)를 기준으로 구분).");
    return DragonUtils.splitWithDelimiter(sc, ", ");
  }

  public int getAttemptNum(String sc){
    //System.out.println("시도할 횟수는 몇 번인가요?");
    //질문: readLine은 메소드인데, 이를 함수 파라미터로 받고 있음.
    return Optional.of(Integer.parseInt(sc)).orElse(0);
  }
}