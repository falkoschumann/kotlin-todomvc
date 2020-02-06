plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    implementation("org.apache.commons:commons-csv:1.8")
}
