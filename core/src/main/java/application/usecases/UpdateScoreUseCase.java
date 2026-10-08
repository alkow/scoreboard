package application.usecases;

import infrastructure.InMemoryRepository;

public class UpdateScoreUseCase {
  private final InMemoryRepository matchRepository;

  public UpdateScoreUseCase(InMemoryRepository matchRepository) {
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
  ) {}
}
