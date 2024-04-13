@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.k4tr1n4.marvelcomics"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.k4tr1n4.marvelcomics"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", "\"b7e14bab409c70a5c4e7c2b319c09d7b\"")
        buildConfigField("String", "TS", "\"1682982412\"")
        buildConfigField("String", "HASH", "\"3482f01e9bf207a437a4b621c91339ad\"")
        buildConfigField("String", "MARVEL_BASE_URL", "\"https://gateway.marvel.com/v1/public/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.extension.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kapt {
        javacOptions {
            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
        }
    }
    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildFile, "generated/ksp/$name/kotlin")
        )
    }
}

dependencies {
    // core
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.appcompat)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // network
    implementation(libs.retrofit)
    implementation(libs.okhttp.interceptor)
    testImplementation(libs.okhttp.mockserver)

    // di
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    //navigation
    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    // unit test/*testImplementation(libs.junit)
    //    testImplementation(libs.turbine)
    //    testImplementation(libs.androidx.test.core)
    //    testImplementation(libs.mockito.kotlin)
    //    testImplementation(libs.mockito.inline)
    //    testImplementation(libs.coroutines.test)
    //    androidTestImplementation(libs.truth)
    //    androidTestImplementation(libs.androidx.junit)
    //    androidTestImplementation(libs.androidx.espresso)
    //    androidTestImplementation(libs.android.test.runner)*/

}