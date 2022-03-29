pipeline {
    agent any 
    stages {
        stage('Compile and Clean') { 
            steps {

                sh "mvn clean compile"
            }
        }
      
        stage('deploy') { 
            steps {
                sh "mvn package"
            }
        }
        
        stage('run app'){
        	steps {
        		sh 'nohup java -jar target/ProductService-0.0.1-SNAPSHOT.jar &'
        	}
        }
    }
}