pipeline {
    agent any

    tools {
        maven 'Maven'  // Assuming Maven is configured in Jenkins
        jdk 'JDK'     // Assuming JDK is configured in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Assuming the repository is already checked out or use git clone if needed
                echo 'Checking out code...'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Reports') {
            steps {
                // Publish Allure reports if configured
                allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
            }
        }
    }

    post {
        always {
            // Archive test results
            junit 'target/surefire-reports/*.xml'
        }
    }
}