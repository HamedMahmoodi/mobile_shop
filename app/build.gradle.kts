plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ir.hamedmahmoodi.mobileshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "ir.hamedmahmoodi.mobileshop"
        minSdk = 23
        //noinspection OldTargetApi
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("androidx.window:window:1.3.0")
    implementation("com.google.firebase:firebase-firestore-ktx:25.1.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Lottie
    implementation("com.airbnb.android:lottie:6.0.0")

    //secure shared preferences
    implementation("androidx.security:security-crypto:1.0.0")

    //add Room
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("androidx.room:room-runtime:2.6.1")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:2.6.1")

    //add retrofit library
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Justify Text View
    implementation("com.codesgood:justifiedtextview:1.1.0")

    // Rating Bar
   /* implementation("com.github.ome450901:SimpleRatingBar:1.5.1")*/

    //Live Data
      implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    //Picasso
     implementation("com.squareup.picasso:picasso:2.8")

}