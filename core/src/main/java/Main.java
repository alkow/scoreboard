import application.usecases.CreateMatchUseCase;
import application.usecases.FinishMatchUseCase;
import application.usecases.GetScoreBoardUseCase;
import application.usecases.UpdateScoreUseCase;
import infrastructure.InMemoryRepository;

void main() throws InterruptedException {
  var matchRepository = new InMemoryRepository();
  var createMatchUseCase = new CreateMatchUseCase(matchRepository);
  var getScoreBoardUseCase = new GetScoreBoardUseCase(matchRepository);
  var finishMatchUseCase = new FinishMatchUseCase(matchRepository);
  var updateScoreUseCase = new UpdateScoreUseCase(matchRepository);
  var random = new Random();

  var teams = List.of(
    "Norway",
    "Brazil",
    "England",
    "France",
    "Spain",
    "Italy",
    "Sweden",
    "Denmark"
  );

  var matches = teams.stream()
    .gather(Gatherers.windowFixed(2))
    .map(pair -> new CreateMatchUseCase.CreateMatchCommand(pair.getFirst(), pair.getLast()))
    .map(createMatchUseCase::execute)
    .toList();

  // Simulate 90 minutes of football
  for (int minute = 10; minute <= 90; minute += 10) {
    var selected = matches.get(random.nextInt(matches.size()));
    var match = matchRepository.findById(selected.matchId());

    boolean homeScores = random.nextBoolean();

    updateScoreUseCase.execute(
      new UpdateScoreUseCase.UpdateScoreCommand(
        match.id(),
        match.home().score() + (homeScores ? 1 : 0),
        match.away().score() + (homeScores ? 0 : 1)
      )
    );

    IO.println("\n⚽ Minute " + minute);
    printScoreBoard(getScoreBoardUseCase);

    Thread.sleep(500);
  }

  // Finish the first two matches
  matches.subList(0, 2).forEach(match ->
    finishMatchUseCase.execute(
      new FinishMatchUseCase.FinishMatchCommand(match.matchId())
    )
  );

  IO.println("\n🏆 FINAL SCOREBOARD");
  printScoreBoard(getScoreBoardUseCase);
}

private static void printScoreBoard(GetScoreBoardUseCase useCase) {
  useCase.execute().matches().forEach(match -> {
    IO.println("%-12s %d - %d %-12s %s".formatted(
      match.home().id(),
      match.home().score(),
      match.away().score(),
      match.away().id(),
      match.isFinished() ? "[FINISHED]" : "[LIVE]"
    ));
  });
}
