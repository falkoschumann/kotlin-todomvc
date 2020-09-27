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
    implementation("com.google.code.gson:gson:2.8.6")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.0")
}
