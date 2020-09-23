/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

plugins {
    application
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

application {
    mainClassName = "de.muspellheim.todomvc.App"
}

dependencies {
    implementation(project(":todomvc-backend"))
    implementation(project(":todomvc-frontend"))
    implementation(kotlin("stdlib-jdk8"))
}
