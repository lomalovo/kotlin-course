dependencies {
    testImplementation(kotlin("test"))
}

plugins{
    kotlin("jvm")
    application
}

tasks.test {
    useJUnit()
}

application {
    mainClass.set("MainKt")
}