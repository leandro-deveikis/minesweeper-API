# Minesweeper-API Challenge - Solution by Leandro Deveikis
This is the solution presented for the [Minesweeper challenge](challenge.md)
Contains a Java application (with Spring-boot) that implements the API Rest required.

Main domain entities:
- Player: Representation of a User and its information.
- Game: A game and its status played by a user.
    - Grid: represents the board with all the Squares.
    - GameState: PLAYING or FINISHED
    - GameResult: WON or LOST
- Square: A portion of a game.
    - State: COVERED (not visible by the player), UNCOVERED (clicked by the player), FLAGGED
    - Value: MINE or NUMBER
    - FlagType: RED_FLAG, QUESTION_MARK (only set if State=FLAGGED)
  

Deployed at Heroku: 
[http://minesweeper-api-ld.herokuapp.com/](http://minesweeper-api-ld.herokuapp.com/)

## API Definition
[Complete Definitiion (with Postman Collection)](https://documenter.getpostman.com/view/9044501/TVRoWkjy)
[Main API Definition document](api_definition.md)

## Notes
* Used a 3 layer architecture: Controller, Service and Repository.
    * Controllers handle the request from the user and parses the response. Handles 
    * Services contain the logic for the game (GameService, GridService and PlayerService)
    * Repository handle the interactions with the database.
  
### Design decisions
* Sprint-Boot (web): Simple and complete framework to develop the REST application (including web server, dependency injection and persistence out of the box). 
* PostgreSQL: Used for persistence of Player and Games information. Simple integration with Heroku server.
* Heroku: Selected this cloud service to host the application and the database. 

### Items that can be improved
* Logs, metrics, monitory mechanisms.
* Exception and error handling can be improved. 
* Testing: Improve coverage.
* Concurrency: lock mechanism should be implemented to avoid concurrency problems. Could be implemented at Service level, locking by game.
* Production instances: At this moment there is only 1 instance for the app and 1 instance for the database. To ensure high-availability, this should be increase according to the expected throughput.
* Security (authentication and authorization) for the operation. 
 