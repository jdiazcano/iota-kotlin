## IOTA Kotlin client library

Unofficial client library for IOTA written in Kotlin, based on [the official Java library](https://github.com/iotaledger/iota-java)
which (for now) allows you to:

1. Interact with an IRI node.
1. ... more to come.

## Getting started

Thanks to [Jitpack](https://jitpack.io/#jdiazcano/iota-kotlin), there is an easy way to use it with Gradle.

In case you want to use it with Maven, refer to the [Jitpack web]()

1. Add Jitpack repository
    ```groovy
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency in your project

    ```groovy
    dependencies {
        compile 'com.github.jdiazcano:iota-kotlin:master-SNAPSHOT'
    }
    ```
    
    You might change the branch!
    
3. Create a Kotlin class and call the service.

    ```kotlin
    fun main() {
        val service = IotaService()
    
        runBlocking {
            val response = service.getNeighbors()
            println(response)
        }
    }
    ```