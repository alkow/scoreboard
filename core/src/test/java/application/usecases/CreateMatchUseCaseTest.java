package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateMatchUseCaseTest {

  @Test
  void shouldCreateMatch() {
    var matchRepository = new InMemoryRepository();
    var useCase = new CreateMatchUseCase(matchRepository);

    var command = new CreateMatchUseCase.CreateMatchCommand("Norway", "Brazil");
    var response = useCase.execute(command);

    var actual = matchRepository.findById(response.matchId());
    assertEquals(response.matchId(), actual.id(), "should have same id");
    assertEquals(new Team(command.homeTeam(), 0), actual.home(), "should have home score of 0");
    assertEquals(new Team(command.awayTeam(), 0), actual.away(), "should have away score of 0");
    assertFalse(actual.isFinished(), "should not be finished");
    assertTrue(actual.isStarted(), "should be started");
  }
}