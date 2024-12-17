import com.vanniktech.maven.publish.SonatypeHost

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
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

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
                        email.set("ahmed2madhoun2@gmail.com") // Update this with your email address
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
