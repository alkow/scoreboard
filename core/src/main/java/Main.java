import application.usecases.CreateMatchUseCase;
import application.usecases.GetScoreBoardUseCase;
import infrastructure.InMemoryRepository;

void main() {
  var matchRepository = new InMemoryRepository();
  var createMatchUseCase = new CreateMatchUseCase(matchRepository);
  var getScoreBoardUseCase = new GetScoreBoardUseCase(matchRepository);

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

  teams.stream()
    .gather(Gatherers.windowFixed(2))
    .map(pair -> new CreateMatchUseCase.CreateMatchCommand(pair.getFirst(), pair.getLast()))
    .forEach(createMatchUseCase::execute);

  var scoreBoard = getScoreBoardUseCase.execute();

  scoreBoard.matches().forEach(IO::println);
}
