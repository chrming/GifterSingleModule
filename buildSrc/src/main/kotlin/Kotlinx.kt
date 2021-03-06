object Kotlinx {
    private const val kotlinxDatetimeVersion = "0.3.2"
    const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDatetimeVersion"

    private const val coroutinesCoreVersion = "1.6.1"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesCoreVersion"

    private const val coroutinesAndroidVersion = "1.6.1"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesAndroidVersion"

    // Need for tests. Plugin doesn't work.
    private const val serializationVersion = "1.3.2"
    const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion"
}