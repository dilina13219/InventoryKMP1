plugins {
    // මේවා දැනටමත් තියෙනවා ඇති
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    // මේ අලුත් line එක අනිවාර්යයෙන්ම තියෙන්න ඕනේ
    alias(libs.plugins.sqlDelight) apply false
}