import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("com.gradleup.shadow") version "9.0.0-rc1"
}

tasks {

    named<ShadowJar>("shadowJar"){
        relocate("co.aikar.commands", "pt.gongas.customitems.lib.aikar.commands")
        relocate("co.aikar.locales", "pt.gongas.customitems.lib.aikar.locales")
        relocate("com.cryptomorin.xseries", "pt.gongas.customitems.lib.xseries")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    build {
        dependsOn(shadowJar)
    }
}

group = "pt.gongas"
version = "1.0.0"
description = "Bukkit module for TurboCustomItems"

dependencies {
    api(project(":shared"))
    implementation(project(":bukkit:v1_8_R3"))
    compileOnly("org.purpurmc.purpur:purpur-api:1.16.5-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-bukkit:0.5.1-SNAPSHOT")
    implementation("com.github.cryptomorin:XSeries:13.3.3")
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.15.1")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    disableAutoTargetJvm()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(8)
}

bukkit {
    name = "TurboCustomItems"
    version = "${project.version}"
    main = "pt.gongas.customitems.platforms.bukkit.BukkitCustomItemsPlugin"
    depend = listOf("NBTAPI")
    author = "ReeachyZ_"
    website = "https://github.com/goncalodelima"
    description = "CustomItems Plugin"
    apiVersion = "1.13"
}