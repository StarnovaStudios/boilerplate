plugins {
    java
    id("com.gradleup.shadow") version "9.0.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

val minecraftVersion: String by project
val paperVersion: String by project
val javaVersion: String by project
val cloudPaperVersion: String by project
val cloudVersion: String by project
val invuiVersion: String by project

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") { name = "papermc-repo" }
    maven("https://repo.xenondevs.xyz/releases") { name = "xenondevs" }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:$paperVersion")

    implementation("org.incendo:cloud-paper:$cloudPaperVersion")
    implementation("org.incendo:cloud-annotations:$cloudVersion")
    annotationProcessor("org.incendo:cloud-annotations:$cloudVersion")
    implementation("xyz.xenondevs.invui:invui:$invuiVersion")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(javaVersion.toInt())
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release = javaVersion.toInt()
        options.compilerArgs.add("-parameters") // lets cloud-annotations read argument names
    }
    shadowJar {
        archiveClassifier = ""
        // Relocate shaded libs so they don't clash with other plugins shipping different versions
        val lib = "${project.group}.${rootProject.name}.lib"
        relocate("org.incendo.cloud", "$lib.cloud")
        relocate("io.leangen.geantyref", "$lib.geantyref")
        relocate("xyz.xenondevs.invui", "$lib.invui")
        relocate("xyz.xenondevs.inventoryaccess", "$lib.inventoryaccess")
    }
    build {
        dependsOn(shadowJar)
    }
    runServer {
        minecraftVersion(minecraftVersion)
    }
    processResources {
        val props = mapOf("version" to version, "name" to rootProject.name, "group" to project.group, "apiVersion" to minecraftVersion)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("plugin.yml") {
            expand(props)
        }
    }
}
