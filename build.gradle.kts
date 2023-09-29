plugins {
    id("org.jetbrains.intellij") version "1.15.0"
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
}

repositories {
    mavenCentral()
}

intellij {
    version.set("IU-LATEST-EAP-SNAPSHOT")
    plugins.set(listOf("JavaScript", "com.intellij.properties", "org.jetbrains.idea.maven"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val KOTLIN_JVM_TARGET = "17"
val KOTLIN_API_VERSION = "1.8"

tasks {
    patchPluginXml {
        sinceBuild.set("231")
    }

    buildSearchableOptions {
        enabled = false
    }

    compileKotlin {
        kotlinOptions {
            jvmTarget = KOTLIN_JVM_TARGET
            apiVersion = KOTLIN_API_VERSION
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = KOTLIN_JVM_TARGET
            apiVersion = KOTLIN_API_VERSION
        }
    }
}