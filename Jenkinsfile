node{
  stage('SCM Checkout'){
    git 'https://github.com/msarr92/Java'
  }
  stage('Compile-Package'){
    def mvnHome = tool name: '', type: 'maven'
    sh "${mvnHome}/bin/mvn package"
  }
}
