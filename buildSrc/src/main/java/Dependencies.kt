object ApplicationId {
    const val id = "com.andor.watchit"
}

object Modules {
    const val app = ":app"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Version {

    const val gradle_build = "3.5.3"
    const val compileSdk = 29
    const val minSdk = 16
    const val targetSdk = 29
    const val build_tool = "29.0.2"
    const val kotlin = "1.3.61"
    const val androidx = "1.1.0"
    const val junit_ext = "1.1.1"
    const val androidx_legacy = "1.0.0"
    const val androidx_lifecycle = "2.1.0"
    const val constraint_layout = "1.1.3"
    const val junit = "4.13"
    const val expresso = "3.2.0"
    const val navigation_component = "2.1.0"
    const val dagger = "2.25.4"
    const val mockito_kotlin = "2.1.0"
    const val retrofit = "2.7.1"
    const val mockwebserver = "4.3.0"
    const val java = "1.8"
    const val rxjava = "2.2.17"
    const val shimmer = "0.5.0@aar"
    const val picasso = "2.71828"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

    //AndroidX
    const val appcompat = "androidx.appcompat:appcompat:${Version.androidx}"
    const val androidxcore = "androidx.core:core-ktx:${Version.androidx}"

    //UI
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraint_layout}"

    //Navigation
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment:${Version.navigation_component}"
    const val navigation_ui = "androidx.navigation:navigation-ui:${Version.navigation_component}"

    //ViewModel
    const val legacy = "androidx.legacy:legacy-support-v4:${Version.androidx_legacy}"
    const val lifecycle_extensions =
        "androidx.lifecycle:lifecycle-extensions:${Version.androidx_lifecycle}"
    const val lifecycle_viewmodel_ktx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.androidx_lifecycle}"

    //Dagger2
    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val dagger_android = "com.google.dagger:dagger-android:${Version.dagger}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Version.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"

    //Retrofit
    const val gson_converter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"

    //Rx-Java
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Version.rxjava}"

    //FB shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Version.shimmer}"

    //Picasso
    const val picasso = "com.squareup.picasso:picasso:${Version.picasso}"

}

object TestLibraries {
    //Testing
    const val junit = "junit:junit:${Version.junit}"
    const val junit_ext = "androidx.test.ext:junit:${Version.junit_ext}"

    //Automation
    const val espresso = "androidx.test.espresso:espresso-core:${Version.expresso}"

    //Mocking
    const val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Version.mockito_kotlin}"
    const val mockwebserver = "com.squareup.okhttp3:mockwebserver:${Version.mockwebserver}"
}