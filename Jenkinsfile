pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/your-username/your-repo.gitL'
            }
        }

        stage('Clean Build') {
            steps {
                bat 'mvn clean'
            }
        }

        stage('Compile') {
            steps {
                bat 'mvn compile'
            }
        }

        stage('Run Tests (UI + API + Cucumber + TestNG)') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Performance Test (JMeter)') {
            steps {
                echo 'JMeter runs inside Maven test phase'
            }
        }

        stage('Generate Reports') {
            steps {
                echo 'Generating Allure / TestNG reports'
            }
        }
    }

    post {

        always {
            // TestNG + Cucumber reports (Surefire)
            junit 'target/surefire-reports/*.xml'

            // If you generate Cucumber reports
            publishHTML(target: [
                reportDir: 'target',
                reportFiles: 'cucumber.html',
                reportName: 'Cucumber Report'
            ])
        }

        success {
            echo 'BUILD SUCCESS'
        }

        failure {
            echo 'BUILD FAILED'
        }
    }
}
