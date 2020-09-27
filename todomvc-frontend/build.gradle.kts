/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

dependencies {
    api(project(":todomvc-contract"))
    implementation(kotlin("stdlib-jdk8"))
}
