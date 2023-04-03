plugins {
    id("java")
    id("maven-publish")
}

group = "com.uroria"
version = "0.0.1"

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

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/uroria/betterteams")
            credentials {
                username = System.getenv("USERNAME")
                password = System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}