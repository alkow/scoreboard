package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateMatchUseCaseTest {

  @Test
  void shouldCreateMatch() {
    var matchRepository = new InMemoryRepository();
    var useCase = new CreateMatchUseCase(matchRepository);

    var command = new CreateMatchUseCase.CreateMatchCommand("Norway", "Brazil");
    var response = useCase.execute(command);

    var actual = matchRepository.findById(response.matchId());
    var expected = new Match(response.matchId(), new Team(command.homeTeam(), 0), new Team(command.awayTeam(), 0), System.currentTimeMillis(), null);
    assertEquals(expected, actual);
  }
}