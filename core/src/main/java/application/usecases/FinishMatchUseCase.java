package application.usecases;

import infrastructure.InMemoryRepository;

public class FinishMatchUseCase {

  private final InMemoryRepository matchRepository;

  public FinishMatchUseCase(InMemoryRepository matchRepository) {
    this.matchRepository = matchRepository;
  }

  public void execute(FinishMatchCommand command) {
    matchRepository.finishMatch(command.id);
  }

  public record FinishMatchCommand(String id) {
  }
}
