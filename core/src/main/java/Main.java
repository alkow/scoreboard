import application.usecases.CreateMatchUseCase;
import application.usecases.FinishMatchUseCase;
import application.usecases.GetScoreBoardUseCase;
import infrastructure.InMemoryRepository;

void main() {
  var matchRepository = new InMemoryRepository();
  var createMatchUseCase = new CreateMatchUseCase(matchRepository);
  var getScoreBoardUseCase = new GetScoreBoardUseCase(matchRepository);
  var finishMatchUseCase = new FinishMatchUseCase(matchRepository);

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

  matches.subList(0, 2)
    .forEach(match -> finishMatchUseCase.execute(new FinishMatchUseCase.FinishMatchCommand(match.matchId())));

  var scoreBoard = getScoreBoardUseCase.execute();

  scoreBoard.matches().forEach(IO::println);
}
