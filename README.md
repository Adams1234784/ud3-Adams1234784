Battle Arena - Turn-Based Combat Game
Project Description
Battle Arena is an interactive turn‑based combat game built with JavaFX where players face progressively stronger monsters using normal attacks, special attacks, and healing abilities, each with unique strategic implications. Inspired by classic RPGs such as Final Fantasy, Pokemon, and Dragon Quest, the project demonstrates core object‑oriented programming principles including inheritance, polymorphism, encapsulation, and custom exception handling through a BattleException system that manages invalid game states. The game provides real‑time visual feedback with health bars and a comprehensive battle log, while the UML diagram and GUI wireframe illustrate the planning and structure behind the implementation. Some features were streamlined to meet the deadline, focusing on the essential mechanics rather than optional extras, ensuring a playable and functional experience. Users interact through a simple JavaFX interface, choosing actions each turn and watching the results unfold in the log, highlighting how strategic decision‑making and resource management drive gameplay.

Project Inspiration
This project was inspired by classic turn-based role-playing games (RPGs) such as:

Final Fantasy series – Strategic turn-based combat mechanics
Pokemon games – Progressive difficulty and monster battles
Dragon Quest – Simple yet engaging battle systems
The goal was to create a modern JavaFX implementation that captures the essence of these classic games while demonstrating core OOP concepts learned in CS112.

UML Diagram
The following UML diagram represents the final class structure of the Battle Arena game: ┌───────────────────────────────┐ │ Exception │ │ (Java Standard) │ └───────────────┬───────────────┘ │ extends ┌───────────────▼───────────────┐ │ BattleException │ │ + BattleException(String msg) │ └───────────────────────────────┘

┌───────────────────────────────┐ │ Character │ │ (Abstract Class) │ ├───────────────────────────────┤ │ - name : String │ │ - health : int │ │ - maxHealth : int │ │ - attack : int │ │ - defense : int │ ├───────────────────────────────┤ │ + Character(...) │ │ + attack(Character) : int │ │ + takeDamage(int) : int │ │ + heal(int) : void │ │ + isDefeated() : boolean │ │ + getHealthPercentage() : double│ └───────────────┬───────────────┘ │ ┌────────────▼─────────────┐ ┌────────────▼─────────────┐ │ Player │ │ Monster │ ├───────────────────────────┤ ├──────────────────────────┤ │ + specialAttack(Character)│ │ + attack(Character) │ │ + useHeal() │ │ + Monster(...) │ └───────────────────────────┘ └──────────────────────────┘

┌───────────────────────────────┐ │ BattleSystem │ ├───────────────────────────────┤ │ - player : Player │ │ - monsters : List │ │ - currentMonster : Monster │ │ - round : int │ │ - battleLog : StringBuilder │ ├───────────────────────────────┤ │ + playerAttack() : String │ │ + playerSpecialAttack() : String│ │ + playerHeal() : String │ │ + monsterTurn() : String │ │ + isBattleOver() : boolean │ │ + playerWon() : boolean │ │ + getBattleLog() : String │ └───────────────────────────────┘

┌───────────────────────────────┐ │ BattleController (JavaFX) │ ├───────────────────────────────┤ │ - UI controls (labels, bars, │ │ buttons, text area) │ │ - battleSystem : BattleSystem │ ├───────────────────────────────┤ │ + initialize() │ │ + onStart() │ │ + onAttack() │ │ + onSpecial() │ │ + onHeal() │ │ - updateUI() │ │ - endBattle() │ └───────────────────────────────┘

OOP Concepts Implemented
Inheritance: Player and Monster extend the abstract Character class
Polymorphism: The attack() method is overridden in both Player and Monster classes
Encapsulation: All class fields are properly encapsulated with private/protected access modifiers
Abstraction: Character is an abstract class that defines the structure for all characters
Exception Handling: Custom BattleException class extends Exception for game-specific error handling
Composition: BattleSystem contains Player and List<Monster> objects
Concepts Not Used
Interfaces: Abstract classes were chosen instead to provide both method signatures and shared implementation
Custom Generics: Standard collections are used; List<Monster> leverages built-in generics
Features
Turn-based Combat: Strategic battle system with player and monster turns
Multiple Actions: Attack, Special Attack, and Heal options
Progressive Difficulty: Monsters become stronger as you progress
Visual Feedback: Health bars and real-time battle log
Exception Handling: Custom exceptions for invalid game states
Modern GUI: Clean JavaFX interface with intuitive controls
How to Run
Ensure you have Java 23 (or Java 17+) and Maven installed
Navigate to the project directory
Run the application using:
mvn clean javafx:run
Or with the Maven wrapper: ./mvnw clean javafx:run

Project Structure
ud3-Adams1234784-main/ ├── pom.xml ├── README.md ├── src/ │ └── main/ │ ├── java/ │ │ └── cs112/ud3/ │ │ ├── HelloApplication.java │ │ ├── BattleController.java │ │ ├── BattleSystem.java │ │ ├── Character.java │ │ ├── Player.java │ │ ├── Monster.java │ │ └── BattleException.java │ └── resources/ │ └── cs112/ud3/ │ └── battle-view.fxml

Technologies Used
Java 23 (compatible with Java 17+) – Core programming language

JavaFX 22 – GUI framework

Maven – Build and dependency management

FXML – Declarative UI layout

Author CS112 – Adam Szloboda – UD3 Final Project
