package application.usecases;

import domain.model.Match;
import domain.model.MatchRanking;
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
    var matches = memoryRepository.findAll()
      .stream()
      .filter(Match::isNotFinished)
      .sorted(MatchRanking.BY_TOTAL_GOALS)
      .toList();
    return new ScoreBoardResponse(matches);
  }

  public record ScoreBoardResponse(
    List<Match> matches
  ) {}
}
