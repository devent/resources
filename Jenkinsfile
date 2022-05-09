/**
 * Builds and deploys the project.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 4.6.1
 * @version 1.3.0
 */
pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: "3"))
        disableConcurrentBuilds()
        timeout(time: 60, unit: "MINUTES")
    }

    agent {
        label "maven"
    }

    stages {

        /**
        * The stage will checkout the current branch.
        */
        stage("Checkout Build") {
            steps {
                container("maven") {
                    checkout scm
                }
            }
        } // stage

        /**
        * The stage will compile, test and deploy on all branches.
        */
        stage("Compile, Test and Deploy") {
            steps {
                container("maven") {
                    script {
                        def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                        if (version =~ /(?i)-snapshot$/) {
                            sh "/setup-gpg.sh; mvn -s /m2/settings.xml -B clean install site:site deploy site:deploy"
                        } else {
                            sh "/setup-gpg.sh; mvn -s /m2/settings.xml -B clean install site:site site:deploy"
                        }
                    }
                }
            }
        } // stage

        /**
        * The stage will deploy the artifacts and the generated site to the public repository from the main branch.
        */
        stage("Publish to Private") {
            when {
                branch "main"
            }
            steps {
                container("maven") {
                    sh "/setup-gpg.sh; mvn -s /m2/settings.xml -B deploy"
                }
            }
        } // stage

        /**
        * The stage will deploy the artifacts and the generated site to the public repository from the main branch.
        */
        stage("Publish to Public") {
            when {
                branch "main"
            }
            steps {
                container("maven") {
                    sh "/setup-gpg.sh; mvn -s /m2/settings.xml -Posssonatype -B deploy"
                }
            }
        } // stage

    } // stages

    post {
        success {
            container("maven") {
                script {
                    def groupId = sh script: 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout', returnStdout: true
                    def artifactId = sh script: 'mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout', returnStdout: true
                    def version = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true
                    manager.createSummary("document.png").appendText("<a href=\"${env.JAVADOC_URL}/${groupId}/${artifactId}/${version}/index.html\">View Maven Site</a>", false)
                }
            }
        }
    } // post
        
}
