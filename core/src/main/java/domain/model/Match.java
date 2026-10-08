package domain.model;

public record Match(
  String id,
  Team home,
  Team away
) {
}
