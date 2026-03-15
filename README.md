# API SCTEC - Documentação

A API SCTEC é um sistema de gerenciamento de **Empreendimentos** e seus respectivos **Sócios**, criada especialmente para o programa SCTEC. 
Foi desenvolvida utilizando Spring Boot 3, Java 17, Spring Data JPA e MapStruct.

## 📌 Acesso Rápido

- **Base URL (Local):** `http://localhost:8080`
- **Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 17** ou superior instalado na máquina.
- **Maven** instalado.

### Passos para rodar localmente
1. Abra o terminal e navegue até a pasta raiz do projeto.
2. Compile o código e baixe as dependências:
   ```bash
   mvn clean install
   ```
3. Inicie a aplicação Spring Boot:
   ```bash
   mvn spring-boot:run
   ```
*Alternativa:* Pode-se abrir o projeto em uma dessas IDEs (IntelliJ IDEA, Eclipse, VS Code) e dar um "Run" diretamente na classe principal `SctecApplication.java`.

---

## 🛠️ Tecnologias Utilizadas

* **Spring Boot 3** - Framework principal
* **Spring Data JPA** - Persistência de dados
* **H2 Database** - Banco de dados em memória (para testes)
* **MapStruct** - Mapeamento entre Entidades e DTOs
* **Jakarta Validation** - Validação de dados de entrada
* **Springdoc OpenAPI (Swagger)** - Documentação interativa da API
* **Lombok** - Redução de código boilerplate (Getters, Setters, etc.)

---

## 🗄️ Banco de Dados (H2 em Memória)

O projeto foi configurado com o **H2 Database**, um banco de dados relacional que roda na memória da aplicação. Isso facilitará a execução e desenvolvimento local, pois não requer a instalação de SGBDs externos (como PostgreSQL ou MySQL).

**⚠️ Aviso Importante:** Como é um banco em memória, **todos os dados são perdidos** sempre que a aplicação é reiniciada. 

### Como acessar o painel do banco:
Para visualizar as tabelas e manipular os dados do banco por meio de uma interface web nativa:
1. Com a aplicação no ar, acesse o navegador no link: `http://localhost:8080/h2-console`
2. Na tela de login, preencha as informações padrão do Spring Boot:
   - **JDBC URL:** `jdbc:h2:mem:testdb` 
   - **User Name:** `sa`
   - **Password:** *(deixe este campo em branco)*
3. Clique em **Connect** para visualizar as tabelas de `EMPREENDIMENTO` e `SOCIO` e testar as queries.

---

## 📦 Formato Padrão de Resposta

Todas as respostas da API são envelopadas em um objeto padrão. Isso facilita o tratamento de retornos para um futuro Front-end.

**Exemplo de Sucesso (`200 OK` / `201 Created`):**
```json
{
  "success": true,
  "data": { ... } // Dados retornados (objeto ou array)
}
```

**Exemplo de Erro (`400 Bad Request` / `404 Not Found`):**
```json
{
  "success": false,
  "errors": [
    "O nome do empreendimento é obrigatório",
    "Empreendimento não encontrado"
  ]
}
```

---

## 🏢 Módulo: Empreendimentos

O recurso principal do sistema. Gerencia os dados de empreendimentos cadastrados.

### 1. Criar um Empreendimento
- **Endpoint:** `POST /empreendimento`
- **Descrição:** Cadastra um novo empreendimento no sistema.

**Corpo da Requisição (JSON):**
```json
{
  "nomeEmpreendimento": "Inova Tech Soluções LTDA",
  "nomeEmpreendedor": "Carlos Eduardo Silva",
  "municipio": "Florianópolis",
  "segmentoAtuacao": "TECNOLOGIA",
  "email": "contato@inovatech.com.br",
  "status": "ATIVO"
}
```
*Nota: `segmentoAtuacao` aceita: `TECNOLOGIA`, `COMERCIO`, `INDUSTRIA`, `SERVICOS`, `AGRONEGOCIO`.*

**Resposta (`201 Created`):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nomeEmpreendimento": "Inova Tech Soluções LTDA",
    "nomeEmpreendedor": "Carlos Eduardo Silva",
    "municipio": "Florianópolis",
    "segmentoAtuacao": "TECNOLOGIA",
    "email": "contato@inovatech.com.br",
    "status": "ATIVO",
    "dataCriacao": "2024-03-14T10:30:00",
    "dataAtualizacao": "2024-03-14T10:30:00",
    "socios": []
  }
}
```

### 2. Listar Empreendimentos
- **Endpoint:** `GET /empreendimento`
- **Descrição:** Retorna todos os empreendimentos cadastrados com seus respectivos sócios.

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "nomeEmpreendimento": "Inova Tech Soluções LTDA",
      // ... outros campos
    }
  ]
}
```

### 3. Atualizar Empreendimento
- **Endpoint:** `PATCH /empreendimento/{id}`
- **Descrição:** Atualiza os dados de um empreendimento existente.

**Corpo da Requisição (JSON):**
```json
{
  "municipio": "São José",
  "email": "novo_email@inovatech.com.br"
}
```

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nomeEmpreendimento": "Inova Tech Soluções LTDA",
    "municipio": "São José",
    "email": "novo_email@inovatech.com.br",
    // ... outros campos
  }
}
```

### 4. Alterar Status (Ativar / Inativar)
A API fornece endpoints de ação específicos para regras de negócio:

- **Endpoint:** `PUT /empreendimento/{id}/ativar`
- **Endpoint:** `PUT /empreendimento/{id}/inativar`

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "status": "INATIVO"
    // ... outros campos
  }
}
```

### 5. Excluir Empreendimento
- **Endpoint:** `DELETE /empreendimento/{id}`
- **Descrição:** Remove o empreendimento e todos os sócios atrelados a ele (Exclusão em cascata).

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": "Empreendimento com ID 1 foi excluído com sucesso."
}
```

---

## 🤝 Módulo: Sócios

Gerencia as pessoas físicas ou jurídicas associadas a um empreendimento específico.

### 1. Adicionar Sócio
- **Endpoint:** `POST /empreendimento/{empreendimentoId}/socios`
- **Descrição:** Vincula um novo sócio a um empreendimento.

**Corpo da Requisição (JSON):**
```json
{
  "nome": "Maria Tereza Souza",
  "cpf": "12345678900",
  "qualificacao": "Sócio-Administrador"
}
```

**Resposta (`201 Created`):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "nome": "Maria Tereza Souza",
    "cpf": "12345678900",
    "qualificacao": "Sócio-Administrador"
  }
}
```

### 2. Listar Sócios de um Empreendimento
- **Endpoint:** `GET /empreendimento/{empreendimentoId}/socios`
- **Descrição:** Retorna a lista de todos os sócios de um determinado empreendimento.

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "nome": "Maria Tereza Souza",
      "cpf": "12345678900",
      "qualificacao": "Sócio-Administrador"
    }
  ]
}
```

### 3. Remover Sócio
- **Endpoint:** `DELETE /empreendimento/{empreendimentoId}/socios/{socioId}`
- **Descrição:** Remove o vínculo e exclui o sócio do sistema.

**Resposta (`200 OK`):**
```json
{
  "success": true,
  "data": "Sócio removido com sucesso."
}
```

---

