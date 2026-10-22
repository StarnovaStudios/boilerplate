# Boilerplate

A starter template for Paper plugins. It comes with:

- [Cloud](https://github.com/Incendo/cloud) for annotation-based commands
- [InvUI](https://github.com/NichtStudioCode/InvUI) for inventory GUIs
- Shadow, so dependencies are bundled and relocated into the plugin jar
- run-paper, to start a test server with one command

## Using this template

1. **`settings.gradle.kts`**: set `rootProject.name` to your plugin's name (lowercase).
2. **`gradle.properties`**: set `group`, `version`, and bump any versions you need.
3. **Package**: rename `src/main/java/dev/starnova/boilerplate` to match `<group>/<name>`.
4. **Main class**: rename `Boilerplate.java` if you want. If you do, update `main:` in `src/main/resources/plugin.yml`.
5. **`plugin.yml`**: change `website` (and add `authors`, `description`, and so on).
6. Delete or replace the example command (`command/`) and GUI (`gui/`).

The plugin name, version and main package in `plugin.yml` are filled in from Gradle automatically.

## Building

```sh
./gradlew build      # jar ends up in build/libs/
./gradlew runServer  # starts a test Paper server with the plugin loaded
```

Requires a JDK matching `javaVersion` in `gradle.properties` (21 by default) to be installed.

## Adding dependencies

Add the version to `gradle.properties` and the dependency to `build.gradle.kts`. Use `implementation` for anything that should be bundled into the jar, and add a `relocate(...)` line in `shadowJar` for it. Use `compileOnly` for anything another plugin or the server already provides.
