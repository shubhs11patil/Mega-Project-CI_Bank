pipeline{
    agent { label 'agent-slave' }
    
    stages{
        stage("Code Clone"){
            steps{
                echo "Code Clone Stage"
                git url: "https://github.com/sushmithavs/Springboot-BankApp.git", branch: "DevOps"
            }
        }
        stage("Code Build & Test"){
            steps{
                script {
                   try {
                       echo "Starting Code Clone Stage"
                        git url: "https://github.com/sushmithavs/Springboot-BankApp.git", branch: "DevOps"
                    } catch (Exception e) {
                       error "Failed to clone repository: ${e.message}"
                    }
                }
             }
         }
    
        stage("Push To DockerHub"){
            steps{
                withCredentials([usernamePassword(
                    credentialsId:"dockerhub-creds",
                    usernameVariable:"dockerHubUser", 
                    passwordVariable:"dockerHubPass")]){
                sh 'echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin'
                sh "docker image tag bankapp:latest ${env.dockerHubUser}/bankapp:latest"
                sh "docker push ${env.dockerHubUser}/bankapp:latest"
                }
            }
        }
        stage("Deploy"){
            steps{
                script {
    sh "docker compose down && docker compose up -d --build"
    // Wait for application to be ready
    sh '''
        max_attempts=30
        attempt=1
        echo "Waiting for application to be ready..."
        while [ $attempt -le $max_attempts ]; do
            if curl -s http://localhost:8080/health; then
                echo "Application is ready!"
                exit 0
            fi
            attempt=$((attempt+1))
            sleep 10
        done
        echo "Application failed to start within timeout"
       exit 1
   '''
}
            }
        }
    }
}