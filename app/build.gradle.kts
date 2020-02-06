plugins {
    application
    kotlin("jvm")
}

application {
    mainClassName = "cli.Main"
}

dependencies {
    implementation(project(":head"))
    implementation(project(":body"))
}
