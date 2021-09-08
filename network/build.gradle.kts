import Versions.allOpen

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-allopen")
    id("dagger.hilt.android.plugin")
}

allOpen {
    annotation ("com.mobimeo.codingchallenge.test.OpenClass")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion

//        versionCode = Config.versionCode
//        versionName = Config.versionName

        //testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation(KotlinDependencies.kotlinStd)

    //hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.38.1")

    //retrofit
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    implementation(Libraries.retrofitRxAdapter)

    //moshi
    implementation(Libraries.moshi)
    implementation(Libraries.moshiConverter)
    kapt(Libraries.moshiKotlinCodegen)

    //Stetho https://github.com/facebook/stetho
    implementation (Libraries.stetho)
    implementation (Libraries.stethoOkhttp)
    implementation (Libraries.stethoJSRhino)

    implementation(Libraries.loggingInterceptor) {
        this.exclude("org.json", "json")
    }

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}