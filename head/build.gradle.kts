plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

dependencies {
    api(project(":body"))
}
