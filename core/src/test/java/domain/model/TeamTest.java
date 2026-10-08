package domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TeamTest {

  @Test
  void shouldNotCreateTeamWithNegativeScore() {
    assertThrows(
      IllegalArgumentException.class,
      () -> new Team("", -1)
    );
  }
}