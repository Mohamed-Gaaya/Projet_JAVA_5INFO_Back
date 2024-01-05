pipeline {
    agent any

    environment {
        rname = "projet_java_5info_back"
        rurl = 'goku47'
        imagename = "projet_java_5info_back"
        dockerhubCredentials = 'e95fc091-e281-4436-92db-c7c5e8f30f5c'  // Replace with your Docker Hub credentials ID
    }

    stages {
        stage('Build') {
            steps {
                script {
                    // Use an official Maven image for building the Spring Boot application
                    def builderImage = 'maven:3.8.4-openjdk-17-slim'
                    def imageName = "${rurl}/${imagename}:latest"

                    // Build the Spring Boot application and create a thin JAR file
                    sh 'mvn clean package -DskipTests'

                    // Use a smaller base image for the runtime
                    docker.build(imageName, '--file Dockerfile .')

                    // Use Docker Hub credentials to log in
                    withCredentials([usernamePassword(credentialsId: dockerhubCredentials, passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
                        // Log in to Docker Hub
                        sh "docker login -u ${env.DOCKERHUB_USERNAME} -p ${env.DOCKERHUB_PASSWORD}"

                        // Push the Docker image to Docker Hub
                        sh "docker push ${imageName}"
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Add your actual test commands here
                    sh 'echo "Running tests"'
                }
            }
        }
    }
}
