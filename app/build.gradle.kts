plugins {
    id("application")
    id("java")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.0")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("hexlet.code.App")
}