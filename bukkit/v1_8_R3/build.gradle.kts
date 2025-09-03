plugins {
    id("java")
}

tasks.withType<JavaCompile> {
    options.release.set(8)
}

dependencies {
    implementation(project(":shared"))
    compileOnly("org.bukkit:craftbukkit:1.8.8-R0.1-SNAPSHOT")
    compileOnly("com.github.cryptomorin:XSeries:13.3.3")
}