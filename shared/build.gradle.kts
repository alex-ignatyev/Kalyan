plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.cocoapods)
    alias(libs.plugins.compose)
    alias(libs.plugins.libres)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.sqlDelight)
    //TODO add Ktor
    alias(libs.plugins.kotlinx.serialization)
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

                implementation(libs.odyssey.core)
                implementation(libs.odyssey.compose)

                implementation(libs.klock.common)

                implementation(libs.kviewmodel.core)
                implementation(libs.kviewmodel.compose)
                implementation(libs.kviewmodel.odyssey)

                implementation(libs.kodein)
                implementation("io.github.skeptick.libres:libres-compose:1.1.8")
                implementation(libs.kotlin.serialization)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.7.0")
                implementation(libs.sqldelight.android)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(libs.sqldelight.native)
            }
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
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
    baseLocaleLanguageCode = "en"
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

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