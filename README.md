<b>Decisões arquiteturais</b>:

  Projeto baseado nas referencias do livro clean architecture do Robert Cecil Martin.

  Esta arquitetura basea-se em deixar nossas entidade/dominio sempre como o core da nossa aplicação.
  Nossas regras de negócio, estarão em nossa camada de serviço, no qual desacoplamos as implementações através das injeções de dependência, desda forma
  dependemos de abstraçõee não de implementação.

  Temos nossa camada de repositories, no qual sua responsabilidade é de fazer acesso a dados.
  Utilizamos o solid como base para codificação.


<b>Versões de linguagem</b>:

  Java 11.

<b>VFerramentas utilizadas</b>::

  Banco em memória: h2database
  
  Spring-boot (Versão 2.6.1)
  
  JPA (2.6.1)
  
  Eclipse ( Versão: 2019-12 (4.14.0) )
  
  Apache Maven 3.6.0

<b>Instruções para a subida do projeto</b>:

Realizar a instalação do Apache Maven 3.6.0. ( https://maven.apache.org/install.html )

Realizar a instalação do Java 11. ( https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html )

Na raíz do projeto, onde se localiza o arquivo pom.xml, baixar as dependencias do projeto, através do comando mvn install.

Executar o projeto através do comando  ./mvnw spring-boot:run



