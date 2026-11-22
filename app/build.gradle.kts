plugins {
    id("com.android.application")
}

android {
    namespace = "io.github.fakeskyline.youtubefusion"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.github.fakeskyline.youtubefusion"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    compileOnly("io.github.libxposed:api:102.0.0")
}
