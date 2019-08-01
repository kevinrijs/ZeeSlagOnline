pipeline {
  agent any
  stages {
    stage('deploy on test server') {
      steps {
        echo 'Starting...'
        bat 'testscript.cmd'
      }
    }
  }
}