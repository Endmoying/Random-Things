import java.text.SimpleDateFormat
import java.util.*

plugins {
    id("net.neoforged.moddev")
}

val minecraftVersion: String by project
val neoForgeVersion: String by project

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

sourceSets {
    main {
        resources {
            srcDir("src/generated/resources")
        }
    }
}

val jeiMinecraftVersion: String by project
val jeiVersion: String by project

dependencies {
    jarJar(project(":randomcore"))
}

tasks.test {
    useJUnitPlatform()
}

neoForge {
    version = neoForgeVersion

    runs {
        create("data") {
            data()

            programArguments.addAll(
                    "--mod", "randomthings_base",
                    // TODO: Fix missing models...
                    //"--all",
                    "--server", "--client",
                    "--output", file("src/generated/resources").absolutePath,
                    "--existing", file("src/main/resources").absolutePath,
            )
        }
    }

    mods {
        register("randomcore") {
            dependency(project(":randomcore"))
        }

        register("randomthings_base") {
            sourceSet(sourceSets["main"])
        }
    }

    unitTest {
        enable()
        testedMod = mods["randomthings_base"]
    }

    neoFormRuntime {
        // verbose = true
    }
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Specification-Title" to "Random Things Base",
                "Specification-Vendor" to "Endmoying",
                "Specification-Version" to "1",
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
                "Implementation-Vendor" to "Endmoying",
                "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date()),
        ))
    }
}

tasks.register<Jar>("apiJar") {
    archiveClassifier.set("api")

    from(sourceSets["main"].output)
    from(sourceSets["main"].allJava)

}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allJava)
}

tasks.build {
    dependsOn(tasks["apiJar"])
    dependsOn(tasks["sourcesJar"])
}
