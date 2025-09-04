enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.triumphteam.dev/snapshots") // GUI API
        maven("https://repo.aikar.co/content/groups/aikar/") // Aikar
        maven("https://repo.purpurmc.org/snapshots") // Purpur
        maven("https://repo.papermc.io/repository/maven-public/") // paperweight, Velocity
        maven("https://repo.codemc.org/repository/nms/") // CraftBukkit + NMS
        maven("https://repo.codemc.org/repository/maven-public/") // Item-NBT-API
        maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
        maven("https://repo.codemc.io/repository/maven-releases/") // PacketEventsAPI
        maven("https://repo.codemc.io/repository/maven-snapshots/") // PacketEventsAPI
        maven("https://hub.spigotmc.org/nexus/content/groups/public/") // BStats
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "TurboCustomItems"

include(":shared")
include(":bukkit")
include("bukkit")
include("shared")