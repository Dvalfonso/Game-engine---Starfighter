# New Game States
- Not Started: when launch the software.
- Set Up Stage: when invoke the play command.
    - Weapon Setup: 5 types.
    - Armour Setup: 4 types.
    - Engine Setup: 3 types.
    - Power Setup: 5 types.
    - Summary
- In Game: when game has begun.


# Stats
## Weapon, Armour, Engine shared stats:
- Health
- Energy
- Regeneration
    - Health
    - Energy
- Armour
- Vision
- Move
- MoveCost

## Weapon
- HealthCost
- EnergyCost
- BaseDamage


# Commands
## Menu commands
- play
- setupNext(int)
- setupBack(int)
- setupSelect(int)

## Game commands
- fire
- special


# Weapons Types
- Standard: right to sf and move 5 to the right.
- Spread: right to sf, 3 verticals projectiles, move in diagonal and to the right the central one
- Snipe: right to sf, teleport 8 to right.
- Rocket: one top-left, and one botton-right, move the double of the distance in each turn to the right.
- Splitter: right to sf, and doesn't move.


# Powers
- Recall: teleport to spawn.
- Repair: Health+50
- Overcharge: 1 Health for 2 Energy.
- Deploy Drones: clear all projectiles.
- Orbital Strike: 100 - enemie Armour, for all enemies.


# Enemies

## Shared Stats
- Health: total
- Health Regen: per turn.
- Armour: 1 to 1.
- Vision
- SeenBySF
- CanSeeSF

## Types
### Syntax
- Name
    - Stats: Health,Regen,Armour,Vision
    - Preemptive Action:
        - command(End/Not End):
    - Action if SF isn't seen:
    - Action if SF is seen:
- FL-4L-15 = fire directly left, projectile move 4 left per turn, 15 damage

- Grunt
    - 100,1,1,5
    - Preemptive Action:
        - SF pass(NE): +10 Current Health, +10 Total Health
        - SF special(NE): +20 Current Health, +20 Total Health
    - Move 2L, FL-4L-15
    - Move 4L, FL-4L-15

- Fighter
    - 150,5,10,10
    - Preemptive Action:
        - SF fire(NE): +1 Armour
        - SF pass(E): Move 6L, FL-10L-100
    - Move 3L, FL-3L-20
    - Move 1L, FL-6L-50

- Carrier
    - 200,10,15,15
    - Preemptive Action, end turn:
        - SF special(NE): +10 Regen
        - SF pass(E): Move 2L, spawn: 1IA, 1IB
    - Move 2L
    - Move 1L, spawn: 1IL

- Interceptor
    - Stats: 50,0,0,5
    - Preemptive Action, don' end turn:
        - SF fire(NE): Move to row of SF
    - Move 3L
    - Move 3L

- Pylon
    - 300,0,0,5
    - None
    - Move 2L, +10 Current Health for enemies.
    - Move 1L, FL-2L-70


# Turn Phases

1. Friendly Projectiles Act
2. Enemy Projectiles Act
3. Starfighter Act
4. Enemy Vision Update
5. Enemies Act
6. Enemy Vision Update
7. Enemy Spawn

## Game Over
- Current Health <= 0
- abort command
