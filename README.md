## Design Decisions

The application uses separate use cases for each operation, with a repository
interface (`MatchRepository`) defining the persistence boundary.

The domain model uses immutable Java records, with business rules encapsulate business rules,
such as preventing negative scores and modifications to finished matches.

The scoreboard response exposes the domain model directly. This is a deliberate
simplification for a console application. In a REST API, a dedicated response DTO
would be preferable to avoid coupling the external API contract to the domain model.

The implementation is intentionally kept simple, avoiding abstractions that
aren't justified by the requirements.