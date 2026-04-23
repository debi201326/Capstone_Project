pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/debi201326/Capstone_Project'
            }
        }

        stage('Clean & Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests (UI + API + JMeter + Cucumber + TestNG)') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Generate Allure Results') {
            steps {
                // Ensure results folder exists
                bat 'dir target || mkdir target'
            }
        }

        stage('Publish Reports') {
            steps {
                // TestNG / Cucumber results
                junit 'target/surefire-reports/*.xml'

                // Allure report (requires Allure plugin configured)
                allure includeProperties: false, results: [[path: 'target/allure-results']]
            }
        }
    }

    post {
        always {
            echo 'Test Execution Completed'
            cleanWs()
        }

        success {
            echo 'Build SUCCESS'
        }

        failure {
            echo 'Build FAILED'
        }
    }
}
