plugins {
    id("java")
}

group = "pt.gongas"
version = "1.0.0"
description = "Shared module for TurboCustomItems"

tasks.withType<JavaCompile> {
    options.release.set(8)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}