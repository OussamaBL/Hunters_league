pipeline {
    agent any

    environment {
        SONAR_SCANNER_HOME = tool 'SonarQube Scanner' // Jenkins SonarQube scanner tool name
    }

    stages {
        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // Replace with your SonarQube server name in Jenkins
                    sh "${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=Hunters_league \
                        -Dsonar.sources=. \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=sqp_ce0075a8e4c90d2b735a543a266fbcc21ef6aca4 \
                        -Dsonar.qualitygate.wait=true"
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    script {
                        def qualityGate = waitForQualityGate()
                        if (qualityGate.status != 'OK') {
                            error "Pipeline aborted due to Quality Gate failure: ${qualityGate.status}"
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t your_app_image:latest .'
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 your_app_image:latest'
            }
        }
    }
}
