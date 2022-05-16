object Hilt {
    const val hiltVersion = "2.41"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val daggerCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
//    const val lifecycleVm = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:1.0.0"
}

object HiltTest {
    const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${Hilt.hiltVersion}"
}