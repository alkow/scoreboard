package application.ports;

import domain.model.Match;

import java.util.List;

public interface MatchRepository {
  Match findById(String id);

  void save(Match match);

  List<Match> findAll();

  void finishMatch(String id);

  void updateScore(String matchId, int homeScore, int awayScore);
}
