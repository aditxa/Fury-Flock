# Angry Birds-Style Game

### Features
- Multiple bird types (Red Bird, Yellow Bird)
- Different pig types (Basic Pig, Armored Pig)
- Various building blocks (Wood, Stone)
- Physics-based gameplay
- Level progression system
- Score tracking
- Pause functionality

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Gradle 7.0 or higher
- LibGDX framework
- An IDE (preferably IntelliJ IDEA)

## Setup Instructions

1. Clone the repository:
```bash
git clone [https://github.com/aditxa/angry2]
cd [angry2]
```

2. Install dependencies:
```bash
./gradlew clean build
```

3. Import project in IntelliJ IDEA:
- Go to `File -> Open`
- Select the project's root directory
- Choose `Import project from external model` -> `Gradle`
- Click `Finish`

## Running the Game

### Running from IDE
1. Open the project in IntelliJ IDEA
2. Locate the `DesktopLauncher` class in the desktop module
3. Right-click and select `Run DesktopLauncher.main()`

### Running from Command Line
```bash
./gradlew desktop:run
```

## Game Controls

- **Left Click**: Select and aim bird
- **Drag**: Pull back to set launch power and angle
- **Release**: Launch bird


## Project Structure

```
project-root/
├── assets/
│   └── [main]    #Images
├── core/
│   ├── build/
│   └── src/
│       └── main/
│           └── java/
│               └── com.aditya.angrybirdsclone/
│                   ├── main.java
│                   ├── UML.m
│                   ├── entities/
│                   │   ├── ArmoredPig.java
│                   │   ├── BasicPig.java
│                   │   ├── Bird.java
│                   │   ├── Block.java
│                   │   ├── GameObject.java
│                   │   ├── Pig.java
│                   │   ├── RedBird.java
│                   │   ├── StoneBlock.java
│                   │   ├── WoodBlock.java
│                   │   └── YellowBird.java
│                   └── screens/
│                       ├── EndScreen.java
│                       ├── GameScreen.java
│                       ├── HomeScreen.java
│                       ├── LevelsScreen.java
│                       └── PauseScreen.java
├── lwjgl3/
│   ├── build/
│   └── src/
│       └── main/
│           └── java/
│               └── com.aditya.angrybirdsclone.lwjgl3/
│                   ├── Lwjgl3Launcher.java
│                   └── StartupHelper.java
├── gradle/
└── build.gradle



```

## Dependencies

- LibGDX (Game framework)
- Box2D (Physics engine)

##  Known Issues

1. Physics glitches may occur with very high velocities
2. Rare collision detection issues between fast-moving objects

##  Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## References

- [LibGDX Documentation](https://libgdx.com/dev/)
- [Box2D Documentation](https://box2d.org/documentation/)
- [Java Game Development with LibGDX](https://www.packtpub.com/product/java-game-development-with-libgdx/9781782166047)
- [Game Programming Patterns](http://gameprogrammingpatterns.com/)


# Angry Birds Clone (LibGDX)

This project is a GUI-based Angry Birds clone built with the **LibGDX** framework. It features multiple levels, UI screens, and bird-launching mechanics, with visuals inspired by the classic game.

## Requirements
- **Java Development Kit (JDK)** (Version 8 or above)
- **IntelliJ IDEA** IDE
- **LibGDX** framework
- Gradle for dependency management

## Setup Instructions
1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd <repository-directory>
Open the project in IntelliJ IDEA:

Select "Open or Import" and choose the project's root directory.
IntelliJ should detect the Gradle build system automatically.
Sync Gradle:

Allow IntelliJ to download and sync dependencies for the project.
Add assets:

Place all required assets (e.g., endscreen.png, homescreen.png, catapult.png, etc.) in the android/assets folder.
Run the Project
Select the Main class: Ensure the Main class is set as the entry point for the project.

Run the desktop module: Execute the desktop module using the Gradle task:

./gradlew desktop:run
Alternatively, use the IntelliJ "Run" button for the desktop configuration.

Testing the Project
Play the game:

Navigate through the different screens (home, levels, pause, etc.).
Test the bird-launching mechanics, collision detection, and level transitions.
Run unit tests (if implemented):

./gradlew test
Online References
YouTube: Tutorials for LibGDX GUI design and game development.
Google: Documentation and forum discussions for troubleshooting.
