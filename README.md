<b>Decisões arquiteturais</b>:

  Projeto baseado nas referencias do livro clean architecture do Robert Cecil Martin.

  Esta arquitetura basea-se em deixar nossas entidade/dominio sempre como o core da nossa aplicação.
  Nossas regras de negócio, estarão em nossa camada de serviço, no qual desacoplamos as implementações através das injeções de dependência, desda forma
  dependemos de abstraçõee não de implementação.

  Temos nossa camada de repositories, no qual sua responsabilidade é de fazer acesso a dados.
  Utilizamos o solid como base para codificação.

<img width="500" src="https://codersopinion.com/images/posts/clean-architecture/clean-architecture.png" alt="alternatetext">


<b>Versões de linguagem</b>:

  Java 11.

<b>VFerramentas utilizadas</b>::

  Banco em memória: h2database
  
  Spring-boot (Versão 2.6.1)
  
  JPA (2.6.1)
  
  Eclipse ( Versão: 2019-12 (4.14.0) )
  
  Apache Maven 3.6.0

<b>Instruções para a subida do projeto</b>:

Realizar a instalação do Apache Maven 3.6.0. (https://maven.apache.org/install.html)

Realizar a instalação do Java 11. (https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)

Na raíz do projeto, onde se localiza o arquivo pom.xml, baixar as dependencias do projeto, através do comando mvn install.

Executar o projeto através do comando  ./mvnw spring-boot:run


====================================================================================================================================================



Aqui está uma documentação leve da regra de negócio implementada neste código:

Serviço de Agendamento de Transferências (AgendaServiceImpl)
Esse serviço gerencia o agendamento e o cálculo de taxas para transferências financeiras, com base nas regras de negócio associadas ao tipo, valor e data da transferência.

Principais Métodos e Regras de Negócio
listar()

Recupera todas as transferências agendadas no sistema, retornando uma lista de objetos Agenda.
agendar(Agenda agenda)

Método principal de agendamento de transferências, responsável por definir a taxa apropriada para a transferência com base nas regras de negócio, que variam conforme o tipo de operação (OperacaoTipoA, OperacaoTipoB, OperacaoTipoC, OperacaoTipoD).
Regras de taxação são aplicadas em uma sequência de checagens condicionais para determinar o tipo de operação, conforme detalhado abaixo:
Operação Tipo D: Aplica uma regra específica para valores:
Valor entre R$ 0 e R$ 1.000: aplica a mesma taxa da Operação Tipo A.
Valor entre R$ 1.001 e R$ 2.000: aplica a taxa da Operação Tipo B.
Valor acima de R$ 2.000: aplica a taxa da Operação Tipo C.
Se nenhuma taxa for atribuída após a execução de OperacaoTipoD, são aplicadas as demais operações (OperacaoTipoA, OperacaoTipoB, OperacaoTipoC) em sequência, e o valor final da taxa é arredondado para duas casas decimais.
OperacaoTipoA(Agenda agenda)

Aplica uma taxa fixa para transferências onde a data de transferência é igual à data de agendamento.
Taxa calculada: R$ 3,00 mais 3% do valor da transferência.
OperacaoTipoB(Agenda agenda)

Aplica uma taxa fixa para transferências agendadas entre 2 e 10 dias após a data atual.
Taxa fixa: R$ 12,00.
OperacaoTipoC(Agenda agenda)

Aplica uma taxa percentual, que varia conforme a data de transferência:
Mais de 10 dias após a data atual: 8,2% do valor da transferência.
Mais de 20 dias após a data atual: 6,9% do valor.
Mais de 30 dias após a data atual: 4,7% do valor.
Mais de 40 dias após a data atual: 1,7% do valor.
obter(Integer id)

Recupera uma Agenda específica pelo seu identificador (ID). Caso o ID não seja encontrado, retorna um objeto Agenda vazio.
Resumo das Regras de Taxação
Operação Tipo A: Para transferências na mesma data de agendamento.
Operação Tipo B: Para transferências entre 2 e 10 dias após o agendamento.
Operação Tipo C: Para transferências com mais de 10 dias de antecedência, aplicando diferentes porcentagens conforme o período.
Operação Tipo D: Seleciona a regra de taxação com base no valor da transferência, delegando a lógica para as operações A, B ou C.
Observações
Este serviço utiliza o repositório AgendaRepository para persistir os dados das transferências e taxas.
O valor da taxa é sempre arredondado para duas casas decimais antes da persistência no banco.


