package application.usecases;

import domain.model.Match;
import infrastructure.InMemoryRepository;

import java.util.List;

public class GetScoreBoardUseCase {

  private final InMemoryRepository memoryRepository;

  public GetScoreBoardUseCase(
    InMemoryRepository memoryRepository
  ) {
    this.memoryRepository = memoryRepository;
  }

  public ScoreBoardResponse execute() {
    var matches = memoryRepository.findAll();
    return new ScoreBoardResponse(matches);
  }

  public record ScoreBoardResponse(
    List<Match> matches
  ) {}
}
