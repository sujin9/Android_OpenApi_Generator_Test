plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.openapi.generator") version "7.0.1" apply true
}

buildscript {
    repositories {
        maven(url = "https://repo1.maven.org/maven2")
    }
}

tasks.register(
    "generateClient",
    org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class,
) {
    generatorName.set("kotlin")
    inputSpec.set("$rootDir/specs/petstore-v3.0.yaml")
    outputDir.set("$rootDir/network")
    packageName.set("com.example.network")
    apiPackage.set("com.example.network.api")
    modelPackage.set("com.example.network.model")
    invokerPackage.set("com.example.network.invoker")
    configOptions.set(
        mapOf(
            "library" to "jvm-retrofit2",
            "dateLibrary" to "java8",
            "omitGradleWrapper" to "true",
            "sourceFolder" to "src/main/java",
        ),
    )
}
