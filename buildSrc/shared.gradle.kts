group = "net.moying"

repositories {
    repositories {
        maven {
            name = "ModMaven"
            url = uri("https://modmaven.dev")
            content {
                includeGroup("mezz.jei")
            }
        }
        mavenLocal() {
            content {
                includeGroup("net.moying")
                includeGroup("net.neoforged")
            }
        }
    }
    dependencies {
        add("compileOnly", "org.jetbrains:annotations:23.0.0")

        if (project.name != "randomcore") {
            add("api", project(":randomcore"))
        }
    }
}
