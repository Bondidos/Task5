buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.31")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}
subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    ktlint {
        debug.set(false)
    }
}
task.register.detekt {
    toolVersion = "1.18.1"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
    allRules = true

    source = files("app/src/main/java", "app/src/main/kotlin")

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
