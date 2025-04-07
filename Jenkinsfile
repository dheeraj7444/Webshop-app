pipeline {
    agent any
    tools{
        jdk 'jdk17'
    }
    environment{
        SCANNER_HOME= tool 'sonar-scanner'
    }
    stages {
        stage('Git Checkout') {
            steps {
                git 'https://github.com/dheeraj7444/Webshop-app.git'
            }
        }
        stage('CLJ Homes-SCAN') {
            steps {
                sh "clj-holmes fetch-rules"
                sh "clj-holmes scan -p ./"
            }
        }
        stage('SONARQUBE ANALYSIS') {
            steps {
              withSonarQubeEnv('sonar') {
               sh " $SCANNER_HOME/bin/sonar-scanner -Dsonar.projectName=Webshop-app -Dsonar.projectKey=Webshop-app "
                }
            }
        }
        stage('Build & deploy') {
            steps {
                sh'docker-compose build'
            }
        }
        stage('Docker Puss') {
            steps {
               withCredentials([usernamePassword(credentialsId: 'docker-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
              sh """
                 echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                  docker tag webshop-app-clj:latest dheeraj7444/webshop-app-clj:$BUILD_ID
                  docker push dheeraj7444/webshop-app-clj:$BUILD_ID
                  """
                 }  
            }
        }
    }
}
