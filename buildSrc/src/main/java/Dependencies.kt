object Dependencies {

    // Essentials
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }

    // Lifecycle Components
    val lifecycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}" }

    // Room
    val roomRuntime by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }

    // Dagger Hilt
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}" }
    val hiltCompiler by lazy { "com.google.dagger:hilt-compiler:${Versions.hilt}" }

    // Compose
    val composeActivity by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val composeUi by lazy { "androidx.compose.ui:ui:${Versions.composeUi}" }
    val composeUiUtil by lazy { "androidx.compose.ui:ui-util:${Versions.composeUi}" }
    val composeUiPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeUi}" }
    val composeMaterial by lazy { "androidx.compose.material:material:${Versions.composeMaterial}" }
    val composeMaterialIcons by lazy { "androidx.compose.material:material-icons-core:${Versions.composeMaterial}" }
    val composeMaterialIconsExtended by lazy { "androidx.compose.material:material-icons-extended:${Versions.composeMaterial}" }
    val composeMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.composeMaterial3}" }
    val composeNavigation by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val composeRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}" }
    val composeViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}" }

    // Splash Screen
    val splahScreen by lazy { "androidx.core:core-splashscreen:${Versions.splahScreen}" }

    // Library
    val composeProgressItem by lazy { "com.github.Hyuck9:ComposeProgressItem:${Versions.progressItem}" }
    val splittiesResources by lazy { "com.louiscad.splitties:splitties-resources:${Versions.splittiesResources}" }

    // Timber Logging
    val timber by lazy { "com.jakewharton.timber:timber:${Versions.timber}" }

    // Testing
    val junit by lazy { "junit:junit:${Versions.junit}" }
    val junitTest by lazy { "androidx.test.ext:junit:${Versions.junitTest}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoTest}" }
    val junitCompose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.composeUi}" }
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.composeUi}" }

}