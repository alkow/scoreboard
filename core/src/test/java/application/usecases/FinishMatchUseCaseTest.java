package application.usecases;

import domain.model.Match;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FinishMatchUseCaseTest {
  @Test
  void shouldFinishMatch() {
    var match = Match.ofNew("Brazil", "Norway");
    var matchRepository = new InMemoryRepository();
    matchRepository.save(match);
    var useCase = new FinishMatchUseCase(matchRepository);

    var command = new FinishMatchUseCase.FinishMatchCommand(match.id());
    useCase.execute(command);

    var actual = matchRepository.findById(match.id());
    assertEquals(true, actual.isFinished());
  }
}