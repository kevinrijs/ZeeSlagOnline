pipeline {
  agent any
  stages {
    stage('deploy on test server') {
      parallel {
        stage('deploy on test server') {
          steps {
            echo 'Starting...'
            bat 'testscript.cmd'
          }
        }
        stage('map enitiies') {
          steps {
            sh 'sh \'echo hi\''
          }
        }
      }
    }
  }
}