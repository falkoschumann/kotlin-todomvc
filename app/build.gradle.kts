plugins {
    application
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

application {
    mainClassName = "cli.Main"
}

dependencies {
    implementation(project(":head"))
    implementation(project(":body"))
}
