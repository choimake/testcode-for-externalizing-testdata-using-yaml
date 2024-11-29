plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")

    // https://mvnrepository.com/artifact/org.yaml/snakeyaml
    testImplementation("org.yaml:snakeyaml:2.3")

}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("PASSED", "FAILED", "SKIPPED") // テスト結果を表示
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL // 詳細表示
        showStandardStreams = true // 標準出力を表示
    }
}
