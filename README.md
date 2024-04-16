# Índice

- [Título e Sobre](#título-e-sobre)
- [Considerações de implementação](#considerações-de-implementação)
- [Objetivo do projeto](#objetivo-do-projeto)
- [Tecnologias utilizadas](#tecnologias-utilizadas)
- [Como rodar o projeto localmente](#como-rodar-o-projeto-localmente)

# Título e Sobre

**Projeto Teste Anbima e Orlatech**
O projeto consiste na criação de uma API RESTful com os recurso de funcionários e projetos. E ela possui as operações de: cadastro, busca de um único recurso e busca de vários recursos. E existe um relacionamento de N:N entre funcionários e projetos. Quando listado um ou mais funcionários, vai trazer junto os projetos que o funcionário faz parte e quando listado um ou mais projetos, vai trazer em cada projeto uma lista de funcionários que fazem parte de tal projeto. E existem 2 enpoints que modificam essa relação: um é para adicionar o funcionário ao projeto e outro para remover ele do projeto.

# Considerações de implementação

Aqui neste README não irei detalhar cada rota da API pois já existe uma documentação desenvolvida utilizando o Swagger com esse propósito. E para vizualizar ela basta acessar a url: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) após rodar o projeto localmente.

# Objetivo do projeto

O projeto **Teste Anbima e Orlatech** tem como objetivo demonstrar minhas seguintes habilidades como Desenvolvedor de Software com ênfase no back-end:

- Programação orientada a objetos (POO)
- Java
- Spring Framework
- Testes automatizados
- Persistência de dados em banco de dados
- Documentação de projeto
- Padrões de projeto e de arquitetura.

# Tecnologias utilizadas

- Java (Linguagem de programação)
- Spring (Framework)
- Swagger (Bliblioteca de documentação de API)
- MYSQL (Banco de dados)
- Junit e Spring Boot Starter Test (Testes automatizados)
- Spring Boot Starter Security (Para criptografar senha no banco de dados)
- Data JPA (Framework de ORM(Object Relational Mapping))

# Como rodar o projeto localmente

1. Clone o repositório

Abra o terminal e execute o comando `git clone git@github.com:fernandoluckesi/orlatech-anbima.git ou git clone https://github.com/fernandoluckesi/orlatech-anbima.git`

2. Instale as dependências de desenvolvimento

Acesse o diretótio onde está o projeto e execute `docker compose up` (Certifique-se de ter o Docker instalado)

3. Para rodar a API execute:

Abra outro terminal e ainda no mesmo diretótio do projeto execute o comando `mvn spring-boot:run`

4. Executando os testes automatizados:

Abra outro terminal e ainda no mesmo diretótio do projeto execute o comando `mvn test`

4. Realizar build do projeto :

No terminal e ainda no mesmo diretótio do projeto execute o comando `mvn clean install`
