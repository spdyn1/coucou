// Module Android partagé (sous-module git) : bouton « Aide à distance » qui lance l'appli Aide
// (ou propose de la télécharger). Le projet racine doit déclarer le plugin com.android.library.
plugins {
    id("com.android.library")
}

android {
    namespace = "com.spdyn1.aide"
    compileSdk = 37
    defaultConfig { minSdk = 29 }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
