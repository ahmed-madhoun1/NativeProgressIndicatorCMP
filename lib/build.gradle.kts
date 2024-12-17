plugins {
    kotlin("multiplatform") version "1.9.10" // Replace with your Kotlin version
    id("com.android.library") version "8.1.0" // Replace with your Android plugin version
    id("org.jetbrains.compose") version "1.5.1" // Replace with your Compose Multiplatform plugin version
    id("com.vanniktech.maven.publish") version "0.25.3" // Replace with your Maven Publish plugin version
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "lib"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val androidMain by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val desktopMain by getting
    }
}

android {
    compileSdk = 33 // Replace with your compile SDK version
    namespace = "com.am.nativeprogressindicatorcmp"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = 21 // Replace with your minimum SDK version
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

mavenPublishing {
    signAllPublications()
    coordinates("com.am.nativeprogressindicatorcmp", "nativeprogressindicatorcmp-runtime", "1.0.0")

    pom {
        name.set(project.name)
        description.set("A simple Kotlin Multiplatform library to show native progress indicators on Android and iOS.")
        inceptionYear.set("2024")
        url.set("https://github.com/ahmed-madhoun1/nativeprogressindicatorcmp/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("ahmed-madhoun1")
                name.set("Ahmed Madhoun")
                url.set("https://github.com/ahmed-madhoun1/")
            }
        }
        scm {
            url.set("https://github.com/ahmed-madhoun1/nativeprogressindicatorcmp/")
            connection.set("scm:git:git://github.com/ahmed-madhoun1/nativeprogressindicatorcmp.git")
            developerConnection.set("scm:git:ssh://git@github.com/ahmed-madhoun1/nativeprogressindicatorcmp.git")
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressIndicatorCMP")
            credentials {
                username = findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = findProperty("gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
