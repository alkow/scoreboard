package infrastructure;

import application.ports.MatchRepository;
import domain.model.Match;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository implements MatchRepository {
  private final Map<String, Match> store = new ConcurrentHashMap<>();

  @Override
  public Match findById(String id) {
    return store.get(id);
  }

  @Override
  public void save(Match match) {
    store.put(match.id(), match);
  }

  @Override
  public List<Match> findAll() {
    return List.copyOf(store.values());
  }

  @Override
  public void finishMatch(String id) {
    save(findById(id).finish());
  }

  @Override
  public void updateScore(String matchId, int homeScore, int awayScore) {
    save(findById(matchId).updateScore(homeScore, awayScore));
  }
}
