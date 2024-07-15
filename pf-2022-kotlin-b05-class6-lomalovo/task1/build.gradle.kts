dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.microutils:kotlin-logging-jvm:2.0.11")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.29")
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