plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.3.1")
    implementation("com.bazaarvoice.jolt:jolt-core:0.0.16")
    implementation("com.bazaarvoice.jolt:json-utils:0.0.16")
    implementation("com.networknt:json-schema-validator:1.0.72")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}