plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.compose)
    alias(libs.plugins.libres)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)
}

version = "1.0-SNAPSHOT"

kotlin {
    android()

    ios()
    iosSimulatorArm64()

    cocoapods {
        summary = "Shared Code"
        homepage = "https://github.com/alex-ignatyev/Kalyan"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")

        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                implementation(libs.compose.imageLoader)
                //implementation(libs.compose.imageLoaderBlur)
                implementation(libs.compose.icons)

                implementation(libs.kviewmodel.core)
                implementation(libs.kviewmodel.compose)
                implementation(libs.kviewmodel.odyssey)

                implementation(libs.odyssey.core)
                implementation(libs.odyssey.compose)

                implementation(libs.klock.common)

                implementation(libs.kodein)
                implementation(libs.koin)

                implementation(libs.libres)

                implementation(libs.napier)

                implementation(libs.ktor.core)
                implementation(libs.ktor.negotiation)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)

                implementation(libs.multiplatformSettings)

                implementation(libs.kstore)

                implementation(libs.kotlin.serialization)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.7.0")
                implementation ("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
                implementation(libs.sqldelight.android)
                implementation(libs.ktor.android)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.ktor.ios)
                implementation(libs.sqldelight.ios)
            }
        }
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("")
            schemaOutputDirectory.set(file("src/commonMain/sqldelight/data/schema"))
            migrationOutputDirectory.set(file("src/commonMain/sqldelight/migrations"))
        }
    }
}

libres {
    generatedClassName = "AppRes"
    generateNamedArguments = true
    baseLocaleLanguageCode = "ru"
}

android {
    compileSdk = 33

    namespace = "com.kalyan.shared"

    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
