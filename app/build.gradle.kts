import consts.CompileVersions
import consts.Dependencies

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

val COMPOSE_VERSION = "1.3.0"

android {
    compileSdk = CompileVersions.CURRENT_COMPILE_VERSION

    namespace = "io.lostimagin4tion.sovcombankchallenge"

    defaultConfig {
        applicationId = namespace!!
        minSdk = CompileVersions.MINIMUM_COMPILE_VERSION
        targetSdk = CompileVersions.CURRENT_COMPILE_VERSION
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = CompileVersions.JAVA_COMPILE_VERSION
        targetCompatibility = CompileVersions.JAVA_COMPILE_VERSION
    }

    kotlinOptions {
        jvmTarget = CompileVersions.JAVA_COMPILE_VERSION_STR
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = CompileVersions.COMPOSE_COMPILE_VERSION
    }

    lint {
        abortOnError = false
    }
}

dependencies {

    // Android Core
    Dependencies.AndroidCore.ALL_DEPS.forEach { implementation(it) }

    // Coroutines
    implementation(Dependencies.Coroutines.CORE)

    // Compose
    Dependencies.Compose.ALL_DEPS.forEach { implementation(it) }
    Dependencies.Compose.Core.ALL_CORE_DEBUG_DEPS.forEach { debugImplementation(it) }

    // UI
    Dependencies.UI.ALL_DEPS.forEach { implementation(it) }

    // Pie Chart
    implementation(Dependencies.PieChart.PIE_CHART)

    // Dagger
    implementation(Dependencies.Dagger.ANDROID)
    Dependencies.Dagger.KAPT_DEPS.forEach { kapt(it) }

    // Timber
    implementation(Dependencies.Logger.TIMBER)

    // Retrofit
    Dependencies.Retrofit.ALL_DEPS.forEach { implementation(it) }

    // Tests
    allTestImplementation(kotlin(Dependencies.Test.TEST_JUNIT))
    allTestImplementation(Dependencies.Test.JUNIT)
    testImplementation(Dependencies.Test.MOCKK)
}

fun DependencyHandler.allTestImplementation(deps: Any) {
    testImplementation(deps)
    androidTestImplementation(deps)
}