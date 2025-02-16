val neoForgeVersion: String by project

apply(from = rootProject.file("buildSrc/shared.gradle.kts"))

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))


dependencies {
}

neoForge {
    version = neoForgeVersion

    mods {
        create("randomcore") {
            sourceSet(sourceSets["main"])
        }
    }
}
