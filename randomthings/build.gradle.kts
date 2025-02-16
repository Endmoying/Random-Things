import java.net.URI

plugins {
    id("net.neoforged.moddev")
}

val minecraftVersion: String by project
val neoForgeVersion: String by project

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

val jeiMinecraftVersion: String by project
val jeiVersion: String by project

dependencies {
    // Include modules
    jarJar(project(":randomthings-base"))
    implementation(project(":randomthings-base"))

    // JEI
    runtimeOnly("mezz.jei:jei-$jeiMinecraftVersion-common:$jeiVersion")
    runtimeOnly("mezz.jei:jei-$jeiMinecraftVersion-neoforge:$jeiVersion")
}

neoForge {
    version = neoForgeVersion

    runs {
        configureEach {
            logLevel = org.slf4j.event.Level.INFO
        }

        create("client") {
            client()
        }

        create("server") {
            server()
        }
    }

    mods {
        create("randomcore") {
            dependency(project(":randomcore"))
        }
        create("randomthings-base") {
            sourceSet(project(":randomthings-base").sourceSets["main"])
        }
    }
}

// Collect all API packages from all modules.
tasks.register<Jar>("apiJar") {
    archiveClassifier.set("api")

    from(project(":randomthings-base").sourceSets["main"].output)
    from(project(":randomthings-base").sourceSets["main"].allJava)

}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")

    from(project(":randomthings-base").sourceSets["main"].allJava)
}

tasks.build {
    dependsOn(tasks["apiJar"])
    dependsOn(tasks["sourcesJar"])
}

