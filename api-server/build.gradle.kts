plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

// JOOQ
apply(from = "jooq.gradle")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.graphql-java:graphql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphiql-spring-boot-starter:5.0.2")
    implementation("com.graphql-java:graphql-java-tools:5.2.4")
    implementation("org.jooq:jooq")
    implementation("mysql:mysql-connector-java:5.1.45")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java8:4.7.1") // for display lambda
    testImplementation("io.cucumber:cucumber-junit:4.7.1")
    testImplementation("org.seleniumhq.selenium:selenium-java:3.14.0")
    testImplementation("junit:junit")
    testImplementation("com.ninja-squad:DbSetup-kotlin:2.1.0")
}

val runScenarioTest = "runScenarioTest"
tasks {
    register<Test>(runScenarioTest) {
        include("**/RunCucumber.class")
    }
    register<Exec>("cucumberRunWithBuild") {
        commandLine("sh", "../tools/cucumber_with_build.sh")
    }
}

afterEvaluate {
    tasks {
        named<Test>(runScenarioTest) {
            logging.level = LogLevel.INFO
        }
    }
}
