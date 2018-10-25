# Trader
Interstellar merchant simulation API. Carry passengers and cargo to other worlds!

# Source
100% Java SpringBoot

# API
```
POST /players          ; creates a new player
GET /players/$id       ; returns the player record
GET /ships             ; lists the ship types available
GET /players/$id/ship  ; returns the player's ship record
GET /players/$id/world ; returns the player's current world
PUT /players/$id/world ; jumps to a neighboring world
```

# Status
* "Star chart" map data is pulled from TravellerMap.com.
* Your data is persisted in a serialized Java object.

# TO DO
* Lots!

# Source
Map, ship, and trade rules from http://Traveller5.net
