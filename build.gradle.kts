/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    base
    kotlin("jvm") version "1.3.61" apply false
    id("org.jlleitschuh.gradle.ktlint") version "9.1.1" apply false
}

subprojects {
    repositories {
        jcenter()
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }
}
