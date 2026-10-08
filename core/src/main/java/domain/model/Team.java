package domain.model;

public record Team(
  String id,
  int score
) {
  public Team {
    if (score < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }
  }

  public static Team ofNew(String id) {
    return new Team(id, 0);
  }
}
