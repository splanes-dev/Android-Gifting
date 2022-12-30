@file:Suppress("UnstableApiUsage")

android {
    namespace = "com.splanes.gifting.ui"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-beta01"
    }
}

dependencies {
    implementation(projects.domain)

    implementation(androidLibs.android.lifecycle)
    implementation(androidLibs.bundles.compose)
    implementation(androidLibs.android.compose.lottie)
    implementation(androidLibs.android.compose.navigation)
    implementation(androidLibs.android.accompanist.navigation.anim)
    implementation(androidLibs.android.accompanist.systemuicontroller)
    implementation(androidLibs.android.accompanist.pager)
    implementation(androidLibs.android.accompanist.pager.indicators)
    implementation(androidLibs.android.security.biometric)
    implementation(androidLibs.android.security.biometric.ktx)

    testImplementation(testLibs.bundles.core.unit)
    androidTestImplementation(testLibs.bundles.core.instrumental)
    androidTestImplementation(testLibs.compose.instrumental)

    debugImplementation(testLibs.bundles.compose.debug)
}
