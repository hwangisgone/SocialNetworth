plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	// Included spring-boot-starter-security in oauth2-resource-server (this is used for jwt token generation and authentication)
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")
	
	
	// https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.7.2")
	
	// https://mvnrepository.com/artifact/org.testcontainers/postgresql
	testImplementation("org.testcontainers:postgresql:1.19.7")

	// Testing REST
	// https://mvnrepository.com/artifact/io.rest-assured/rest-assured
	testImplementation("io.rest-assured:rest-assured:5.4.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
