// Pipeline as Code - A2 DevOps e QA
// Projeto: API de Gamificacao
// Etapa inicial: Integracao Continua (CI)

pipeline {
    agent any

    tools {
        jdk 'JDK23'
        maven 'maven'
    }

    stages {
        stage('0 - Preflight do Ambiente') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('1 - Checkout') {
            steps {
                checkout scm
            }
        }

        stage('2 - CI - JUnit + JaCoCo') {
            steps {
                bat 'mvn -B clean test'
            }
            post {
                always {
                    junit testResults: 'target/surefire-reports/*.xml',
                          allowEmptyResults: true

                    archiveArtifacts artifacts: 'target/site/jacoco/**',
                                     allowEmptyArchive: true
                }
            }
        }

        stage('3 - CI - Qualidade PMD') {
            steps {
                bat 'mvn -B pmd:pmd -DskipTests'
            }
            post {
                always {
                    archiveArtifacts artifacts: 'target/pmd.xml,target/site/pmd.html',
                                     allowEmptyArchive: true
                }
            }
        }

        stage('4 - CI - Package') {
            steps {
                bat 'mvn -B package -DskipTests'
            }
        }
    }

    stage('5 - CD - Docker Build') {
        steps {
            echo 'Iniciando build da imagem Docker da API Gamificacao'

            bat '"C:\\Users\\edson\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" compose -f docker-compose.yml build app'
        }
    }

    post {
        success {
            echo 'Pipeline CI executado com sucesso!'
        }

        failure {
            echo 'Falha na execucao do Pipeline CI.'
        }
    }
}