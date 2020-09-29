/*
 * TodoMVC
 * Copyright (c) 2020 Falko Schumann
 */

plugins {
    java
    distribution
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

ext["mainClassName"] = "de.muspellheim.todomvc.App"

dependencies {
    implementation(project(":todomvc-backend"))
    implementation(project(":todomvc-frontend"))
    implementation(kotlin("stdlib-jdk8"))
}

tasks.register<JavaExec>("run") {
    group = "application"
    main = ext["mainClassName"].toString()
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register<Jar>("uberJar") {
    group = "distribution"
    archiveClassifier.set("uber")
    manifest {
        attributes(
            "Implementation-Title" to "Todo MVC",
            "Implementation-Version" to archiveVersion,
            "Implementation-Vendor" to "Falko Schumann <falko.schumann@muspellheim.de>",
            "Main-Class" to ext["mainClassName"]
        )
    }
    from(sourceSets.main.get().output)
    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

distributions {
    main {
        contents {
            from(tasks.findByName("uberJar"))
            from(rootDir) {
                include("README.md")
                include("LICENSE.txt")
                include("CHANGELOG.md")
                rename("md", "txt")
            }
        }
    }
}
