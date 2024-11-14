pipeline {
    agent { label 'spring-server' }
    
    stages {
        stage("Code Clone") {
            steps {
                echo "Code Clone Stage"
                git url: "https://github.com/shailesh271997/Springboot-BankApp.git", branch: "nginx-setup"
            }
        }
        stage("Code Build & Test") {
            steps {
                echo "Code Build Stage"
                sh "docker build -t springboot-bankapp ."
            }
        }
        stage("Push To DockerHub") {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: "dockerHubCreds", 
                    usernameVariable: "dockerHubUser", 
                    passwordVariable: "dockerHubPass")]) {
                    sh 'echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin'
                    sh "docker image tag springboot-bankapp ${env.dockerHubUser}/springboot-bankapp:latest"
                    sh "docker push ${env.dockerHubUser}/springboot-bankapp:latest"
                }
            }
        }
        stage("Deploy") {
            steps {
                sh "docker compose down && docker compose up -d --build"
            }
        }
    }
}

