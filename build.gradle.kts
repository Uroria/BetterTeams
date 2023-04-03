plugins {
    id("java")
}

group = "com.uroria"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.purpurmc.purpur", "purpur-api", "1.19.4-R0.1-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}