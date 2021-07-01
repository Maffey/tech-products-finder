val junit: String by project
val selenium: String by project
val assertj: String by project
val opencsv: String by project
val log4j: String by project
val cucumber: String by project

plugins {
    application
    idea
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("org.seleniumhq.selenium:selenium-java:$selenium")
    implementation("com.opencsv:opencsv:$opencsv")
    implementation("org.apache.logging.log4j:log4j-core:$log4j")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:${junit}")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:$junit")
    testImplementation ("org.junit.jupiter:junit-jupiter-params:$junit")
    testImplementation ("org.junit.vintage:junit-vintage-engine:$junit")

    testImplementation("org.assertj:assertj-core:$assertj")

    testImplementation("io.cucumber:cucumber-java8:$cucumber")
    testImplementation("io.cucumber:cucumber-junit:$cucumber")
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}