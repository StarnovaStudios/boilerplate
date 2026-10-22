# AGENTS.md

Paper plugin template: Cloud (annotation commands), InvUI (GUIs), Shadow (bundling + relocation), run-paper (test server).

## Setting up a new plugin from this template

When asked to set this template up as a new plugin (name `<name>`, group `<group>`):

1. `settings.gradle.kts`: set `rootProject.name = "<name>"` (lowercase).
2. `gradle.properties`: set `group` and `version`.
3. Move `src/main/java/dev/starnova/boilerplate` to `src/main/java/<group as path>/<name>` and update every `package` and `import` line.
4. Rename `Boilerplate.java` to the plugin's class name. `plugin.yml` has `main: ${group}.${name}.Boilerplate`, so update the class name there too.
5. `plugin.yml`: update `website`; add `authors` / `description` if given.
6. Rename the `boilerplate` command literals and `boilerplate.*` permissions in `BoilerplateCommand`, and the comment in `config.yml`.
7. Remove the example command/GUI if the user doesn't want them (and the `annotationParser.parse(...)` call).
8. Run `./gradlew build` to verify.

`name`, `version`, `main` package and `api-version` in `plugin.yml` are filled in from Gradle; don't hardcode them.

## Commands

```sh
./gradlew build      # shaded jar in build/libs/
./gradlew runServer  # test Paper server with the plugin loaded
```

Needs the JDK in `javaVersion` (`gradle.properties`, default 21).

## Conventions

- Versions live in `gradle.properties`, read in `build.gradle.kts` via `by project`.
- `implementation` deps are shaded and **must** get a `relocate(...)` in `shadowJar`; server/plugin-provided deps are `compileOnly`.
- `InvUI.getInstance().setPlugin(this)` must stay in `onEnable` since InvUI is shaded.
- Commands: new class with `@Command`/`@Permission` methods, registered via `annotationParser.parse(...)` in the main class.
