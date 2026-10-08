package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetScoreBoardUseCaseTest {
  @Test
  void shouldGetScoreBoard() {
    var match = new Match("", new Team("Norway", 0), new Team("Brazil", 0));
    var matchRepository = new InMemoryRepository();
    matchRepository.save(match);
    var useCase = new GetScoreBoardUseCase(matchRepository);

    var response = useCase.execute();

    var expected = new GetScoreBoardUseCase.ScoreBoardResponse(
      List.of(match)
    );
    assertEquals(expected, response);
  }
}