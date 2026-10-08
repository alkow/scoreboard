package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinishMatchUseCaseTest {
  @Test
  void shouldFinishMatch() {
    var match = new Match("1", new Team("Norway", 0), new Team("Brazil", 0), System.currentTimeMillis(), null);
    var matchRepository = new InMemoryRepository();
    matchRepository.save(match);
    var useCase = new FinishMatchUseCase(matchRepository);

    var command = new FinishMatchUseCase.FinishMatchCommand(match.id());
    useCase.execute(command);

    var actual = matchRepository.findById(match.id());
    assertEquals(true, actual.isFinished());
  }
}