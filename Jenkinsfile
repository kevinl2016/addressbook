pipeline {
  agent any
  stages {
    stage('dev') {
      steps {
        git(poll: true, url: 'https://github.com/kevinl2016/addressbook', branch: 'master', changelog: true)
      }
    }
  }
}