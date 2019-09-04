plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	kotlin("jvm")
	kotlin("plugin.spring")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.cucumber:cucumber-java8:4.7.1") // for display lambda
	testImplementation("io.cucumber:cucumber-junit:4.7.1")
	testImplementation("org.seleniumhq.selenium:selenium-java:3.14.0")
	testImplementation("junit:junit")
}
