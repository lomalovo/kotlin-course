import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.5.20" apply false
}

group = "ru.spbu.math-cs"
version = "1.0"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}