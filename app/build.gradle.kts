/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.8/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    id("com.github.node-gradle.node") version "3.0.1" // Use the latest version
    `base`
}

node {
    version.set("9.11.2")
    download.set(true)
    workDir.set(file("${project.projectDir}/node"))
    nodeProjectDir.set(file("${project.projectDir}"))
}

tasks.named<com.github.gradle.node.npm.task.NpmInstallTask>("npmInstall") {
    packageJsonFile.set(file("${project.projectDir}/package.json"))
    args.set(listOf("install"))
    workingDir.set(file("${project.projectDir}"))
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("npm_build") {
    args.set(listOf("run", "build"))
    dependsOn(tasks.named("npmInstall"))
    workingDir.set(file("${project.projectDir}"))
}

tasks.register<com.github.gradle.node.npm.task.NpmTask>("npm_test") {
    args.set(listOf("test"))
    dependsOn(tasks.named("npmInstall"))
    workingDir.set(file("${project.projectDir}"))
}

tasks.register<Zip>("zip") {
    from(".") {
        include("*")
        include("bin/**")
        include("data/**")
        include("node_modules/**")
        include("public/**")
        include("routes/**")
        include("views/**")
    }
    destinationDirectory.set(file("dist"))
    archiveBaseName.set("trainSchedule")
    dependsOn(tasks.named("npm_build"))
}

tasks.named("build") {
    dependsOn(tasks.named("zip"))
    dependsOn(tasks.named("npm_build"))
}

tasks.named("npm_build") {
    dependsOn(tasks.named("npm_test"))
}
