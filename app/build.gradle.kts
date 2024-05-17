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
    namespace = "com.k4tr1n4.mlteste"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    flavorDimensions.addAll(arrayOf("app"))

    productFlavors {
        create("mlb") {
            isDefault = true
            applicationId = "com.ml.brasil"
            dimension = "app"
            buildConfigField("String", "BASE_URL",
                    "\"https://api.mercadolibre.com/sites/MLB/\"")
            buildConfigField("String", "BASE_LANGUAGE", "\"pt\"")
            buildConfigField("String", "BASE_COUNTRY", "\"BR\"")
        }

        create("mla") {
            applicationId = "com.ml.argentina"
            dimension = "app"
            buildConfigField("String", "BASE_URL", "" +
                    "\"https://api.mercadolibre.com/sites/MLA/\"")
            buildConfigField("String", "BASE_LANGUAGE", "\"es\"")
            buildConfigField("String", "BASE_COUNTRY", "\"AR\"")
        }
    }

    sourceSets {
        getByName("mlb") {
            resources.srcDir("src/mlb/res")
        }

        getByName("mla") {
            resources.srcDir("src/mlb/res")
        }
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
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)
    implementation(libs.accompanist.swiperefresh)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // network
    implementation(libs.retrofit)
    implementation(libs.okhttp.interceptor)

    // di
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // navigation
    implementation(libs.compose.destinations.core)
    ksp(libs.compose.destinations.ksp)

    // room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp (libs.androidx.room.compiler)

    // test
    testImplementation(libs.turbine)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.junit)
    testImplementation(libs.okhttp.mockserver)
    testImplementation(libs.mockito.core)
    testImplementation(libs.core.testing)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk.android)
    testImplementation(libs.mockk.agent)

}