package domain.model;

import java.util.UUID;

public record Match(
  String id,
  Team home,
  Team away,
  long startTimeStamp,
  Long endTimeStamp
) {
  public boolean isFinished() {
    return endTimeStamp != null;
  }

  public boolean isStarted() {
    return startTimeStamp() > 0;
  }

  public Match finish() {
    if (isFinished()) {
      throw new IllegalStateException("Cannot finish an already finished match");
    }
    return new Match(
      id,
      home,
      away,
      startTimeStamp,
      System.currentTimeMillis()
    );
  }

  public Match updateScore(int homeScore, int awayScore) {
    if (isFinished()) {
      throw new IllegalStateException("Cannot update finished match");
    }
    return new Match(
      id,
      new Team(home.id(), homeScore),
      new Team(away.id(), awayScore),
      startTimeStamp,
      endTimeStamp
    );
  }

  public static Match ofNew(String homeTeam, String awayTeam) {
    return new Match(
      UUID.randomUUID().toString(),
      new Team(homeTeam, 0),
      new Team(awayTeam, 0),
      System.currentTimeMillis(),
      null
    );
  }
}
