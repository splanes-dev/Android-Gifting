@file:Suppress("UnstableApiUsage")

android {
    namespace = "com.splanes.gifting.data"
    compileSdk = 33
    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.0-alpha02"
    }
}

dependencies {
    implementation(projects.domain)
    implementation(baseLibs.gson)
    implementation(androidLibs.android.security.crypto)
    implementation(baseLibs.google.firebase.auth)
    implementation(baseLibs.google.firebase.db)
}
