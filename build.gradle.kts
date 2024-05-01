plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "id.ac.ui.cs.advprog.heymart"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

val junitJupiterVersion = "5.9.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")


    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks.register<Test>("unitTest") {
    description = "Runs unit tests."
    group = "verification"

    filter {
        excludeTestsMatching("*FunctionalTest")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    filter {
        excludeTestsMatching("*FunctionalTest")
    }

    finalizedBy(tasks.jacocoTestReport)
}

afterEvaluate {
    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        classDirectories.setFrom(files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/McsAuthenticationApplication.class")
            }
        }))
        reports {
            xml.required = true
            html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
        }
    }
}
