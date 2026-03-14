import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.gradleup.shadow") version "9.0.0-rc1"
}

allprojects {
    group = "pt.gongas"
    version = "1.0.5"
    description = "CustomItems plugin system"
    ext.set("id", "turbocustomitems")
    ext.set("website", "https://github.com/goncalodelima/TurboCustomItems")
    ext.set("author", "ReeachyZ_")
}

subprojects {

    plugins.apply("maven-publish")

    plugins.withId("com.gradleup.shadow") {
        tasks.withType<ShadowJar>().configureEach {
            archiveClassifier.set("") // remove -all
        }
    }

    tasks.withType<Jar>().configureEach {
        archiveVersion.set(project.version.toString())
        archiveBaseName.set("EconomyPlugin-${project.name}")
    }

    repositories {
        mavenCentral()
        maven("https://repo.codemc.org/repository/maven-public/") // Item-NBT-API
        maven("https://repo.triumphteam.dev/snapshots") // GUI API
        maven("https://repo.purpurmc.org/snapshots") // Purpur
        maven("https://repo.extendedclip.com/releases") // PlaceholderAPI
        maven("https://repo.aikar.co/content/groups/aikar/") // Aikar
        maven("https://repo.papermc.io/repository/maven-public/") // paperweight, Velocity
        maven("https://repo.codemc.org/repository/nms/") // CraftBukkit + NMS
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://hub.spigotmc.org/nexus/content/groups/public/") // BStats
        maven("https://repo.piggypiglet.me/releases")
    }

    project.afterEvaluate {

        val javaExt = extensions.getByType<JavaPluginExtension>()

        val sourcesJar = tasks.register<Jar>("sourcesJar") {

            archiveClassifier.set("sources")
            from(javaExt.sourceSets["main"].allSource)

            if (project.name == "bukkit") { // Include the shared project in bukkit-sources.jar
                val sharedProject = project(":shared")
                val sharedJava = sharedProject.extensions.getByType<JavaPluginExtension>()
                from(sharedJava.sourceSets["main"].allSource)
            }

        }

        extensions.configure<PublishingExtension>("publishing") {

            publications {

                create<MavenPublication>("mavenModules") {
                    artifactId = "TurboCustomItems-${project.name}"
                    from(components["java"])
                    artifact(sourcesJar)
                }

            }

            repositories {

                val url = "https://repo.codemc.io/repository/goncalodelima/"

                val mavenUsername = System.getenv("goncalodelima_username") ?: return@repositories
                val mavenPassword = System.getenv("goncalodelima_password") ?: return@repositories

                maven(url) {
                    credentials {
                        username = mavenUsername
                        password = mavenPassword
                    }
                }

            }

        }

    }

}