pipeline {
    agent any
    
    tools {
        // Make sure this Maven name matches Jenkins > Global Tool Configuration
        maven 'Maven3'
        jdk 'JDK11'
    }

    environment {
        WAR_FILE = "target/seven-wonders.war"
        CONTEXT_PATH = "/seven-wonders"
        TOMCAT_URL = "http://localhost:8080" // change if remote
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Cloning repository..."
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                echo "Building WAR file using Maven..."
                sh 'mvn clean package -DskipTests=true'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo "Deploying WAR to Tomcat..."
                withCredentials([usernamePassword(credentialsId: 'tomcat-manager-creds', usernameVariable: 'TOMCAT_USER', passwordVariable: 'TOMCAT_PASS')]) {
                    sh '''
                        echo "Undeploying previous version (if any)..."
                        curl -u $TOMCAT_USER:$TOMCAT_PASS "$TOMCAT_URL/manager/text/undeploy?path=$CONTEXT_PATH" || true
                        echo "Deploying new WAR..."
                        curl -u $TOMCAT_USER:$TOMCAT_PASS -T $WAR_FILE "$TOMCAT_URL/manager/text/deploy?path=$CONTEXT_PATH&update=true"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful! Visit $TOMCAT_URL$CONTEXT_PATH"
        }
        failure {
            echo "❌ Deployment failed. Check Jenkins logs."
        }
    }
}
