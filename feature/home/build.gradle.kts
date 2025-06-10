import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("kotlinx-serialization")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "search"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "search.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.runtime)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.lifecycle.runtime.compose) // жизненный цикл

            //Retrofit
            implementation(libs.retrofit)
            implementation(libs.converter.gson)

            //Coil
            implementation(libs.coil.compose)
            implementation(libs.coil.network.okhttp)

            //Koin
            implementation(libs.koin.android)

            //Ktor client
            implementation(libs.ktor.client.android)
            implementation(libs.ktor.client.content.negotiation)

            implementation(project(":ui-core"))
        }

        commonMain.dependencies {
            implementation(libs.navigation.compose) // навигация
            implementation(compose.runtime) // реактивность и управление состоянием (remember, mutableStateOf)
            implementation(compose.foundation) // базовый UI
            implementation(compose.material3) // готовые UI компоненты (Button, Card...)
            implementation(compose.ui) // ядро компоуса для работы с графикой и вводом (dp, color, textStyle..)
            implementation(compose.components.resources) // шрифты и строки
//            implementation(libs.koin.core)
            implementation(libs.lifecycle.viewmodel.compose)

            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(project(":feature:film"))
        }
        wasmJsMain.dependencies {
            //Ktor client
            implementation("io.ktor:ktor-client-js:3.1.3")
            implementation("org.jetbrains.skiko:skiko-js-wasm-runtime:0.9.4.2")
//            implementation(libs.html.core)

        }
    }
}

android {
    namespace = "com.example.search"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
//        applicationId = "org.example.filmoteka"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
//        versionCode = 1
//        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
