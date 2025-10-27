pipeline {
    agent any
    
    tools {
        // Ensure these tool names match Jenkins → Manage Jenkins → Global Tool Configuration
        maven 'Maven3'
        jdk 'JDK21'   // You can use JDK17 if that’s the version configured in Jenkins
    }

    environment {
        WAR_FILE = "target/SevenWondersApp.war"  
        CONTEXT_PATH = "/SevenWondersApp"        
        TOMCAT_URL = "http://localhost:8080"     
    }

    stages {
        stage('Checkout') {
            steps {
                echo "🔁 Cloning repository..."
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                echo "⚙️ Building WAR file using Maven..."
                bat 'mvn clean package -DskipTests=true'
            }
            post {
                success {
                    echo "WAR built successfully"
                    archiveArtifacts artifacts: 'target/*.war', fingerprint: true
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo " Deploying WAR to Tomcat..."
                withCredentials([usernamePassword(credentialsId: 'TomcatAdmin', usernameVariable: 'TOMCAT_USER', passwordVariable: 'TOMCAT_PASS')]) {
                    bat '''
                        echo Undeploying previous version (if any)...
                        curl -u %TOMCAT_USER%:%TOMCAT_PASS% "%TOMCAT_URL%/manager/text/undeploy?path=%CONTEXT_PATH%" || exit 0

                        echo Deploying new WAR...
                        curl -u %TOMCAT_USER%:%TOMCAT_PASS% -T %WAR_FILE% "%TOMCAT_URL%/manager/text/deploy?path=%CONTEXT_PATH%&update=true"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deployment successful! Visit ${TOMCAT_URL}${CONTEXT_PATH}"
        }
        failure {
            echo "❌ Deployment failed. Check Jenkins console logs."
        }
    }
}
