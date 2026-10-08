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
}