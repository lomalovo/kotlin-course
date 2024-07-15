plugins {
    kotlin("jvm")
    application
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnit()
}

application {
    mainClass.set("MainKt")
}