# 🚀 Starfighter

A turn-based space combat game developed in Java as part of a Software Engineering project.

The project was designed following Software Engineering principles, emphasizing maintainability, extensibility, clean architecture and automated testing. Beyond implementing the game mechanics, the primary goal was to design a scalable architecture capable of supporting new entities, weapons, abilities and gameplay systems with minimal modifications to existing code.

---

# 🎮 Gameplay

Starfighter is a turn-based strategy game where the player controls a customizable spaceship and must survive against increasingly dangerous enemy fleets.

The game includes:

- Multiple enemy types with unique AI behaviors.
- Different weapon systems.
- Special abilities.
- Projectile interactions.
- Vision and Fog of War mechanics.
- Turn-based combat system.
- Dynamic enemy spawning.
- Equipment customization.

---

# 📸 Screenshot

## Gameplay

![Gameplay Screenshot](Starfighter/docs/images/gameplay.png)


---

# 🏗️ Architecture

The project was designed using Object-Oriented Programming principles and several software design patterns to ensure low coupling and high cohesion.

## UML Diagram

![UML Diagram](Starfighter/docs/images/uml1.png)

![UML Diagram](Starfighter/docs/images/uml2.png)

![UML Diagram](Starfighter/docs/images/uml3.png)

---

## Design Patterns

### Command Pattern

Gameplay actions are represented as commands.

Examples:

- Move
- Fire
- Pass
- Special

This allows input handling to remain independent from gameplay execution.

### Observer Pattern

Used to facilitate communication between systems while reducing direct dependencies.

Benefits:

- Better modularity
- Easier maintenance
- Improved extensibility

### Factory Pattern

Responsible for creating game entities and reducing object creation coupling.

Benefits:

- Centralized creation logic
- Easier addition of new entity types
- Improved scalability

---

# ⚙️ Technical Highlights

- Turn-based game architecture.
- Multi-phase game loop.
- Modular entity system.
- Enemy AI behaviors.
- Projectile interaction system.
- Collision handling.
- State management.
- Event-driven communication.
- Automated testing.
- Build automation using Gradle.

---

# 🔄 Turn Processing Pipeline

Each turn is divided into multiple phases:

1. Friendly Projectiles Act
2. Enemy Projectiles Act
3. Starfighter Act
4. Enemy Vision Update
5. Enemies Act
6. Enemy Vision Update
7. Enemy Spawn

This architecture allows gameplay systems to remain deterministic and predictable while keeping responsibilities clearly separated.

---

# 👾 Enemy System

The game currently supports multiple enemy types:

| Enemy | Characteristics |
|---------|----------------|
| Grunt | Basic enemy unit |
| Fighter | Mobile combat unit |
| Carrier | Spawns additional enemies |
| Interceptor | Aggressive pursuit behavior |
| Pylon | Support and healing unit |

Each enemy has its own:

- Health
- Armor
- Vision
- Regeneration
- Movement logic
- Combat behavior

---

# 🔫 Combat System

The combat system supports:

- Friendly projectiles.
- Enemy projectiles.
- Projectile collisions.
- Damage calculation.
- Armor mitigation.
- Healing mechanics.
- Entity destruction.
- Collision resolution.

---

# 🧩 Extensibility

One of the primary goals of the project was extensibility.

The architecture was designed so that new:

- Enemies
- Weapons
- Abilities
- Projectiles
- Gameplay mechanics

can be introduced without requiring significant modifications to existing systems.

---

# 🧪 Testing

The project was developed following TDD and BDD practices.

Testing goals included:

- Validation of gameplay mechanics.
- Verification of entity interactions.
- Prevention of regressions.
- High instruction and branch coverage.

Run tests:

```bash
./gradlew test
```

---

# 🛠️ Technologies

- Java
- Gradle
- JUnit
- Git

---

# 📂 Project Structure

```text
src/
├── controller/
├── model/
├── view/
├── commands/
├── entities/
├── enemies/
├── projectiles/
├── states/
├── game/
└── tests/
```

---

# 🚀 Running the Project

Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/starfighter.git
cd starfighter
```

Build:

```bash
make build
```

Run:

```bash
make run
```

---

# 💡 What I Learned

Through this project I gained practical experience in:

- Software Architecture
- Design Patterns
- Object-Oriented Design
- Automated Testing
- UML Modeling
- Team Development
- Game Programming
- Refactoring
- Build Automation

---
