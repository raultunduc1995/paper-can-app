pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        includeBuild("./gradle/platform")
    }
}
rootProject.name = "PaperScanApp"
include(":app")
