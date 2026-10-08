package domain.model;

import java.util.Comparator;

public class MatchRanking {
  public static final Comparator<Match> BY_TOTAL_GOALS_THEN_NEWEST =
    Comparator.comparingInt(
        (Match m) -> m.home().score() + m.away().score()
      )
      .thenComparingLong(Match::startTimeStamp)
      .reversed();
}
