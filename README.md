# Sistema de Avaliação PACER

<i>Um projeto da equipe Alpha Code!</i>

### Índice 

* [Descrição do Projeto e Funcionalidades](#descrição-do-projeto-e-funcionalidades)
* [Backlog do Produto](#backlog-do-produto)
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
* <b>Linguagem de programação:</b> A linguagem de programação utilizada para o desenvolvimento do produto será Java, e o acesso ao banco de dados será feito com SQL. Para a visualização e criação da interface gráfica será utilizado o JavaFX Scene Builder;
* <b>IDE:</b> A IDE utilizada pela equipe será o IntelliJ IDEA;
* <b>SGBD:</b> O sistema gerenciador de banco de dados utilizado será o PostgreSQL;
* <b>Wireframe:</b> Para o desenho do wireframe do projeto, será utilizado o Figma;
* <b>Gerenciamento da equipe e do projeto:</b> Para o gerenciamento da equipe, serão utilizados o Jira, o Miro e o Github.

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=github,java,postgres,idea,figma" />
  </a>
</p>

<div align="center">
          
# Backlog do produto
          
| RANK  | PRIORIDADE | USER STORIES | ESTIMATIVA | SPRINT | REQUISITO DO PARCEIRO |
| :-----------: | :-----------------: | :--------: | :---------------: | :------------------: | :----: |
|1|MÉDIA|Como professor, eu quero definir quais alunos irão participar dos processos de avaliação de forma simples e intuitiva, para que posteriormente eu possa formar grupos de avaliação.|15 Dias|1|1|
|2|MÉDIA|Como professor, eu quero informar os alunos e grupos que vão se avaliar de forma fácil e rápida, a fim de otimizar o meu tempo e evitar possíveis erros.|15 Dias|2|2|
|3|ALTA|Como professor, eu quero criar e verificar grupos de avaliação, para que eu possa obter as informações de forma rápida e intuitiva.|15 Dias|2|3|
|4|MÉDIA|Como professor, eu quero definir os critérios de avaliação que irão compor as notas dos grupos, para que sejam consideradas competências relevantes para fins acadêmicos.|21 Dias|3|4|
|5|ALTA|Como professor, eu quero definir o início e o fim das Sprints, para que os alunos tenham prazos para participarem das avaliações.|21 Dias|3|5|
|6|ALTA|Como professor, eu quero que os alunos possam se autoavaliar e avaliar o grupo, para que eu não precise digitar as notas, afim de evitar possíveis erros.|15 Dias|4|6|
|7|ALTA|Como professor, eu desejo ver as notas e médias dos alunos e grupos avaliados, para que eu possa acompanhar os resultados de forma simples e rápida.|15 Dias|4|7|

</div>

# Backlog por Sprint 
### Sprint 1. Concepção e Planejamento de Sprints

<b>TÍTULO: Desenvolvimento Tela de Acesso Professor
| USER STORY  | Como professor, eu quero criar e verificar, grupos de avaliação, para que eu possa obter as informações de forma rápida e intuitiva. |
| :--: | :--: |

<b>TELA DE LOGIN
- [X] >> Desenvolmento Tela de Login
- [X] >> Solicita ao usuário o e-mail e senha de acesso
- [X] >> Verificação se as credenciais estão corretas (Em caso de erro retornar mensagem)
- [X] >> Após Login direcionar para a tela Home do usuário
      
<b>TELA HOME
- [X] >> Desenvolmento Tela Home
- [X] >>  A tela deve possuir os principais atalhos para navegação.
- [X] >> Criação de Grupos
- [X] >> Cadastro de Alunos
- [X] >> O sistema deve permitir a edição e atualização de grupos de avaliação.

<b>LAYOUT/DESIGN
- [X] >> Apresentação das informações de forma intuitiva
- [X] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.
<br>

### Sprint 2. Desenvolvimento do Projeto

<b>TÍTULO: Composição de Critérios de Avaliação
| USER STORY  | Como professor, eu quero definir os critérios de avaliação que irão compor as notas dos grupos, para que sejam consideradas competências relevantes para fins acadêmicos. |
| :--: | :--: |

<b>INTEGRAÇÃO COM O BANCO DE DADOS
- [X] >> Conectar os Critérios ao Banco de Dados
- [X] >> Conectar as Telas de Críterios e Grupos

<b>TELA DE CRITÉRIOS
- [X] >> Criação de Critérios de Avaliação
- [X] >> Manipulação de Critérios

<b>LAYOUT/DESIGN
- [X] >> Desenvolvimento Interface da Tela de Critérios
- [X] >> Apresentação das informações de forma intuitiva
- [X] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.

<b>USABILIDADE
- [X] >> Análise do Código e Realização de Testes

<br>

<b>TÍTULO: Desenvolvimento do Gerenciamento de Grupos
| USER STORY  | Como professor, eu quero criar e verificar, grupos de avaliação, para que eu possa obter as informações de forma rápida e intuitiva. |
| :--: | :--: |

<b>INTEGRAÇÃO
- [X] >> Criação do Código de Conexão para Banco de Dados
- [X] >> Criação do Código de Operações SQL
- [X] >> Criação do Banco de Dados (Tabelas)
- [X] >> Conectar os Alunos ao Banco de Dados
- [X] >> Conectar as Telas de Alunos e Grupos

<b>TELA DE GRUPOS
- [X] >> Criação de Grupos
- [X] >> Overview de Grupos
- [X] >> Manipular Informações e Integrantes

<b>LAYOUT/DESIGN
- [X] >> Desenvolvimento da Tela de Alunos
- [X] >> Desenvolvimento da Tela de Grupos
- [X] >> Apresentação das informações de forma intuitiva
- [X] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.

<b>USABILIDADE
- [X] >> Análise do Código e Realização de Testes
<br>

### Sprint 3 . Desenvolvimento do Projeto
<br>

<b>TÍTULO: Composição de Critérios de Avaliação
| USER STORY  | Como professor, eu quero definir os critérios de avaliação que irão compor as notas dos grupos, para que sejam consideradas competências relevantes para fins acadêmicos. |
| :--: | :--: |

<b>INTEGRAÇÃO DO BANCO DE DADOS
- [ ] >> Criação do Código de Operações SQL

<b>TELA DE CRITÉRIOS
- [ ] >> Criação de Critérios de Avaliação
- [ ] >> Manipulação de Critérios
      
<b>LAYOUT/DESIGN
- [ ] >> Desenvolvimento Interface da Tela de Critérios
- [ ] >> Apresentação das informações de forma intuitiva
- [ ] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.

<b>USABILIDADE
- [ ] >> Análise do Código e Realização de Testes
      
<br>

<b>TÍTULO: Desenvolvimento do Gerenciamento de Grupos
| USER STORY  | Como professor, eu quero criar e verificar, grupos de avaliação, para que eu possa obter as informações de forma rápida e intuitiva.|
| :--: | :--: |

<b>INTEGRAÇÃO DO BANCO DE DADOS
- [ ] >> Criação do Código de Operações SQL

<b>TELA DE GRUPOS
- [ ] >> Criação de Grupos
- [ ] >> Overview de Grupos
- [ ] >> Manipular Informações e Integrantes
      
<b>LAYOUT/DESIGN
- [ ] >> Desenvolvimento Interface da Tela de Grupos
- [ ] >> Apresentação das informações de forma intuitiva
- [ ] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.

<b>USABILIDADE
- [ ] >> Análise do Código e Realização de Testes

<br>

<b>TÍTULO: Definição de Sprints
| USER STORY  | Como professor, eu quero definir o início e o fim das Sprints, para que os alunos tenham prazos para participarem das avaliações.|
| :--: | :--: |

<b>INTEGRAÇÃO DO BANCO DE DADOS
- [ ] >> Criação do Código de Operações SQL

<b>TELA DE SPRINTS
- [ ] >> Criação de Sprints
- [ ] >> Controle de Inicio e Fim de Sprints
      
<b>LAYOUT/DESIGN
- [ ] >> Desenvolvimento Interface da Tela de Grupos
- [ ] >> Apresentação das informações de forma intuitiva
- [ ] >> O design das telas deve estar de acordo  com  a usabilidade e identidade visual da aplicação.

<b>USABILIDADE
- [ ] >> Análise do Código e Realização de Testes

### Sprint 4. Finalização do Projeto
<br>

# Autores do Projeto Equipe Alpha Code
|    Função     | Nome                                  |                                                                                                                                                      LinkedIn & GitHub                                                                                                                                                      |
| :-----------: | :------------------------------------ | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| Product Owner | José Wesley |[![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/jwesley) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/jwesleey) |
| Scrum Master  | Felipe Reis|[![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](www.linkedin.com/in/felipe-reiss) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/felpzreiz)     |
| Dev  | Gustavo Nascimento |      [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/gustavo-mendes-b80008234) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/gustavonmendes01)     |
|  Dev  | João Victor |          [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/joaovsnas)        |
|  Dev  | Lailson Rodrigues |  [![Linkedin Badge](https://img.shields.io/badge/Linkedin-blue?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/lailson-silva-9854a7105/) [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Lailson96)|
| Dev  | Victor Rodrigues |      [![GitHub Badge](https://img.shields.io/badge/GitHub-111217?style=flat-square&logo=github&logoColor=white)](https://github.com/Yzvictorr)     |
