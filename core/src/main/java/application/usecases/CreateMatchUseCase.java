package application.usecases;

import domain.model.Match;
import infrastructure.InMemoryRepository;

public class CreateMatchUseCase {
  private final InMemoryRepository matchRepository;
  public CreateMatchUseCase(InMemoryRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public CreateMatchResponse execute(CreateMatchCommand command) {
    Match match = Match.ofNew(command.homeTeam, command.awayTeam);
    matchRepository.save(match);
    return new CreateMatchResponse(match.id());
  }

  public record CreateMatchResponse(String matchId) { }

  public record CreateMatchCommand(
    String homeTeam,
    String awayTeam
  ) {
  }
}
