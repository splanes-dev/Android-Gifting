@file:Suppress("UnstableApiUsage")

buildscript {
    onEachModule {
        androidLibs.plugins.android.run {
            when (type) {
                ProjectType.Application -> application
                ProjectType.Library -> library
            }
        }.let { provider -> apply(provider = provider) }
        apply(provider = baseLibs.plugins.kotlin.android)
        apply(provider = baseLibs.plugins.kotlin.kapt)
        apply(provider = baseLibs.plugins.kotlin.parcelize)
        apply(provider = baseLibs.plugins.detekt)
        apply(provider = baseLibs.plugins.google.firebase.crashlytics)
        apply(provider = androidLibs.plugins.hilt)

        androidConfig {
            compileSdkVersion(33)
            defaultConfig {
                minSdk = 31
                compileSdkVersion(33)
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables { useSupportLibrary = true }
                consumerProguardFiles("consumer-rules.pro")
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        dependencies {
            implementation(baseLibs.bundles.core)
            implementation(androidLibs.bundles.core)
            kapt(androidLibs.android.dagger.hilt.compiler)
            implementation(androidLibs.bundles.hilt)
            implementation(platform(baseLibs.google.firebase.bom))
            implementation(baseLibs.google.firebase.analytics)
            implementation(baseLibs.google.firebase.crashlytics.asProvider())

            testImplementation(testLibs.bundles.core.unit)
            androidTestImplementation(testLibs.bundles.core.instrumental)
        }
    }
}
