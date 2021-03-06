# API Definition
## INFO
GET http://minesweeper-api-ld.herokuapp.com/

Example:
```shell script
    curl --request GET 'http://minesweeper-api-ld.herokuapp.com/' 
```
Example response:
```json
{
    "author": "Leandro Deveikis <leandro.deveikis@gmail.com>",
    "repo": "https://github.com/leandro-deveikis/minesweeper-API",
    "project_name": "Minesweeper-API",
    "version": "1.0.0"
}
```
## Player
### Create new Player
POST http://minesweeper-api-ld.herokuapp.com/player
Request:
```
{
    "name" : [player_name]
}
```
Example:
```shell script
curl --request POST 'http://minesweeper-api-ld.herokuapp.com/player' \
     --header 'Content-Type: application/json' \
     --data-raw '{
    "name": "Player name"
}'
```
Example response:
```json
{
    "id": 1,
    "name": "Player name"
}
```
## Game
### Game creation
POST http://minesweeper-api-ld.herokuapp.com/game
Request
```
{
    "player_id": [player_id],
    "height": [game_height],
    "width": [game_width],
    "mine_quantity": [mine_quantity]
}    
```
Example:
```shell script
curl --location --request POST 'http://minesweeper-api-ld.herokuapp.com/game' \
--header 'Content-Type: application/json' \
--data-raw '{
    "playerId": "1",
    "height": "15",
    "width": "15",
    "mineQuantity": "50"
}'
```
Example response:
```
{
    "id": 2,
    "player": {
        "id": 3,
        "name": "Leandro"
    },
    "grid": [
        [
            // deleted to reduse lenght
        ]
    ],
    "startTime": "2020-10-10T22:12:59.230321",
    "finishTime": null,
    "state": "PLAYING",
    "result": null,
    "height": 15,
    "width": 15,
    "timeExpended": 0
}
```
### Game interaction
#### Click square
POST http://minesweeper-api-ld.herokuapp.com/game/{id_game}/click
Request
```
{
    "x": [x],
    "y": [y]
}
```
Example:
```shell script
curl --location --request POST 'http://minesweeper-api-ld.herokuapp.com/game/2/click' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 3,
    "y": 2
}'
```
Example response:
```
{
    "id": 2,
    "player": {
        "id": 3,
        "name": "Leandro"
    },
    "grid": [
        [
            // deleted to reduse lenght - grid is updated
        ]
    ],
    "startTime": "2020-10-10T22:12:59.230321",
    "finishTime": null,
    "state": "PLAYING",
    "result": null,
    "height": 15,
    "width": 15,
    "timeExpended": 0
}
```
#### Flag square (Red flag or Question Mark)
POST http://minesweeper-api-ld.herokuapp.com/game/{id_game}/flag
```
{
    "x": [x],
    "y": [y],
    "flagType": [RED_FLAG,QUESTION_MARK]
}
```
Example
```shell script
curl --location --request POST 'http://minesweeper-api-ld.herokuapp.com/game/3/flag' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 1,
    "y": 0,
    "flagType": "QUESTION_MARK"
}'
```
Response:
```
{
    "id": 2,
    "player": {
        "id": 3,
        "name": "Leandro"
    },
    "grid": [
        [
            // deleted to reduse lenght - grid is updated
        ]
    ],
    "startTime": "2020-10-10T22:12:59.230321",
    "finishTime": null,
    "state": "PLAYING",
    "result": null,
    "height": 15,
    "width": 15,
    "timeExpended": 0
}
```