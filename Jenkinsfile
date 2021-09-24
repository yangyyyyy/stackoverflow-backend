pipeline {
    agent any

    environment {
        JOB_NAME = 'backend'
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh "cd $WORKSPACE"

                    sh 'mvn clean package'
                }
            }
        }

        stage('Deliver'){
            steps{
                sh 'sudo cp target/example.war /usr/local/tomcat9/webapps'
                sh 'sudo /usr/local/tomcat9/bin/startup.sh'
            }
        }
    }
}
