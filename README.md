# minesweeper-API - Solution by Leandro Deveikis
This is the solution presented for the [Minesweeper challenge](challenge.md)

Main domain entities:
- User: Representation of a User and its information.
- Game: A game and its status played by a user.
- Square: A portion of a game. 

## API Definition
###User

####User creation
POST /minesweeper/user/

Request:
```
{
   "name": "user_name"
}
```

Response:
``` 
{
   "id": 1234,
   "name": "user_name"
}
```

###Game

####Game creation
POST /minesweeper/user/

Request
```
{
"user_id":1234,
"height": 10,
"width": 10,
"mine_quantity": 20
}     
```
Response:
```
//Complete with response of game state
```
####Game interaction
#####HIT
/minesweeper/game/{id_game}/hit
```
{
   "x": 5,
   "y": 6
}
```

Response:
```
//Complete with response of game state
```
#####FLAG
/minesweeper/game/{id_game}/flag
```
{
   "x": 5,
   "y": 6
}
```

Response:
```
//Complete with response of game state
```