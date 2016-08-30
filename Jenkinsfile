node {
    echo "Building ------> build${env.BUILD_NUMBER}"
    def giturl = "https://github.com/dunc101/osspringbooteap.git"
    def pomdirectory = "osspringbooteap"
    def dockerimagename = "openshiftspringbootdemo/openshiftspringbootdemo"
    def app = "osspringbooteap"
    def readinessprobe = "http://localhost:8080/osspringbooteap-1.0.0/health"
    def livenessprobe = "http://localhost:8080/osspringbooteap-1.0.0/health"
    def containertype = "eap64"
    def replicas = "3"
    
    stage 'Checkout and Build'
    build job: 'demo-checkoutandbuild', parameters: [[$class: 'StringParameterValue', name: 'GITURL', value: "$giturl"], [$class: 'StringParameterValue', name: 'POMDIRECTORY', value: "$pomdirectory"], [$class: 'StringParameterValue', name: 'DOCKERIMAGENAME', value: "$dockerimagename"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"]]
    
    stage 'Push to Dev'
    build job: 'demo-dev', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"], [$class: 'StringParameterValue', name: 'CONTAINERTYPE', value: "$containertype"]]

    stage 'Integration Tests DEV'
    echo "Run your dev integration tests here.  Skipping because this a demo."
    
    stage 'Deploy to Test'
    build job: 'demo-test', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"]]
    
    stage 'Integration Tests TEST'
    echo "Run your test integration tests here.  Skipping because this a demo."
    
    stage 'Request Authorization to Promote to Stage'
    input message: 'Please approve the promotion to the Stage environment.  All tests and builds have passed.', ok: 'Approve'
    
    stage 'Deploying to stage'
    build job: 'demo-stage', parameters: [[$class: 'StringParameterValue', name: 'TAG', value: "build${env.BUILD_NUMBER}"], [$class: 'StringParameterValue', name: 'APP', value: "$app"], [$class: 'StringParameterValue', name: 'READINESSPROBE', value: "$readinessprobe"], [$class: 'StringParameterValue', name: 'LIVENESSPROBE', value: "$livenessprobe"], [$class: 'StringParameterValue', name: 'REPLICAS', value: "$replicas"]]
    
    //stage 'Integration Tests STAGE'
    //echo "Run your stage integration tests here. Skipping because this is a demo... Roll back if this fails!... oc rollback $APP"
}
