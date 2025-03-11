import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Общие зависимости для всех платформ
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.core.ktx.v1120)
                implementation(libs.androidx.ui) // Замените на актуальную версию
                implementation(libs.material) // Замените на актуальную версию
                implementation(libs.androidx.ui.tooling.preview) // Замените на актуальную версию
                implementation(libs.androidx.activity.compose.v160) // Замените на актуальную версию
            }
        }
    }
}

android {
    namespace = "com.example.search"
    compileSdk = libs.versions.android.compileSdk.get().toInt() // Укажите вашу версию compileSdk

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt() // Укажите минимальную версию SDK
        targetSdk = libs.versions.android.targetSdk.get().toInt() // Укажите целевую версию SDK
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}