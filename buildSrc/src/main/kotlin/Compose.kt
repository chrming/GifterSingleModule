object Compose {
    private const val activityComposeVersion = "1.4.0"
    const val activity = "androidx.activity:activity-ktx:$activityComposeVersion"

    const val composeVersion = "1.2.0-alpha08"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val uiPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
    const val compiler = "androidx.compose.compiler:compiler:$composeVersion"

    private const val lifecycleVmVersion = "2.4.1"
    const val lifecycleVm = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVmVersion"

    private const val navigationVersion = "2.4.2"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}

object ComposeTest {
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
}
