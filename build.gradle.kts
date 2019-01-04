import com.bmuschko.gradle.docker.tasks.container.DockerRemoveContainer
import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerPushImage
import com.bmuschko.gradle.docker.tasks.image.DockerRemoveImage
import com.bmuschko.gradle.docker.tasks.image.Dockerfile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.3.10"
    id("org.springframework.boot") version "2.1.0.RELEASE"
    id("com.bmuschko.docker-remote-api") version "4.2.0"
}

group = "com.springmarker"
version = "1.0.2"

buildscript {
    val springBootVersion = "2.1.1.RELEASE"
    val kotlinVersion = "1.3.11"
    repositories {
        maven("https://repo.huaweicloud.com/repository/maven/")
        gradlePluginPortal()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("com.bmuschko:gradle-docker-plugin:4.2.0")
    }
}


apply(plugin = "kotlin")
apply(plugin = "kotlin-spring")
apply(plugin = "eclipse")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "java")
apply(plugin = "com.bmuschko.docker-remote-api")


java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


sourceSets {
    main {
        resources {
            setSrcDirs(listOf("src/main/java", "src/main/kotlin", "src/main/resources"))
            exclude("**/*.kt", "**/*.java")
        }
    }
}
//get("main").resources.setSrcDirs(setOf("src/main/java", "src/main/kotlin", "src/main/resources"))


repositories {
    maven("https://repo.huaweicloud.com/repository/maven/")
    mavenCentral()
}


docker {
    registryCredentials {
        System.getenv()
        val registryUsername: String = (project.findProperty("registryUsername") ?: "") as String
        val registryPassword: String = (project.findProperty("registryPassword") ?: "") as String
        if (registryUsername.isEmpty() || registryPassword.isEmpty()) {
            return@registryCredentials
        }
        username.set(registryUsername)
        password.set(registryPassword)
    }
}


tasks {

    val tasks = this

    /**
     * 创建Dockerfile
     */
    val createDockerfile = create<Dockerfile>("createDockerfile") {
        destFile.set(project.file("Dockerfile"))
        val baseDockerImage: String = (project.findProperty("baseDockerImage") ?: "1.0").toString()
        from(baseDockerImage)
        copyFile("build/libs/${rootProject.name}-$version.jar", "/opt/app/${rootProject.name}-$version.jar")
        exposePort(8080)
        defaultCommand("/usr/bin/java", "-jar", "/opt/app/${rootProject.name}-$version.jar")
    }

    /**
     * build Docker的Image
     */
    val buildImage = create<DockerBuildImage>("buildImage") {
        dependsOn(this@tasks.getByName("createDockerfile"))
        println(System.getProperty("user.dir"))
        inputDir.set(file(createDockerfile.destFile.get().asFile.parent))
        val projectDockerImage: String = (project.findProperty("projectDockerImage") ?: "1.0").toString()
        tags.add("$projectDockerImage:$version")
    }

    create<DockerPushImage>("pushImage") {
        dependsOn(this@tasks.getByName("buildImage"))
        buildImage.tags.get().forEach {
            imageName.set(it)
            onError {
                tasks.getByName<DockerRemoveImage>("removeImage").start()
            }
            doLast {
                tasks.getByName<DockerRemoveImage>("removeImage").start()
            }
        }
    }

    create<DockerRemoveImage>("removeImage") {
        dependsOn(this@tasks.getByName("buildImage"))
        targetImageId(buildImage.imageId)
        force.set(true)
    }

    getByName<BootJar>("bootJar") {
        launchScript()
    }

    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }

}

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-freemarker")
    compile("org.springframework.boot:spring-boot-starter-aop")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    runtime("org.springframework.boot:spring-boot-devtools")
    testCompile("org.springframework.boot:spring-boot-starter-test")
    //compile('org.springframework.boot:spring-boot-starter-webflux')
    compile("com.squareup.okhttp3:okhttp:3.11.0")
    compile("com.baomidou:mybatis-plus-boot-starter:3.0.7")
    compile("org.apache.commons:commons-lang3:3.8.1")
}