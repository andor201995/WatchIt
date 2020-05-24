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

    const val gradle_build = "3.6.3"
    const val compileSdk = 29
    const val minSdk = 16
    const val targetSdk = 29
    const val build_tool = "29.0.2"
    const val kotlin = "1.3.72"
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
    const val logging_interceptor = "3.11.0"

    const val mockwebserver = "4.3.0"
    const val java = "1.8"

    const val rxjava = "2.2.17"
    const val rxandroid = "2.1.0"
    const val adapter_rxjava = "2.4.0"
    const val rxbinding = "3.1.0"

    const val shimmer = "0.4.0"
    const val picasso = "2.71828"
    const val glide = "4.11.0"

    const val pagination = "2.1.1"
    const val zoomage = "1.3.0-SNAPSHOT"

    const val room = "2.2.5"
    const val coroutine = "1.3.5"
}

object Libraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

    //AndroidX
    const val appcompat = "androidx.appcompat:appcompat:${Version.androidx}"
    const val androidxcore = "androidx.core:core-ktx:${Version.androidx}"
    const val pagination = "androidx.paging:paging-runtime:${Version.pagination}"
    const val pagination_rxjava = "androidx.paging:paging-rxjava2:${Version.pagination}"


    //UI
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Version.constraint_layout}"

    //Navigation
    const val navigation_runtime =
        "androidx.navigation:navigation-runtime-ktx:${Version.navigation_component}"
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation_component}"
    const val navigation_ui =
        "androidx.navigation:navigation-ui-ktx:${Version.navigation_component}"

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
    const val dagger_processor = "com.google.dagger:dagger-android-processor:${Version.dagger}"

    //Retrofit
    const val gson_converter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.logging_interceptor}"
    const val adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava2:${Version.adapter_rxjava}"


    //Rx-Java
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Version.rxjava}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Version.rxandroid}"
    const val rxbinding_appcompact =
        "com.jakewharton.rxbinding3:rxbinding-appcompat:${Version.rxbinding}"
    const val rxbinding_core = "com.jakewharton.rxbinding3:rxbinding-core:${Version.rxbinding}"
    const val rxbinding_platform = "com.jakewharton.rxbinding3:rxbinding:${Version.rxbinding}"


    //FB shimmer
    const val shimmer = "com.facebook.shimmer:shimmer:${Version.shimmer}"
    const val shimmer_recyclerview = "com.todkars:shimmer-recyclerview:${Version.shimmer}"

    //Picasso
    const val picasso = "com.squareup.picasso:picasso:${Version.picasso}"

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Version.glide}"

    //Zoomage
    const val zoomage = "com.jsibbold:zoomage:${Version.zoomage}"

    //Room
    const val room_compiler = "androidx.room:room-compiler:${Version.room}"
    const val room_ktx = "androidx.room:room-ktx:${Version.room}"

    //Coroutine
    const val coroutine_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
    const val coroutine_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"


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