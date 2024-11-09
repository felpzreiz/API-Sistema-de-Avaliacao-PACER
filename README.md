# Sistema de Avaliação PACER

<i>Um projeto da equipe Alpha Code!</i>

### Índice

* [Descrição do Projeto e Funcionalidades](#descrição-do-projeto-e-funcionalidades)
* [Backlog do Produto](#backlog-do-produto)
* [Wireframe do projeto](#wireframe-do-projeto)
* [Modelo DER](#modelo-der)
* [Guia de Instalação e Manual](#guia-de-instalação)
* [Autores do Projeto](#autores-do-projeto-equipe-alpha-code)

# Descrição do Projeto e Funcionalidades

<div align="justify"> O presente projeto visa desenvolver um software intuitivo e seguro que empodera os alunos de uma instituição de ensino superior, proporcionando autonomia para gerenciar suas notas acadêmicas de forma personalizada. Essa ferramenta aumenta a transparência no processo avaliativo, fortalece o engajamento do aluno com seus estudos e otimiza os processos da instituição, liberando tempo para que professores e coordenadores se dediquem a atividades mais estratégicas. </div>

### Funcionalidades

* Permite que um aluno avalie todos os membros de sua equipe ao final de cada Sprint;

* Permitie que o professor gere um relatório contendo a nota média por aluno
  para cada critério de avaliação em uma determinada Sprint;

* Fornecer uma forma de carregar informações referentes aos grupos por meio de arquivo;

* Possibilita a edição de membros em grupos no caso de realocações;

* Permite o gerenciamento dos critérios de avaliação;

* Permite que o professor cadastre o calendário de Sprints para cada semestre;

<br>

### Metodologia de Desenvolvimento

<div align="justify">O desenvolvimento do projeto segue a metodologia Scrum, uma metodologia ágil a fim de otimizar o tempo e garantir flexibilidade e autonomia para os membro da equipe, dividindo-o em etapas de desenvolvimento que incluem análise de requisitos, desenvolvimento da interface, implementação das funcionalidades, casos de teste e integração de todas as partes. No decorrer da elaboração do produto serão utilizadas boas práticas de programação, documentação detalhada e revisões regulares para garantir a qualidade e robustez do software, além de reuniões para identificar as necessidades e prioridades da equipe, como um todo.</div>

### Tecnologias utilizadas

* <b>Linguagem de programação:</b> A linguagem de programação utilizada para o desenvolvimento do produto será Java, e o
  acesso ao banco de dados será feito com SQL. Para a visualização e criação da interface gráfica será utilizado o
  JavaFX Scene Builder;
* <b>IDE:</b> A IDE utilizada pela equipe será o IntelliJ IDEA;
* <b>SGBD:</b> O sistema gerenciador de banco de dados utilizado será o PostgreSQL;
* <b>Wireframe:</b> Para o desenho do wireframe do projeto, será utilizado o Figma;
* <b>Gerenciamento da equipe e do projeto:</b> Para o gerenciamento da equipe, serão utilizados o Jira, o Miro e o
  Github.

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=github,java,postgres,idea,figma" />
  </a>
</p>

<div align="center">

# Backlog do produto

| RANK | PRIORIDADE |                                                                                    USER STORIES                                                                                     | ESTIMATIVA | SPRINT | REQUISITO DO PARCEIRO |
|:----:|:----------:|:-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------:|:------:|:---------------------:|
|  1   |   MÉDIA    | Como professor, eu quero definir quais alunos irão participar dos processos de avaliação de forma simples e intuitiva, para que posteriormente eu possa formar grupos de avaliação. |  15 Dias   |   1    |           1           |
|  2   |   MÉDIA    |              Como professor, eu quero informar os alunos e grupos que vão se avaliar de forma fácil e rápida, a fim de otimizar o meu tempo e evitar possíveis erros.               |  15 Dias   |   2    |           2           |
|  3   |    ALTA    |              Como professor, eu quero que os alunos possam se autoavaliar e avaliar o grupo, para que eu não precise digitar as notas, afim de evitar possíveis erros.              |  15 Dias   |   2    |           3           |
|  4   |   MÉDIA    |      Como professor, eu quero definir os critérios de avaliação que irão compor as notas dos grupos, para que sejam consideradas competências relevantes para fins acadêmicos.      |  21 Dias   |   3    |           4           |
|  5   |    ALTA    |                          Como professor, eu quero definir o início e o fim das Sprints, para que os alunos tenham prazos para participarem das avaliações.                          |  21 Dias   |   3    |           5           |
|  6   |    ALTA    |                Como professor, eu desejo ver as notas e médias dos alunos e grupos avaliados, para que eu possa acompanhar os resultados de forma simples e rápida.                 |  21 Dias   |   4    |           6           |

</div>

# Backlog por Sprint

### Sprint 1. Concepção e Planejamento de Sprints

|  USER STORY  |                                                                                         Como professor, eu quero criar e verificar, grupos de avaliação, para que eu possa obter as informações de forma rápida e intuitiva.                                                                                         |
|:------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| **OBJETIVO** | **Desenvolver um sistema que permita ao professor gerenciar a participação dos alunos nas avaliações. Isso inclui a criação de grupos de avaliação, cadastro de alunos e a implementação de uma interface amigável para facilitar a navegação.  Nesta User Story será desenvolvido os aspectos inicias do projeto.** |

### Definition of Ready (DoR) - Sprint 1: Concepção e Planejamento de Sprints

* ### **Composição de Critérios para inicio da Sprint**

_<b>TELA DE LOGIN_

1. [X] Definição de Metodologia e recursos a serem utilizados no projeto.
2. [X] Desenvolmento de Tela de Login e Home para acesso do usuário (A tela de Login deve solicitar e-mail e senha).
3. [X] Verificação se as credenciais estão corretas (Em caso de erro retornar mensagem)

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img1.png" alt="Tela de Login" width="280" height="320">
<img src="documentacao/ImagesReadME/img2.png" alt="Tela de Login" width="280" height="320">
</div>

1. [X] Após Login direcionar para a tela Home do usuário.

_<b>TELA HOME_

1. [X] Desenvolmento da Tela Home
2. [X] A tela deve possuir os principais atalhos para navegação para utilização do professor.

<div style="text-align: center;">
<img src="documentacao/ImagesReadME/img.png" alt="Tela Home" width="600">
</div>

_<b>LAYOUT/DESIGN_

1. [X] Apresentação das informações de forma intuitiva
2. [X] O design das telas deve estar de acordo com a usabilidade e identidade visual da aplicação.

### Definition of Done (DoD) - Sprint 1: Concepção e Planejamento de Sprints

_<b>TELA DE LOGIN_

1. [X] O código para a tela de login foi desenvolvido e funciona corretamente.
2. [X] A tela de Login solicita as credenciais do usuário, caso seja inválido, uma alerta de erro é exibida.
3. [X] Após login correto, o usuário é direcionado para a tela Home.

_<b>TELA HOME_

1. [X] A tela Home está desenvolvida com os principais atalhos de navegação e permite a navegação entre as interfaces.

_<b>LAYOUT/DESIGN_

1. [X] A interface das telas foi projetada de forma intuitiva e de fácil navegação.
2. [X] A navegação entre as interfaces foi testado e estão funcionando corretamente.

_<b>SEGURANÇA E FUNCIONALIDADE_

1. [X] O código para verificação das credenciais de login está funcionando corretamente e de forma segura.
2. [X] Todos os fluxos de navegação (login → home) foram testados e estão funcionando corretamente.

### Sprint 2. Desenvolvimento do Projeto

| USER STORY |                              Como professor, eu quero informar os alunos e grupos que vão se avaliar de forma fácil e rápida, a fim de otimizar o meu tempo e evitar possíveis erros.                               |
|:----------:|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  OBJETIVO  | Facilitar as tarefas do professor, a importação de dados de alunos e grupos através de um arquivo CSV, garante que o processo seja intuitivo e seguro, contribuindo para uma gestão mais eficiente das informações. |

### Definition of Ready (DoR) - Sprint 2: Desenvolvimento do Projeto

* ### **Critérios de Aceitação**

_<b>INTEGRAÇÃO COM O BANCO DE DADOS_

1. [X] Criação do código de conexão com o Banco de Dados e Classe de operações SQL utilizando modelo DAO.
2. [X] A estrutura do banco de dados está configurada e pronta para armazenar os dados de alunos e grupos.
3. [X] Validação da estrutura de informações do arquivo CSV para integrar ao Banco de Dados.

_<b>TELA DE ALUNOS_

1. [X] O design da tela de importação desenvolvido para atender as necessidades do usuário.
2. [X] Funções de Adicionar, Editar, Excluir e Importar CSV.

<div style="text-align: center;">
<img src="documentacao/ImagesReadME/img3.png" alt="Botões da interface" width="600" height="200" title="Tela Alunos">
</div>

_<b>LAYOUT/DESIGN_

1. [X] Apresentação das informações de forma intuitiva
2. [X] O design das telas deve estar de acordo com a usabilidade e identidade visual da aplicação.

_<b>USABILIDADE_

1. [X] Análise do Código e Realização de Testes
2. [X] O processo de importação de dados deve ser intuitivo e seguro.
3. [X] As mensagens de erro, como falhas de importação, dados ausentes ou incorretos, estão especificadas.

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img4.png" alt="Validação" width="280" height="200">
<img src="documentacao/ImagesReadME/img5.png" alt="Validação" width="320" height="140">
<img src="documentacao/ImagesReadME/img6.png" alt="Validação" width="280" height="150">
</div>

### Definition of Done (DoD) - Sprint 2: Desenvolvimento do Projeto

_<b>INTEGRAÇÃO COM O BANCO DE DADOS_

1. [X] A integração com o Banco de Dados foi concluída.
2. [X] O código de conexão com o Banco de Dados está funcional.
3. [X] Os dados do arquivo CSV estão sendo incluidos com sucesso no Banco de Dados.

_<b>TELA DE ALUNOS_

1. [X] O professor pode fazer o upload de um arquivo CSV e os dados são importados corretamente.
2. [X] O sistema exibe uma mensagem de sucesso ou erro, dependendo do resultado da importação.
3. [X] A tela de alunos permite que o professor busque, visualize e manipule os dados se necessário.

_<b>LAYOUT/DESIGN_

1. [X] O processo de importação de CSV foi integrado de forma intuitiva.

_<b>USABILIDADE_

1. [X]  A importação de dados foi validada com diferentes cenários de dados (CSV com dados corretos, incompletos ou
2. [X]   inválidos).
3. [X] As funções de buscar, adicionar, editar e remover estão testadas e validadas.

_<b>TESTES_

1.[X] O código foi analisado, testado e revisado.

<br>

### Sprint 3 . Desenvolvimento do Projeto

<br>

##### **Na Sprint 3 serão desenvolvidas 3 User Stories**

<div style="text-align: center;">
    1. <strong>Título: Avaliação de Alunos</strong>
</div>

| USER STORY | Como professor, eu quero que os alunos possam se autoavaliar e avaliar o grupo, para que eu não precise digitar as notas, afim de evitar possíveis erros. |
|:----------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  OBJETIVO  |        Facilitar a autoavaliação e avaliação em grupo dos alunos, permitindo que os professores economizem tempo na digitação de notas dos alunos.        |

### Definition of Ready (DoR) - Avaliação de Alunos

* ### **Critérios de Aceitação**

_<b>BANCO DE DADOS_

1. [X] As tabelas e relações necessárias para armazenar as avaliações de alunos e grupos estão planejadas.
2. [X] As operações SQL necessárias para estão em desenvolvimento.
3. [X] A estrutura do banco de dados está definida para armazenar os dados

_<b>TELA HOME DO ALUNO_

1. [X] A tela de home do aluno deve permitir que ele vizualize os dados do seu grupo (outros integrantes) e realize
   avaliação com base nos critérios definidos pelo professor.

_<b>USABILIDADE_

1. [X] O processo de autoavaliação e avaliação dos integrantes do grupo deve ser claro.
2. [X] O sistema deve fornecer mensagens de erro caso o aluno ultrapasse a quantidade de pontos estabelecidos para
   avaliação.

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img8.png" alt="Validação" width="600" height="250">
<img src="documentacao/ImagesReadME/img7.png" alt="Validação" width="300" height="160">
</div>

### Definition of Done (DoD) - Avaliação de Alunos

_<b>BANCO DE DADOS_

1. [X] As tabelas para armazenar a autoavaliação e avaliação estão implementadas e integradas com o sistema.
2. [X] As operações SQL para inserir, atualizar e recuperar avaliações estão funcionando corretamente.

_<b>TELA HOME DO ALUNO_

1. [X] A tela de home para o aluno está funcional.
2. [X] Os alunos podem realizar autoavaliação e avaliação dos integrantes de grupo de maneira fácil e intuitiva.

_<b>USABILIDADE_

1. [X] O código foi revisado e testado para garantir a integridade e funcionalidade das avaliações.
2. [X] Mensagens de erro e confirmações de sucesso foram implementadas corretamente.

<br>
<br>
<div style="text-align: center;">
    2. <strong>Título: Definição de Sprints</strong>
</div>

| USER STORY | Como professor, eu quero definir o início e o fim das Sprints, para que os alunos tenham prazos para participarem das avaliações. |
|:----------:|:---------------------------------------------------------------------------------------------------------------------------------:|
|  OBJETIVO  |                                   Permitir que o professor controle os periodos das avaliações.                                   |

### Definition of Ready (DoR) - Definição de Sprints

* ### **Critérios de Aceitação**

_<b>BANCO DE DADOS_

1. [X] O banco de dados deve estar configurado para armazenar as datas de início e fim de cada Sprint.

_<b>TELA DE SPRINTS_

1. [X] A interface para criar e definir o início e fim de cada Sprint foi planejada.

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img9.png" alt="Validação" width="450" height="300">
</div>

_<b>USABILIDADE_

1. [X] O fluxo de criação e visualização das Sprints está claro e fácil de usar.

### Definition of Done (DoD) - Definição de Sprints

_<b>BANCO DE DADOS_

O código para definir as datas de início e fim das Sprints foi implementado com sucesso.

_<b>TELA DE SPRINTS_

1. [ ] O fluxo de configuração das Sprints está funcionando corretamente.

_<b>LAYOUT/DESIGN_

1. [X] O design da tela de Sprints foi implementado de acordo com os padrões de usabilidade e identidade visual.
2. [X] As informações de data de início e fim estão claras e organizadas na interface.

_<b>TESTES_

1. [X] O código foi analisado, testado e revisado.

   <br>
   <br>

<div style="text-align: center;">
    3. <strong>Título: Critérios de Avaliação</strong>
</div>

| USER STORY |    Como professor, eu quero definir os critérios de avaliação que irão compor as notas dos grupos, para que sejam consideradas competências relevantes para fins acadêmicos    |
|:----------:|:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|  OBJETIVO  | Permitir que o professor crie e gerencie critérios de avaliação, para que as notas reflitam habilidades e competências essenciais para o desenvolvimento acadêmico dos alunos. |

### Definition of Ready (DoR) - Critérios de Avaliação

* ### **Critérios de Aceitação**

_<b>BANCO DE DADOS_

O banco de dados deverá armazenar os critérios de avaliação.

_<b>TELA DE CRITÉRIOS_

1. [X] A tela para a Critérios foi planejada e aprovada.
2. [X] O sistema deve permitir ao professor definir os critérios.
3. [X] O professor pode adicionar e remover critérios conforme necessidade.

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img9.png" alt="Validação" width="450" height="300">
</div>

_<b>USABILIDADE_

1. [X] O processo de criação e manipulação dos critérios de avaliação deve ser intuitivo para o professor.

### Definition of Done (DoD) - Composição de Critérios de Avaliação

_<b>BANCO DE DADOS_

1. [X] As tabelas para armazenar os critérios estão funcionando corretamente.
2. [X] O sistema permite criar, e excluir critérios de avaliação.

_<b>TELA DE CRITÉRIOS_

1. [X] A tela para criação de critérios e está funcional.

_<b>LAYOUT/DESIGN_

1. [X] O design da tela de critérios foi implementado, com foco na usabilidade e identidade visual.

_<b>TESTES_

1. [X] O código foi analisado, testado e revisado.

<br>
<br>

### Sprint 4. Finalização do Projeto

| USER STORY | Como professor, eu desejo ver as notas e médias dos alunos e grupos avaliados, para que eu possa acompanhar os resultados de forma simples e rápida, |
|:----------:|:----------------------------------------------------------------------------------------------------------------------------------------------------:|
|  OBJETIVO  |                                     Permitir que o professor visualize de forma clara as informações existentes.                                     |

### Definition of Done (DoD) - Acesso aos Resultados de Avaliação

* ### **Critérios de Aceitação**

_<b>BANCO DE DADOS_

1. [X] As tabelas que armazenam as notas dos alunos e grupos foram criadas e estão funcionando corretamente.
2. [X] As operações SQL para calcular as notas dos alunos estão em desenvolvimento.
3. [X] As relações entre as tabelas de avaliação, alunos e grupos estão definidas.

_<b>TELA DE RESULTADOS_

1. [X] O design da tela que exibirá as notas alunos.
2. [X] O fluxo de navegação da tela está claro: o professor poderá acessar os resultados de maneira intuitiva, com
   filtros e visualização rápida das informações.
3. [X] A tela deverá permitir que o professor visualize os resultados detalhados (notas individuais e médias) para cada
   aluno e grupo, além de permitir o filtro por turma, sprint ou critério de avaliação.

<div style="display: flex; justify-content: center; gap: 20px;">
<img src="documentacao/ImagesReadME/img10.png" alt="Validação" width="450" height="300">
</div>

_<b>USABILIDADE_

1. [X] A interface de resultados deve ser clara e organizada, com as informações dispostas de maneira fácil.
2. [X] Mensagens de erro  (se necessário) devem ser implementadas, como quando não houver resultados ou quando ocorrerem
   problemas na consulta.
3. [X] A página deve ser carregada de forma rápida, mesmo com grandes volumes de dados (notas de muitos alunos e
   grupos).

### Definition of Done (DoD) - Acesso aos Resultados de Avaliação

######           * **Em desenvolvimento, disponibilizado no fim da Sprint**

<br>

## Wireframe do projeto

[Link Wireframe](https://www.figma.com/board/Ipnah7761NPMoBxkENVr4Y/Wireframe---Sistema-Pacer---Alpha-Code?node-id=2-6163&node-type=section&t=dFksliHYnWDUNbxQ-0)

## Modelo DER

[Link Modelo DER](documentacao/Modelo%20conceitual.png)

## Guia de Instalação e Manual

[Guia de Instalação](URL)

[Manual do Usuário](documentacao/Instruções%20de%20Uso%20Interface%20Aluno.pdf)

[Executável](out/artifacts/PacerAlphaCode_jar/PacerAlphaCode.jar)

## Autores do Projeto Equipe Alpha Code

|    Função     | Nome               |                                                                                                                                                   LinkedIn & GitHub                                                                                                                                                    |
|:-------------:|:-------------------|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Product Owner | José Wesley        |             [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/jwesley) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/jwesleey)              |
| Scrum Master  | Felipe Reis        |              [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](www.linkedin.com/in/felipe-reiss) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/felpzreiz)               |
|      Dev      | Gustavo Nascimento | [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/gustavo-mendes-b80008234) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/gustavonmendes01) |
|      Dev      | João Victor        |                                                                                       [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/joaovsnas)                                                                                        |
|      Dev      | Lailson Rodrigues  |    [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/lailson-silva-9854a7105/) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Lailson96)     |
|      Dev      | Victor Rodrigues   |                                                                                       [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Yzvictorr)                                                                                        |
