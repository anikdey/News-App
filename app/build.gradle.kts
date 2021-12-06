plugins {
    id( "com.android.application" )
    id( "kotlin-android" )
    id( "kotlin-kapt" )
    id( "kotlin-allopen" )
    id( "dagger.hilt.android.plugin" )
    id( "androidx.navigation.safeargs" )
}

allOpen {
    //annotation ("com.mobimeo.codingchallenge.test.OpenClass")
}

android {
    compileSdk = Config.compileSdkVersion
    buildToolsVersion = Config.buildToolsVersion


    buildFeatures {
        dataBinding = true
        viewBinding = true
    }

    defaultConfig {
        applicationId = Config.applicationID
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        //testInstrumentationRunner ("androidx.test.runner.AndroidJUnitRunner")
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

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
    }
}

dependencies {

    implementation(AndroidXSupportDependencies.coreKtx)
    implementation(AndroidXSupportDependencies.appCompat)
    implementation(MaterialDesignDependencies.materialDesign)
    implementation (AndroidXSupportDependencies.constraintLayout)

    //hilt
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // For local unit tests
    testImplementation("com.google.dagger:hilt-android-testing:2.38.1")

    //Room
    implementation(AndroidXSupportDependencies.room)
    kapt(AndroidXSupportDependencies.roomCompiler)
    implementation(AndroidXSupportDependencies.roomCoroutinesSupport)

    //Coroutines
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)

    // Navigation Components
    implementation(AndroidXSupportDependencies.navigationFragmentKtx)
    implementation(AndroidXSupportDependencies.navigationUI)

    //paging3
    implementation("androidx.paging:paging-runtime-ktx:3.0.1") //3.1.0
    testImplementation("androidx.paging:paging-common:3.0.1")

    // Coroutines lifecycle scopes
    //implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    //implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")

    implementation(Libraries.glide)
    kapt(Libraries.glideKapt)

    implementation(project(":core"))
    implementation(project(":network"))

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

    implementation(Libraries.ssp)
    implementation(Libraries.sdp)

    implementation(Libraries.loggingInterceptor) {
        this.exclude("org.json", "json")
    }

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

}