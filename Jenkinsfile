pipeline {
    agent any
    triggers {
        upstream(upstreamProjects: "gate-top", threshold: hudson.model.Result.SUCCESS)
    }
    tools { 
        maven 'Maven Current' 
        jdk 'JDK1.8' 
    }
    options {
        disableConcurrentBuilds()
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn -e clean compile' 
            }
        }
        stage('Document') {
            when{
                expression { currentBuild.currentResult != "FAILED" && currentBuild.changeSets != null && currentBuild.changeSets.size() > 0 }
            }
            steps {
                sh 'mvn -e site'
            }
            post {
                always {
                    //junit 'target/surefire-reports/**/*.xml'
                    findbugs canRunOnFailed: true, failedNewAll: '0', pattern: '**/findbugsXml.xml', unstableNewAll: '0', useStableBuildAsReference: true
                    warnings canRunOnFailed: true, consoleParsers: [[parserName: 'Java Compiler (javac)']], defaultEncoding: 'UTF-8', excludePattern: "**/test/**", failedNewAll: '0', unstableNewAll: '0', useStableBuildAsReference: true
                }
                success {
                    step([$class: 'JavadocArchiver', javadocDir: 'target/site/apidocs', keepAll: false])
                }
            }
        }
        stage('Deploy') {
            when{
                branch 'master'
                expression { currentBuild.currentResult == "SUCCESS" && currentBuild.changeSets != null && currentBuild.changeSets.size() > 0 }
            }
            steps {
                sh 'mvn -e -Dmaven.test.skip=true source:jar javadoc:jar deploy'
            }
        }
    }
}
