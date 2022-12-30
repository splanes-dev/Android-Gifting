plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

dependencies {
    val baseCatalog = versionCatalogs.named("baseLibs")
    val androidCatalog = versionCatalogs.named("androidLibs")

    implementation(baseCatalog.findLibrary("kotlin-gradle").get())
    implementation(baseCatalog.findLibrary("detekt-gradle").get())
    implementation(baseCatalog.findLibrary("google-services-gradle").get())
    implementation(baseCatalog.findLibrary("google-firebase-crashlytics-gradle").get())
    implementation(androidCatalog.findLibrary("android-gradle").get())
    implementation(androidCatalog.findLibrary("android-dagger-hilt-gradle").get())
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
        kotlinOptions.freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}
