package com.plumcookingwine.versions

/**
 * composeUI 适用于as  金丝雀版本
 * // compose ui
 * // "compose_ui"        : "androidx.compose.ui:ui:$compose_version",
 * // // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
 * // "compose_foundation": "androidx.compose.foundation:foundation:$compose_version",
 * // // Tooling support (Previews, etc.)
 * // "compose_ui_toolings"       : "androidx.ui:ui-tooling:$compose_version",
 * // // Material design icons
 * // "compose_icon"      : "androidx.compose.material:material-icons-core:$compose_version",
 * // "compose_icon_ext"  : "androidx.compose.material:material-icons-extended:$compose_version",
 * // // Integration with observables
 * // "compose_livedata"  : "androidx.compose.runtime:runtime-livedata:$compose_version",
 */
@SuppressWarnings('unused')
class VersionControl {

    static final compileSdkVersion = 30
    static final buildToolsVersion = "30.0.2"
    static final minSdkVersion = 21
    static final targetSdkVersion = 30

    static final kotlin_version = "1.4.21"
    static final legacy_version = "1.0.0"
    static final appcompat_version = "1.2.0"
    static final constraint_version = "2.0.4"
    static final recyclerview_version = "1.1.0"
    static final material_version = "1.2.1"
    static final statusbar_version = "1.5.1"
    static final multidex_version = "2.0.1"
    static final startup_version = "1.0.0"
    static final coroutinescore_version = "1.4.2"
    static final coroutinesktx_version = "1.4.2"
    static final lifecycle_version = "2.2.0"
    static final glide_version = "4.11.0"
    static final retrofit_version = "2.9.0"
    static final logger_version = "4.7.2"
    static final conscrypt_version = "2.4.0"
    static final loadsir_version = "1.3.8"
    static final hilt_version = "2.28.1-alpha"
    static final hilt_vm_version = "1.0.0-alpha02"
    static final nav_version = "2.3.2"
    static final fragment_version = "1.2.4"
    static final compose_version = "1.0.0-alpha05"
    static final banner_version = "2.1.0"
    static final divider_version = "2.0"
    static final paging_version = "3.0.0-alpha10"
    static final room_version = "2.2.5"


    static final class Dependencies {

        static final mDependenciesMap = [
                "kotlinstd"              : "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version",
                "appcompat"              : "androidx.appcompat:appcompat:$appcompat_version",
                "legacy"                 : "androidx.legacy:legacy-support-v4:$legacy_version",
                "constraintlayout"       : "androidx.constraintlayout:constraintlayout:$constraint_version",
                "recyclerview"           : "androidx.recyclerview:recyclerview:$recyclerview_version",
                "multidex"               : "androidx.multidex:multidex:$multidex_version",
                /**
                 * ------------------ JetPack库相关 start ------------------
                 */
                // ui库
                "material"               : "com.google.android.material:material:$material_version",
                // 启动项，初始化相关
                "startup"                : "androidx.startup:startup-runtime:$startup_version",
                // 分页库
                "paging"                 : "androidx.paging:paging-runtime:$paging_version",
                "room"                   : "androidx.room:room-runtime:$room_version",
                "room_ktx"               : "androidx.room:room-ktx:$room_version",
                // 协程相关
                "coroutinescore"         : "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinescore_version",
                "coroutinesktx"          : "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesktx_version",
                // lifecycle 相关
                "lifecycle_livedata"     : "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version",
                "lifecycle_vm"           : "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version",
                "lifecycle_runtime"      : "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version",
                "lifecycle_vm_state"     : "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version",
                "lifecycle_common"       : "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version",
                "lifecycle_ext"          : "androidx.lifecycle:lifecycle-extensions:$lifecycle_version",

                // di依赖注入
                "hilt"                   : "com.google.dagger:hilt-android:$hilt_version",
                "hilt_vm"                : "androidx.hilt:hilt-lifecycle-viewmodel:$hilt_vm_version",
                // 导航组件
                // "nav_fragment"           : "androidx.navigation:navigation-fragment-ktx:$nav_version",
                "fragment"           : "androidx.fragment:fragment-ktx:$fragment_version",
                "nav_ui"                 : "androidx.navigation:navigation-ui-ktx:$nav_version",
                /**
                 * ------------------ JetPack库相关 end ------------------
                 */
                // 图片加载
                "glide"                  : "com.github.bumptech.glide:glide:$glide_version",
                //网络请求相关
                "retrofit"               : "com.squareup.retrofit2:retrofit:$retrofit_version",
                "retrofit_gson"          : "com.squareup.retrofit2:converter-gson:$retrofit_version",
                "retrofit_scalars"       : "com.squareup.retrofit2:converter-scalars:$retrofit_version",
                "retrofit_logger"        : "com.squareup.okhttp3:logging-interceptor:$logger_version",
                "conscrypt"              : "org.conscrypt:conscrypt-android:$conscrypt_version",

                // 页面加载状态
                "loadsir"                : "com.kingja.loadsir:loadsir:$loadsir_version",
                // 状态栏相关
                "status_bar"             : "com.jaeger.statusbarutil:library:$statusbar_version",
                // banner
                "banner"                 : "com.youth.banner:banner:$banner_version",
                // 通用的RecyclerView的分割线，支持LinearLayoutManager和GridLayoutManager
                "y_divideritemdecoration": "com.yanyusong.y_divideritemdecoration:y_divideritemdecoration:$divider_version",
        ]

    }


    static final class Compilers {

        static final mCompilers = [
                "hilt"   : "com.google.dagger:hilt-android-compiler:$hilt_version",
                "hilt_vm": "androidx.hilt:hilt-compiler:$hilt_vm_version",
                "room"   : "androidx.room:room-compiler:$room_version",
        ]
    }
}