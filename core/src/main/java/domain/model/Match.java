package domain.model;

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

  public Match finish() {
    return new Match(
      id,
      home,
      away,
      startTimeStamp,
      System.currentTimeMillis()
    );
  }
}
