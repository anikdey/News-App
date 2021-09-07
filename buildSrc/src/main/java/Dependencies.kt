/**
 * To define plugins
 */
object BuildPlugins {
    val androidBuildTools by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlinPlugins by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }

    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val navigationSafeArg by lazy { "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}" }
    const val allOpenPlugin = "org.jetbrains.kotlin:kotlin-allopen:${Versions.allOpen}"
}

object KotlinDependencies {

    val kotlinStd by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}" }

}

object AndroidXSupportDependencies {

    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activityKtx}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}" }

    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }


    // room
    val room by lazy { "androidx.room:room-runtime:${Versions.roomVersion}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.roomVersion}" }
    val roomCoroutinesSupport by lazy { "androidx.room:room-ktx:${Versions.roomVersion}" }
    val roomRxJava2Support by lazy { "androidx.room:room-rxjava2:${Versions.roomVersion}" }
    val roomRxJava3Support by lazy { "androidx.room:room-rxjava3:${Versions.roomVersion}" }


    // Navigation
    val navigationFragmentKtx by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}" }
    val navigationUI by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}" }

}

object MaterialDesignDependencies {

    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }

}

object Libraries {

    // retrofit
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}" }
    val retrofitMoshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}" }
    val retrofitRxAdapter by lazy { "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofitVersion}" }

    // moshi
    val moshi by lazy { "com.squareup.moshi:moshi:${Versions.moshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter}" }

    // stetho - network interceptor
    val stetho by lazy { "com.facebook.stetho:stetho:${Versions.stetho}" }
    val stethoOkhttp by lazy { "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}" }
    val stethoJSRhino by lazy { "com.facebook.stetho:stetho-js-rhino:${Versions.stetho}" }

    // coroutines & coroutines-android
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}" }

    //rxjava and rxAndroid
    val rxJava by lazy { "io.reactivex.rxjava3:rxjava:${Versions.rxJava}" }
    val rxAndroid by lazy { "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}" }


    val sdp by lazy { "com.intuit.sdp:sdp-android:${Versions.sdpssp}" }
    val ssp by lazy { "com.intuit.ssp:ssp-android:${Versions.sdpssp}" }

    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val glideKapt by lazy { "com.github.bumptech.glide:compiler:${Versions.glide}" }

    // hilt
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }
    val hiltNavigationFragment by lazy { "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}" }

}


