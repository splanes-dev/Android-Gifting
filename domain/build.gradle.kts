@file:Suppress("UnstableApiUsage")

android {
    namespace = "com.splanes.gifting.domain"
    compileSdk = 33
    buildTypes {
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