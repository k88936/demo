val gdxVersion: String by project
val visUiVersion: String by project
plugins {
    java
}

group = "kvtodev"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    api("com.badlogicgames.gdx:gdx:$gdxVersion")
    api("com.kotcrab.vis:vis-ui:$visUiVersion")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
