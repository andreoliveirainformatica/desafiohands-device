
#MicroService - Desafio Hands - User

#####Construir Docker image: mvn clean package docker:build
#####Rodar aplicação: docker run -p 8080:8080 -d desafio_hands 

##Tecnologias utilizadas:

###Desenvolvimento
Java 8

SpringBoot

SpringFox/Swagger - Documentação da API
###Testes
Jacoco - para testes de cobertura - Relatório gerado em: target/site/jacoco/index.html

Spock - para testes de BDD - Relatório gerado em: target/spock/index.html

JUnit - Teste Unitários

Mockito - Mock

MockMvc - Testes Integrados/Controller


##Arquitetura:

Clean architecture
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/andreoliveirainformatica/desafio/blob/master/CleanArchitecture.jpg)

##Utilização:

http://localhost:8080/swagger-ui.html
