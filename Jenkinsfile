/* groovylint-disable-next-line CompileStatic */
pipeline {
    agent { label 'agent-slave' }
    environment {
        VERSION = "${BUILD_NUMBER}"
    }
    stages {
        stage('Code Clone') {
            steps {
                echo 'Code Clone Stage'
                git url: 'https://github.com/nkantamani2023/Springboot-BankApp.git', branch: 'DevOps'
                timeout(time: 5, unit: 'MINUTES')
            {
                    /* groovylint-disable-next-line DuplicateStringLiteral */
                    git url: params.REPO_URL ?: 'https://github.com/nkantamani2023/Springboot-BankApp.git',
                /* groovylint-disable-next-line DuplicateStringLiteral */
                branch: params.BRANCH ?: 'DevOps',
                shallow: true
            }
            }
        }
        stage('Code Build & Test') {
            steps {
                echo 'Code Build Stage'
                sh './mvnw clean test'
                sh 'docker build -t bankapp .'
            }
        }
        stage('Push To DockerHub') {
            steps {
                withCredentials([usernamePassword(
            credentialsId:'dockerhub-creds',
            usernameVariable:'dockerHubUser',
            passwordVariable:'dockerHubPass')]) {
                    sh 'echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin'
                    sh "docker image tag bankapp:latest ${env.dockerHubUser}/bankapp:${VERSION}"
                    sh "docker push ${env.dockerHubUser}/bankapp:${VERSION}"
            }
            }
        }
        stage('Deploy') {
            environment {
                COMPOSE_PROJECT_NAME = "${JOB_NAME}-${VERSION}"
            }
            steps {
                script {
                    // Graceful deployment with zero downtime
                    sh """
                docker compose -p ${COMPOSE_PROJECT_NAME} up -d --build
                sleep 30
                if docker compose -p ${COMPOSE_PROJECT_NAME} ps | grep -q "unhealthy"; then
                    echo "Deployment failed - rolling back"
                    docker compose -p ${COMPOSE_PROJECT_NAME} down
                    exit 1
                else
                    docker compose -p ${JOB_NAME}-previous down || true
                fi
                """
                }
            }
        }
    }
    post {
        failure {
            echo 'Deployment failed - initiating rollback'
            sh "docker compose -p ${JOB_NAME}-previous up -d"
        }
    }
}

