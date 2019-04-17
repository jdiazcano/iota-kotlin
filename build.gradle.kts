import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URI

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
    compile(Libraries.bouncycastle)

    testCompile(Libraries.kotlintest.core)
    testCompile(Libraries.kotlintest.junitrunner)

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

java.sourceSets["test"].resources {
    setSrcDirs(listOf("tstres"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        // set options for log level LIFECYCLE
        events = setOf(FAILED, PASSED, SKIPPED, STANDARD_OUT)
        exceptionFormat = TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
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