plugins {
    id("maven-publish")
    kotlin("jvm") version "1.8.21"
    application
}

group = "com.alcano"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "com.alcano"
            artifactId = project.name
            version = "1.0.0"
        }
    }
    repositories {
        mavenLocal()
    }
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}