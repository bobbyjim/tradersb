# Trader
Interstellar merchant simulation API. Explore Charted Space!

# Source
Java SpringBoot with Swagger2

# REST and Swagger
The API is a set of atomic HTTP calls, using the typical GET, PUT, POST, and DELETE
commands, using JSON as the payload.  In other words, this is RESTful. When running,
the service is accessed on port 10203.

Swagger is a convenient, "experimentable" wrapper to REST APIs. To access the Swagger
page, surf to http://localhost:10203/swagger-ui.html

# GENERAL API
```
GET /ships             ; lists the ship types available
```

# PLAYER API
```
POST /players           ; creates a new player
GET /players/$id        ; returns the player record
GET /players/$id/skills ; get the skills list
PUT /players/$id/skills/$skillname ; add or improve a skill

GET /players/$id/ship  ; returns the player's ship record
PUT /players/$id/ship  ; upgrade the ship

GET /players/$id/world ; returns the player's current world
PUT /players/$id/world ; jumps to a neighboring world

GET /players/$id/hex   ; returns the player's sector and hex number only
```

# Status
* "Star chart" map data is pulled from TravellerMap.com.
* Your data is persisted in a serialized Java object.
* Trades are calculated using the rules in the Traveller5 Core Rulebook (http://traveller5.net/Shop-List%2006%20Traveller5.html).

# TO DO
* Lots!

# Source
Map, ship, and trade rules from http://Traveller5.net
