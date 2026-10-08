package domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {
  @Test
  void shouldNotUpdateFinishedMatch() {
    var match = Match.ofNew("Brazil", "Norawy")
      .finish();

    assertThrows(
      IllegalStateException.class,
      () -> match.updateScore(1, 0)
    );
  }

  @Test
  void shouldNotFinishAnAlreadyFinishedMatch() {
    var match = Match.ofNew("Brazil", "Norawy")
      .finish();

    assertThrows(
      IllegalStateException.class,
      match::finish
    );
  }
}