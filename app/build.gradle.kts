plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.openapi.generator")
}

android {
    namespace = "com.example.test"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.test"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    tasks.register(
        "generateClient",
        org.openapitools.generator.gradle.plugin.tasks.GenerateTask::class,
    ) {
        generatorName.set("kotlin")
        inputSpec.set("$rootDir/specs/petstore-v3.0.yaml")
        outputDir.set("$rootDir/app/src/main/java/com/example/test/network")
        packageName.set("com.example.network")
        apiPackage.set("com.example.network.api")
        modelPackage.set("com.example.network.model")
        invokerPackage.set("com.example.network.invoker")
        configOptions.set(
            mapOf(
                "library" to "jvm-retrofit2",
                "dateLibrary" to "java8",
            ),
        )
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
