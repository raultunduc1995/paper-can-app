plugins {
    `java-platform`
}

group = "com.example.paperscanapp"

javaPlatform.allowDependencies()
dependencies {
    // UI Toolkit
    api(platform("androidx.compose:compose-bom:2022.10.00"))
}
dependencies.constraints {
    api("androidx.core:core-ktx:1.8.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    api("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    api("androidx.activity:activity-compose:1.5.1")
    api("junit:junit:4.13.2")
    api("androidx.test.ext:junit:1.1.5")
    api("androidx.test.espresso:espresso-core:3.5.1")
}