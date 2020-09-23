/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10" apply false
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0" apply false
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
