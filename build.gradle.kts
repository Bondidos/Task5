// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven ("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.2")
        classpath  ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

plugins{
    id ("io.gitlab.arturbosch.detekt") version "1.10.0"
    id ("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

subprojects {
    apply (plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint{
        "debug" to false
    }
}

detekt {
    toolVersion = "1.10.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    failFast = true

    input = files("app/src/main/java", "app/src/main/kotlin")

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}