plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.dokka")
    id("com.vanniktech.maven.publish")
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
        val androidMain by getting {
            dependencies {
                // Add Android-specific dependencies if needed
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val desktopMain by getting {
            dependencies {
                // Add Desktop-specific dependencies if needed
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.am.nativeprogressindicatorcmp"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
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
    // Remove or comment out Maven Central publishing
    // publishToMavenCentral(SonatypeHost.S01, automaticRelease = true)

    // Configure GitHub Packages repository
    publishToGitHubPackages("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressIndicatorCMP", "NativeProgressIndicatorCMP")

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
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
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
}

// GitHub authentication setup
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressIndicatorCMP")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

// Make sure your GitHub token is set in your environment or in gradle.properties file:
