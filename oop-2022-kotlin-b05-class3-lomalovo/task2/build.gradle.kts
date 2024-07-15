dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
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