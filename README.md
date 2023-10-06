# Football World Cup Score Board Library

This is a Java library for a Football World Cup Score Board. The library allows you to manage football matches, update scores, and get a summary of games by total score. Test-driven development (TDD) was followed during development.

## Assumptions

### Technical

- This library is an in-memory solution that uses collections to store match data, which is lost when the program terminates. For production readiness, consider integrating with a database.

- This is a Java library without a user interface.

- Concurrent runs and performance are not measured.

- Compatibility with various java versions is not considered. 


### Functional

- Team names aren't validated; any text string is accepted.

- The `updateScore` method doesn't validate scores for logical consistency or excessively large values.


[![ Maven Build ](https://github.com/archanaajmera/football-scoreboard/actions/workflows/maven.yml/badge.svg)](https://github.com/archanaajmera/football-scoreboard/actions/workflows/maven.yml)