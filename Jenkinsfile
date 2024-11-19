@Library('Shared')_
pipeline{
    agent { label 'dev-server'}
    
    stages{
        stage("Code clone"){
            steps{
                sh "whoami"
            clone("https://github.com/pundir8372/Springboot-BankApp.git" , "DevOps")
            }
        }
        stage("Code Build"){
            steps{
            dockerbuild("notes-app","latest")
            }
        }
        
        stage("Deploy"){
            steps{
                deploy()
            }
        }
        
    }
}
