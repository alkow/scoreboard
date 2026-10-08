package application.usecases;

import application.ports.MatchRepository;

public class UpdateScoreUseCase {
  private final MatchRepository matchRepository;

  public UpdateScoreUseCase(MatchRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public void execute(UpdateScoreCommand command) {
    matchRepository.updateScore(
      command.matchId(),
      command.homeScore(),
      command.awayScore()
    );
  }

  public record UpdateScoreCommand(
    String matchId,
    int homeScore,
    int awayScore
  ) {
  }
}
