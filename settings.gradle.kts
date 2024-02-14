rootProject.name = "TheMovieDB"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

include(":composeApp")
include(":apis:movie")
include(":features:home")
include(":features:favorite")
include(":features:moviedetail")
include(":features:movielist")
include(":libraries:component")
include(":libraries:core")
