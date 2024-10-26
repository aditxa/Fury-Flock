# Angry Birds-Style Game

A 2D physics-based puzzle game implemented in Java using LibGDX framework.

## 🎮 Game Overview

Players launch birds at structures to eliminate pigs, using different bird types with unique abilities. The game features multiple levels with increasing difficulty, various types of blocks, and different pig types.

### Features
- Multiple bird types (Red Bird, Yellow Bird)
- Different pig types (Basic Pig, Armored Pig)
- Various building blocks (Wood, Stone)
- Physics-based gameplay
- Level progression system
- Score tracking
- Pause functionality

## 🛠️ Prerequisites

- Java Development Kit (JDK) 11 or higher
- Gradle 7.0 or higher
- LibGDX framework
- An IDE (preferably IntelliJ IDEA)

## ⚙️ Setup Instructions

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

## 🚀 Running the Game

### Running from IDE
1. Open the project in IntelliJ IDEA
2. Locate the `DesktopLauncher` class in the desktop module
3. Right-click and select `Run DesktopLauncher.main()`

### Running from Command Line
```bash
./gradlew desktop:run
```

## 🧪 Testing

Run all tests:
```bash
./gradlew test
```


## 🎮 Game Controls

- **Left Click**: Select and aim bird
- **Drag**: Pull back to set launch power and angle
- **Release**: Launch bird


## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com.aditya.angrybirdsclone
│   │       ├── Main.java
│   │       ├── screens/
│   │       │   ├── GameScreen.java
│   │       │   ├── HomeScreen.java
│   │       │   ├── LevelsScreen.java
│   │       │   ├── PauseScreen.java
│   │       │   └── EndScreen.java
│   │       ├── objects/
│   │       │   ├── GameObject.java
│   │       │   ├── birds/
│   │       │   ├── pigs/
│   │       │   └── blocks/
│   │       └── utils/
│   └── resources/
│       ├── textures/
│       ├── sounds/
│       └── levels/
└── test/
    └── java/
        └── com.aditya.angrybirdsclone
```

## 🔧 Configuration

Game settings can be modified in `config.properties`:
- Window dimensions
- Physics parameters
- Debug mode
- Sound settings

## 📦 Dependencies

- LibGDX (Game framework)
- Box2D (Physics engine)
- JUnit (Testing)
- Mockito (Testing)
- Log4j (Logging)

## 🐛 Known Issues

1. Physics glitches may occur with very high velocities
2. Rare collision detection issues between fast-moving objects
3. Memory leak when rapidly switching between levels (fixed in upcoming version)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📚 References

- [LibGDX Documentation](https://libgdx.com/dev/)
- [Box2D Documentation](https://box2d.org/documentation/)
- [Java Game Development with LibGDX](https://www.packtpub.com/product/java-game-development-with-libgdx/9781782166047)
- [Game Programming Patterns](http://gameprogrammingpatterns.com/)

## 📝 Version History

- 1.0.0
  - Initial release
  - Basic game mechanics
  - 10 levels
- 1.1.0
  - Added new bird types
  - Improved physics
  - Bug fixes
