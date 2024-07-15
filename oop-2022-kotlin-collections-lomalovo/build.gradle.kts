import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.20"
    application
}

group = "ru.spbu.math-cs"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit"))
    testImplementation("com.github.cretz.kastree:kastree-ast-jvm:0.4.0")
    testImplementation("com.github.cretz.kastree:kastree-ast-psi:0.4.0")
}

tasks.test {
    useJUnit()
}


application {
    mainClass.set("MainKt")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}