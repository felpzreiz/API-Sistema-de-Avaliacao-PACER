# Sistema de Avaliação PACER

<i>Projeto desenvolvido pela da equipe Alpha Code</i>

<div style="text-align: left;">
<img src="/documentacao/Imagens/logo.png" alt="AlphaCode" width="400
">
</div>

## Guia de Instalação

Este guia tem como objetivo ajudá-lo a executar o Sistema de Avaliação desenvolvido.

### 1. Instalar o PostgreSQL e pgAdmin 4

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=postgres" />
  </a>
</p>

Para a execução do programa será necessário a instalação do SGBD PostgreSQL e o pgAdmin 4 que irá servir como a
plataforma de administrador para o PostgreSQL

PostgreSQL:
Baixe e instale o PostgreSQL a partir do site oficial: PostgreSQL Downloads.

pgAdmin 4:
Baixe e instale o pgAdmin 4 a partir do site oficial: pgAdmin Downloads.

### 2. Configuração do Banco de Dados

Após a instalação do PostgreSQL e pgAdmin 4, siga os passos abaixo para criar o banco de dados necessário para o
funcionamento do sistema:

Abra o pgAdmin 4

Clique sobre "Databases" e selecione Create > Database.

- Crie um Banco de Dados com o nome: `pacer`

### 3. Configuração das Tabelas

Abra o pgAdmin 4 e, com o banco de dados pacer selecionado, clique em Query Tool.

No Query Tool, clique em File > Open e selecione o arquivo SQL baixado.

O arquivo está disponível no link abaixo:

> [Script Banco de Dados](../SchemaSQL/Schema.sql)

Após, execute o seguinte comando para criar o acesso do professor à aplicação.

>` INSERT INTO usuario (email, senha) VALUES ('professor@fatec.sp.gov.br' , 'Informe aqui a senha de acesso desejada');`

> `Por padrão a senha de acesso dos alunos é a primeira String do e-mail de acesso.`

### 4. Executável

Agora que o banco de dados está configurado, faça o download do JDK para que seja possível o funcionamento do executável.

> [JDK](https://www.oracle.com/br/java/technologies/downloads/)

Após baixado e instalado você pode baixar o executável do Sistema de Avaliação para utilizá-lo
localmente.

> [Executável](/out/artifacts/PacerAlphaCode_jar)



