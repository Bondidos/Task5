dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
       // maven ("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
    }
}
rootProject.name = "Task5"
include (":app")