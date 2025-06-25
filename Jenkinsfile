pipeline {
  agent any
  tools {
    jdk 'JDK17'        // Define this in Jenkins > Global Tools
  }
  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/soknapich/ecommerce.git'
      }
    }
    stage('Build') {
      steps {
        script {
          if (isUnix()) {
            sh './gradlew clean build'
          } else {
            bat 'gradlew.bat clean build'
          }
        }
      }
    }
    stage('Run') {
      steps {
        script {
          if (isUnix()) {
            sh 'java -jar build/libs/*.jar'
          } else {
            bat 'java -jar build\\libs\\*.jar'
          }
        }
      }
    }
  }
}
