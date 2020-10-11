# Minesweeper-API Challenge - Solution by Leandro Deveikis
This is the solution presented for the [Minesweeper challenge](challenge.md)

Main domain entities:
- Player: Representation of a User and its information.
- Game: A game and its status played by a user.
- Square: A portion of a game. 

Deployed at Heroku: 
[http://minesweeper-api-ld.herokuapp.com/](http://minesweeper-api-ld.herokuapp.com/)

## API Definition
### INFO
```shell script
    curl --request GET 'http://minesweeper-api-ld.herokuapp.com/' 
```

### Player

#### Player creation
POST /minesweeper/player/

Request:
```json
{
    "name": "user_name"
}
```

Response:
```json
{
    "id": 1234,
    "name": "user_name"
}
```

### Game

#### Game creation
POST /minesweeper/game/

Request
```json
{
    "player_id": 1234,
    "height": 10,
    "width": 10,
    "mine_quantity": 20
}    
```
Response:
```
//Complete with response of game state
```
#### Game interaction
##### HIT
/minesweeper/game/{id_game}/hit
```json
{
    "x": 5,
    "y": 6
}
```

Response:
```
//Complete with response of game state
```
##### FLAG
/minesweeper/game/{id_game}/flag
```json
{
    "x": 5,
    "y": 6
}
```

Response:
```
//Complete with response of game state
```