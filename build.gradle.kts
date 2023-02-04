buildscript {

    dependencies {
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.navigation)
        classpath(BuildPlugins.daggerHilt)
    }
}

plugins {
    id("com.android.application") version "7.4.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
}
