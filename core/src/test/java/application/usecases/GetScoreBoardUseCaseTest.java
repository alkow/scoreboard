package application.usecases;

import domain.model.Match;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetScoreBoardUseCaseTest {
  @Test
  void shouldGetScoreBoard() {
    var match = Match.ofNew("Norway", "Brazil");
    var matchRepository = new InMemoryRepository();
    matchRepository.save(match);
    var useCase = new GetScoreBoardUseCase(matchRepository);

    var response = useCase.execute();

    var expected = new GetScoreBoardUseCase.ScoreBoardResponse(
      List.of(match)
    );
    assertEquals(expected, response);
  }

  @Test
  void shouldSortByTotalGoalsInMatch() {
    var sixGoals = Match.ofNew("Norway", "Brazil").updateScore(3, 3);
    var fiveGoals = Match.ofNew("England", "Germany").updateScore(3, 2);
    var twoGoals = Match.ofNew("Spain", "Italy").updateScore(1, 1);

    var matchRepository = new InMemoryRepository() {
      @Override
      public List<Match> findAll() {
        return List.of(fiveGoals, twoGoals, sixGoals);
      }
    };
    var useCase = new GetScoreBoardUseCase(matchRepository);

    var response = useCase.execute();

    var expected = new GetScoreBoardUseCase.ScoreBoardResponse(
      List.of(sixGoals, fiveGoals, twoGoals)
    );
    assertEquals(expected, response);
  }
}