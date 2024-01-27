node{
  stage('SCM Checkout'){
    git 'https://github.com/msarr92/Java'
  }
  stage('Compile-Package'){
    sh 'mvn package'
  }
}
