package application.usecases;

import application.ports.MatchRepository;
import domain.model.Match;

public class CreateMatchUseCase {
  private final MatchRepository matchRepository;
  public CreateMatchUseCase(MatchRepository matchRepository) {
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
