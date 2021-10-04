dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( "https://plugins.gradle.org/m2/")
    }
}
rootProject.name = "Task5"
include (":app")