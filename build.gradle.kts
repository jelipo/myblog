import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    idea
    kotlin("jvm") version "1.3.50"
    kotlin("plugin.spring") version "1.3.50"
    id("org.springframework.boot") version "2.2.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

group = "com.jelipo"
version = "1.1.4-2"

sourceSets.main {
    resources {
        setSrcDirs(listOf("src/main/java", "src/main/kotlin", "src/main/resources"))
        exclude("**/*.kt", "**/*.java")
    }
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

idea.project {
    jdkName = "1.8"
    languageLevel = IdeaLanguageLevel("1.8")
    vcs = "Git"
}

tasks {
    bootJar {
        archiveFileName.set("${rootProject.name}.${archiveExtension.get()}")
        launchScript()
    }
    withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
}

idea {
    project {
        jdkName = "1.8"
        languageLevel = IdeaLanguageLevel("8")
        vcs = "Git"
    }
}

repositories {
    mavenLocal()
    maven("https://repo.huaweicloud.com/repository/maven/")
    mavenCentral()
}

val developmentOnly: Configuration by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.apache.commons:commons-lang3:3.9")
    runtime("org.postgresql:postgresql")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}