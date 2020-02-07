import model.Candidates;
import model.Democrats;
import model.Republican;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CandidatesTest {
  private Democrats democrats;
  private Republican republican;

  @Before
  public void setup(){
    obama = new Democrats("Barack Obama");
    bush = new Republican("George W. Bush");
  }

  @Test
  public void 랜덤숫자가_0부터_9사이인가(){
    for (int i = 0; i < 999; i++){
      int random = Utils.getRandomNumber();
      assertTrue(random >= 0 && random < 10);
    }
  }

  @Test
  public void 랜덤숫자가_4이상이면_움직인다(){
    obama.move(4);
    assertEquals(1, obama.getPosition());
    bush.move(3);
    assertEquals(0, bush.getPosition());
  }

  @Test
  public void 점수별_내림차순_정렬_테스트(){
    List<Candidates> candidates = new ArrayList<>();
    Democrats warren = new Democrats("Elizabeth Warren");
    Democrats biden = new Democrats("Joe Biden");
    Democrats sanders = new Democrats("Benry Sanders");
    Democrats buttigieg = new Democrats("Pete Buttigieg");
    Republican trump = new Republican("President Donald Trump");

    warren.updateResults(3); //4
    biden.updateResults(2); //5
    sanders.updateResults(4); //3
    buttigieg.updateResults(6); //1
    trump.updateResults(5); //2

    candidates.add(warren);
    candidates.add(biden);
    candidates.add(sanders);
    candidates.add(buttigieg);
    candidates.add(trump);

    List<Candidates> sortedCandidates = Utils.soryByPositionDesc();

    assertEquals("Pete Buttigieg", sortedCandidates.get(0).getName());
    assertEquals("President Donald Trump", sortedCandidates.get(1).getName());
    assertEquals("Benry Sanders", sortedCandidates.get(2).getName());
    assertEquals("Elizabeth Warren", sortedCandidates.get(3).getName());
    assertEquals("Joe Biden", sortedCandidates.get(4).getName());
  }
  @Test(expected = IllegalArgumentException.class)
  public void 쉼표를_기준으로_구분_테스트(){
    Utils.splitWithDelimiter(",", ""); //performs adequately
    Utils.splitWithDelimiter(null, ""); //expected to throw exception
  }
}
