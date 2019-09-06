plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphql-java-tools:5.2.4")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java8:4.7.1") // for display lambda
    testImplementation("io.cucumber:cucumber-junit:4.7.1")
    testImplementation("org.seleniumhq.selenium:selenium-java:3.14.0")
    testImplementation("junit:junit")
}

tasks {
    // TODO: ログ出力されない
    // ref: https://github.com/junit-team/junit5-samples/blob/master/junit5-jupiter-starter-gradle-kotlin/build.gradle.kts#L16
    test {
        testLogging {
            events("passed", "skipped", "failed")
            info.events("passed", "skipped", "failed")
        }
    }
    register<Test>("cucumberRun") {
        include("**/RunCucumber.class")
    }
    register<Exec>("cucumberRunWithBuild") {
        commandLine("sh", "../tools/cucumber_with_build.sh")
    }
}
