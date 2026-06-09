val pluginName = "Clean Architecture Helper"
val pluginGroup = "com.github.tranvu"
val pluginVersion = "1.0.0"
val ideaVersion = "2023.3"

val vendorName = "Vincent"
val vendorEmail = "vincent@gmail.com"
val vendorUrl = "https://vincent.com"

val publishChannels = "stable"
val certificateChain = ""
val privateKey = ""
val privateKeyPassword = ""
val publishToken = ""

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij.platform") version "2.5.0"
}

group = pluginGroup
version = pluginVersion

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html#setting-up-intellij-platform
dependencies {
    intellijPlatform {
        intellijIdeaCommunity(ideaVersion, useInstaller = false)
        bundledPlugin("com.intellij.java")
    }
}

intellijPlatform {
    pluginConfiguration {
        id = pluginGroup
        name = pluginName
        version = pluginVersion
        changeNotes.set("""
            <h1>Changelog</h1>
            <h2>v1.0.0</h2>
            <h3>Release Notes – Flutter Clean Architecture Helper</h3>
            <ul>
                <li>First stable release of the plugin</li>
                <li>Generates a full-featured Flutter Clean Architecture folder structure</li>
                <li>Supports optional root folder naming</li>
                <li>Supports optional split for local and remote data sources</li>
            </ul>
            """.trimIndent())
        ideaVersion {
            sinceBuild = "233"
            untilBuild = "271.*"
        }
        vendor {
            name = vendorName
            email = vendorEmail
            url = vendorUrl
        }
    }

    signing {
        certificateChain.set(System.getProperty("CERTIFICATE_CHAIN"))
        privateKey.set(System.getProperty("PRIVATE_KEY"))
        password.set(System.getProperty("PRIVATE_KEY_PASSWORD"))
    }

    publishing {
        token.set(System.getProperty("PUBLISH_TOKEN"))
        channels.set(listOf(publishChannels)) // Opcional si usas canales como "stable", "eap"
    }
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}

