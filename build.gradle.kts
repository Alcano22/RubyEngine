plugins {
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.alcano"
version = "1.0.0"

repositories {
    mavenCentral()
}



kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}