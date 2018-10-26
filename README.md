# Trader
Interstellar merchant simulation API. Carry passengers and cargo to other worlds!

# Source
100% Java SpringBoot

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

# TO DO
* Lots!

# Source
Map, ship, and trade rules from http://Traveller5.net
