/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    implementation("org.apache.commons:commons-csv:1.8")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.0")
}
