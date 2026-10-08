package domain.model;

import java.util.Comparator;

public class MatchRanking {
  public static final Comparator<Match> BY_TOTAL_GOALS =
    Comparator.comparingInt(
      (Match m) -> m.home().score() + m.away().score()
    ).reversed();
}
