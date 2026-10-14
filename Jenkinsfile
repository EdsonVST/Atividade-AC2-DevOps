
pipeline {
    agent any

    tools {
        jdk 'JDK23'
        maven 'maven'
    }

    environment {
        PATH+NODEJS = 'C:\\Program Files\\nodejs'
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

        // Constroi a imagem Docker da aplicacao.
        stage('5 - CD - Docker Build') {
            steps {
                echo 'Iniciando build da imagem Docker da API Gamificacao'

                bat '"C:\\Users\\edson\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" compose -f docker-compose.yml build app'
            }
        }

        // Inicia os containers da aplicacao e do monitoramento.
        stage('6 - CD - Docker Deploy') {
            steps {
                echo 'Iniciando deploy da API Gamificacao'

                bat '"C:\\Users\\edson\\AppData\\Local\\Programs\\DockerDesktop\\resources\\bin\\docker.exe" compose -f docker-compose.yml up -d --no-build'
            }
        }

        // Executa os testes automatizados da API com Cypress.
        stage('7 - CI - Cypress E2E') {
            steps {
                echo 'Verificando Node.js e NPM'

                bat 'node -v'
                bat 'npm.cmd -v'

                echo 'Instalando dependencias do Cypress'

                bat 'npm.cmd ci'

                echo 'Executando testes automatizados da API'

                bat 'npx.cmd cypress run --spec "cypress/e2e/gamificacao.cy.js" --browser edge'
            }
        }
    }

    post {
        success {
            echo 'Pipeline CI/CD executado com sucesso!'
        }
        failure {
            echo 'Falha na execucao do Pipeline CI/CD.'
        }
    }
}
