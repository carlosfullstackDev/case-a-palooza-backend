pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/carlosfullstackDev/case-a-palooza-backend'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'java -jar target/my-app.jar'
            }
        }
    }
}