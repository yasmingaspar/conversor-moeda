# 💰 Conversor de Moedas em Tempo Real (Java Console)

Este projeto é um conversor de moedas interativo desenvolvido em Java. Ele permite ao usuário escolher entre diversas opções de conversão e obter taxas de câmbio dinâmicas e em tempo real, consumindo uma API externa.

## 🎯 Objetivos do Projeto

O objetivo principal deste projeto foi desenvolver uma aplicação Java que demonstrasse:

1.  **Interação via Console (I/O):** Criação de um menu interativo e leitura de entradas do usuário usando a classe `Scanner`.
2.  **Consumo de API Externa:** Utilização do `java.net.http.HttpClient` (a partir do Java 11) para realizar requisições HTTP GET.
3.  **Análise de Dados JSON:** Mapeamento eficiente da resposta JSON da API para objetos Java (POJOs) utilizando a biblioteca **Gson** (melhor prática).
4.  **Estrutura Orientada a Objetos (OO):** Separação de responsabilidades em classes distintas (`ApiClient`, `TaxaConversao`).

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Função |
| :--- | :--- |
| **Java 25 (JDK)** | Linguagem principal do projeto. |
| **`HttpClient`** | Módulo nativo do Java para fazer requisições HTTP. |
| **Gson (Google)** | Biblioteca para serialização e deserialização (Parsing) de JSON. |
| **ExchangeRate-API** | Serviço de API utilizado para obter as taxas de câmbio em tempo real. |

---

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para configurar e executar o conversor em sua máquina.

### 1. Requisitos

* Java Development Kit (**JDK 11 ou superior**).
* A biblioteca **Gson** configurada como dependência (via Maven, Gradle ou JAR manual).

### 2. Configuração da API Key 🔒

Este projeto requer uma chave de API da ExchangeRate-API. Para garantir a segurança (evitando o commit acidental da chave), ela deve ser fornecida como uma **variável de ambiente**.

**Não modifique diretamente nenhum arquivo de código-fonte** para inserir a chave.

### 3. Execução
1. Compile o projeto (via IDE como IntelliJ/Eclipse ou linha de comando).

2. Execute a classe principal: java Main.

O programa iniciará o menu interativo no console, onde você poderá escolher as opções de conversão.
### 📂 Estrutura do Código

O projeto é dividido em três classes principais:

| Classe | Descrição |
| :--- | :--- |
| **`Main.java`** | Contém o método `main`, o **Menu de Opções**, a lógica de leitura do `Scanner` e o método `processarConversao` (que orquestra o uso do `ApiClient` e do `Gson`). |
| **`ApiClient.java`** | Classe de comunicação. Constrói a URL e executa a requisição **HTTP GET** à ExchangeRate-API. |
| **`TaxaConversao.java`** | **POJO (Plain Old Java Object)**. Modelo de dados para mapear a resposta JSON da API (ex: `conversion_rate`, `base_code`, `target_code`). |


