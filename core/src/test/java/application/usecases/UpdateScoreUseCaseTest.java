package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UpdateScoreUseCaseTest {

  @Test
  void shouldUpdateScore() {
    var match = Match.ofNew("Brazil", "Norway");
    var matchRepository = new InMemoryRepository();
    matchRepository.save(match);
    var useCase = new UpdateScoreUseCase(matchRepository);

    var command = new UpdateScoreUseCase.UpdateScoreCommand(
      match.id(), 3, 2
    );
    useCase.execute(command);

    var actual = matchRepository.findById(match.id());

    assertEquals(new Team("Brazil", 3), actual.home());
    assertEquals(new Team("Norway", 2), actual.away());
    assertFalse(actual.isFinished());
  }
}