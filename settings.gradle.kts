// Remove `@file:Suppress("UnstableApiUsage")` once Version Catalogs has had released as @Stable
@file:Suppress("UnstableApiUsage")

enableFeaturePreview("VERSION_CATALOGS")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

apply(from = "gradle/dependency-catalogs.settings.gradle.kts")

rootProject.name = "Gifting"
include(":app")
include(":domain")
include(":data")
include(":ui")