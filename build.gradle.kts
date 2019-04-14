import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

import java.net.URI;

plugins {
    java
    kotlin("jvm") version Versions.kotlin
}

group = "com.jdiazcano"
version = "1.0-SNAPSHOT"
val mainClass = ""

repositories {
    mavenCentral()
    jcenter()
    maven { url = URI("https://kotlin.bintray.com/kotlinx") }
}

dependencies {
    compile(kotlin("stdlib-jdk8", Versions.kotlin))

    compile(Libraries.logging)
    compile(Libraries.log4j.log4j2)
    compile(Libraries.log4j.log4j2slf4j)
    compile(Libraries.ktor.client.cio)
    compile(Libraries.ktor.client.gson)
    compile(Libraries.ktor.client.loggingjvm)


}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

java.sourceSets["main"].java {
    setSrcDirs(listOf("src"))
}

java.sourceSets["main"].resources {
    setSrcDirs(listOf("res"))
}

java.sourceSets["test"].java {
    setSrcDirs(listOf("tst"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = mainClass
    }
    from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}