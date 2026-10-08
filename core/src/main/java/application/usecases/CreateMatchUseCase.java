package application.usecases;

import domain.model.Match;
import domain.model.Team;
import infrastructure.InMemoryRepository;

import java.util.UUID;

public class CreateMatchUseCase {
  private final InMemoryRepository matchRepository;
  public CreateMatchUseCase(InMemoryRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public CreateMatchResponse execute(CreateMatchCommand command) {
    String matchId = UUID.randomUUID().toString();
    matchRepository.save(new Match(matchId, new Team(command.homeTeam, 0), new Team(command.awayTeam, 0), System.currentTimeMillis(), null));
    return new CreateMatchResponse(matchId);
  }

  public record CreateMatchResponse(String matchId) { }

  public record CreateMatchCommand(
    String homeTeam,
    String awayTeam
  ) {
  }
}
