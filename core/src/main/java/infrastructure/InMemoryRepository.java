package infrastructure;

import domain.model.Match;

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
}
