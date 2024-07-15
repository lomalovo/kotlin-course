dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("reflect"))
    implementation(project(":task2"))
    testImplementation(project(":task2"))
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