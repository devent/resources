/**
 * Builds and deploys the project.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 4.4.0
 * @version 1.0.0
 */
pipeline {

    options {
        buildDiscarder(logRotator(numToKeepStr: "3"))
        disableConcurrentBuilds()
        timeout(time: 30, unit: "MINUTES")
    }

    agent {
        label 'maven-3-jdk-8'
    }

    stages {

		/**
		* The stage will checkout the current branch.
		*/
        stage("Checkout Build") {
            steps {
                container('maven') {
                    checkout scm
                }
            }
        }

		/**
		* The stage will setup the container for the build.
		*/
        stage('Setup Build') {
            steps {
                container('maven') {
                    withCredentials([string(credentialsId: 'gpg-key-passphrase', variable: 'GPG_PASSPHRASE')]) {
                        configFileProvider([configFile(fileId: 'gpg-key', variable: 'GPG_KEY_FILE')]) {
                            sh '/setup-gpg.sh'
                        }
                    }
                }
            }
        }

		/**
		* The stage will deploy the generated site for feature branches.
		*/
        stage('Deploy Site') {
    		when {
    			// skip if branch is master or develop.
		        branch '^(?!.*(master|develop)).*$'
			}
            steps {
                container('maven') {
                	configFileProvider([configFile(fileId: 'maven-settings-global', variable: 'MAVEN_SETTINGS')]) {
                    	withMaven() {
	                        sh '/setup-ssh.sh'
                        	sh '$MVN_CMD -s $MAVEN_SETTINGS -B site:site site:deploy'
                    	}
                    }
                }
            }
        } // stage

		/**
		* The stage will perform a release from the develop branch.
		*/
        stage('Release to Private') {
    		when {
		        branch 'develop'
		        expression {
		        	// skip stage if it is triggered by maven release.
					return !sh(script: "git --no-pager log -1 --pretty=%B", returnStdout: true).contains('[maven-release-plugin]')
				}
			}
            steps {
                container('maven') {
                	configFileProvider([configFile(fileId: 'maven-settings-global', variable: 'MAVEN_SETTINGS')]) {
                    	withMaven() {
	                        sh '/setup-ssh.sh'
                    	    sh 'git checkout develop && git pull origin develop'
                        	sh '$MVN_CMD -s $MAVEN_SETTINGS -B release:prepare'
                        	sh '$MVN_CMD -s $MAVEN_SETTINGS -B release:perform'
                    	}
                    }
                }
            }
        } // stage

		/**
		* The stage will deploy the artifacts and the generated site to the public repository from the master branch.
		*/
        stage('Publish to Public') {
    		when {
		        branch 'master'
			}
            steps {
                container('maven') {
                	configFileProvider([configFile(fileId: 'maven-settings-global', variable: 'MAVEN_SETTINGS')]) {
                    	withMaven() {
                            sh '$MVN_CMD -s $MAVEN_SETTINGS -Posssonatype -B deploy'
                    	}
                    }
                }
            }
        } // stage

    }
}
