package application.usecases;

import application.ports.MatchRepository;

public class FinishMatchUseCase {

  private final MatchRepository matchRepository;

  public FinishMatchUseCase(MatchRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public void execute(FinishMatchCommand command) {
    matchRepository.finishMatch(command.id);
  }

  public record FinishMatchCommand(String id) {
  }
}
