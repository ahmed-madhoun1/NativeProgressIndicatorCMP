plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") // For KMP serialization if needed
    id("com.android.library")
    id("maven-publish") // For publishing to GitHub
}

kotlin {
    android()
    iosX64() // iOS simulator target
    iosArm64() // iOS device target
    // Add other targets as necessary (such as js, macosX64, etc.)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("androidx.compose.ui:ui:1.5.0") // Use the appropriate Compose version
                implementation("androidx.compose.material:material:1.5.0") // Material Compose
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2") // Optional Coroutines dependency
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.compose.ui:ui-tooling-preview:1.5.0") // For Android-specific tooling
            }
        }
        val iosMain by getting {
            dependencies {
                // Add iOS-specific libraries or dependencies here if needed
            }
        }
    }
}

android {
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        targetSdk = 34
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["android"])

            groupId = "com.am.nativeprogressindicatorcmp" // Updated package name
            artifactId = "NativeProgressIndicatorCMP"
            version = "1.0.0" // Version of your library

            pom {
                name.set("NativeProgressIndicatorCMP")
                description.set("A Compose Multiplatform library for native progress indicators")
                url.set("https://github.com/ahmed-madhoun1/NativeProgressIndicatorCMP")

                scm {
                    url.set("https://github.com/ahmed-madhoun1/NativeProgressIndicatorCMP.git")
                    connection.set("scm:git:git://github.com/ahmed-madhoun1/NativeProgressIndicatorCMP.git")
                    developerConnection.set("scm:git:git@github.com:ahmed-madhoun1/NativeProgressIndicatorCMP.git")
                }
                licenses {
                    license {
                        name.set("Apache 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        id.set("ahmed-madhoun1")
                        name.set("Ahmed Madhoun")
                        email.set("your-email@example.com") // Update this with your email address
                    }
                }
            }
        }
    }

    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/ahmed-madhoun1/NativeProgressIndicatorCMP")
            credentials {
                username = project.findProperty("gpr.user") ?: System.getenv("USERNAME_GITHUB")
                password = project.findProperty("gpr.token") ?: System.getenv("TOKEN_GITHUB")
            }
        }
    }
}
