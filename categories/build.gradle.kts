import com.ivy.buildsrc.Hilt
import com.ivy.buildsrc.Testing

apply<com.ivy.buildsrc.IvyComposePlugin>()

plugins {
    `android-library`
}

dependencies {
    Hilt()
    implementation(project(":common:main"))
    implementation(project(":design-system"))
    implementation(project(":core:ui"))
    implementation(project(":core:data-model"))
    implementation(project(":navigation"))
    Testing()
}