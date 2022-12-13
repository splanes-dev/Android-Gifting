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
    kotlinOptions {
        jvmTarget = "11"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-beta01"
    }
}

dependencies {
    implementation(projects.domain)
    implementation(baseLibs.gson)
    implementation(androidLibs.android.security.crypto)
}
