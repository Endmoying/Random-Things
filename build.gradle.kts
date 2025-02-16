import com.github.spotbugs.snom.Effort
import com.github.spotbugs.snom.SpotBugsTask

plugins {
    id("net.neoforged.moddev") version "1.0.19" apply false
    id("com.diffplug.spotless") version "6.25.0"
    id("idea")
    id("com.github.spotbugs") version "6.0.28"
}

allprojects {
    gradle.projectsEvaluated {
        tasks.withType<JavaCompile> {
            options.compilerArgs.addAll(arrayOf("--release", "400"))
        }
    }
}

subprojects {
    apply(plugin = "net.neoforged.moddev")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "com.github.spotbugs")

    spotless {

        encoding("UTF-8")

        java {
            cleanthat()

            eclipse().configFile("$rootDir/config/codeformat/codeformat.xml")

            // Revert to spaces, thank you eclipse
            indentWithSpaces(4)

            importOrder()
            removeUnusedImports()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }

    spotbugs {
        reportsDir = project.layout.buildDirectory.dir("reports/spotbugs/")
        effort = Effort.MAX
        ignoreFailures = true
    }

    tasks.withType<SpotBugsTask> {
        reports {
            create("html") {
                required = true
                outputLocation = project.layout.buildDirectory.file("reports/spotbugs/spotbugs.html")
            }
        }
    }

}

dependencies {

}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
