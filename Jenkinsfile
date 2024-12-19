pipeline {
    agent any

    environment {
        SONAR_SCANNER_HOME = tool 'SonarQube Scanner' // SonarQube scanner tool name in Jenkins
        DOCKER_IMAGE = "Jenkins_deploy_hunter_img:latest"       // Docker image name and tag
    }

    stages {
        stage('Checkout Code') {
            steps {
                // Fetch the code from the source control system (e.g., Git)
                checkout scm
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') { // Replace 'SonarQube' with the name of your SonarQube server in Jenkins
                    sh """
                        ${SONAR_SCANNER_HOME}/bin/sonar-scanner \
                        -Dsonar.projectKey=Hunters_league \
                        -Dsonar.sources=. \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.login=sqp_ce0075a8e4c90d2b735a543a266fbcc21ef6aca4 \
                        -Dsonar.qualitygate.wait=true
                    """
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    script {
                        // Check the SonarQube quality gate status
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
                script {
                    // Build the Docker image
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Stop and remove any running container with the same name
                    sh """
                        docker stop deploy_hunter_container || true
                        docker rm deploy_hunter_container || true

                        // Run the new Docker container
                        docker run -d --name deploy_hunter_container -p 8080:8080 ${DOCKER_IMAGE}
                    """
                }
            }
        }
    }
}
