package infrastructure;

import domain.model.Match;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository {
  private final Map<String, Match> store = new ConcurrentHashMap<>();

  public Match findById(String id) {
    return store.get(id);
  }

  public void save(Match match) {
    store.put(match.id(), match);
  }

  public List<Match> findAll() {
    return List.copyOf(store.values());
  }

  public void finishMatch(String id) {
    save(findById(id).finish());
  }

  public void updateScore(String matchId, int homeScore, int awayScore) {
    save(findById(matchId).updateScore(homeScore, awayScore));
  }
}
