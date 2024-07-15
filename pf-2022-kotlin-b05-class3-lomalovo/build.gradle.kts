import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.spbu.math-cs"
version = "1.0"

allprojects {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.5.20" apply false
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}