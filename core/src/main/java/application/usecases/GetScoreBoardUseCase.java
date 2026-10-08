package application.usecases;

import application.ports.MatchRepository;
import domain.model.Match;
import domain.model.MatchRanking;

import java.util.List;

public class GetScoreBoardUseCase {

  private final MatchRepository memoryRepository;

  public GetScoreBoardUseCase(
    MatchRepository memoryRepository
  ) {
    this.memoryRepository = memoryRepository;
  }

  public ScoreBoardResponse execute() {
    var matches = memoryRepository.findAll()
      .stream()
      .filter(Match::isNotFinished)
      .sorted(MatchRanking.BY_TOTAL_GOALS_THEN_NEWEST)
      .toList();
    return new ScoreBoardResponse(matches);
  }

  public record ScoreBoardResponse(
    List<Match> matches
  ) {}
}
