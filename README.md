# Automação de Inserção de Frases no Anki

Este projeto foi desenvolvido para automatizar a inserção de frases no Anki, um software de estudo baseado em flashcards, visando otimizar o tempo e manter a consistência no aprendizado. A aplicação foi criada com **Java**, utilizando o **Spring Framework**, gerenciada com **Maven**, documentada com **Swagger**, e containerizada com **Docker**.  


## Funcionalidades

- **Automação da inserção de frases no Anki**: O usuário pode enviar frases formatadas no payload correto, junto com o nome do deck e do modelo de cartão.
- **Integração com Anki Connect**: O projeto utiliza a [API Anki Connect](https://foosoft.net/projects/anki-connect/) para realizar a inserção dos dados diretamente no Anki.
- **Suporte a MultiPartFile**: Aceita o upload de arquivos contendo os dados necessários.
- **Arquitetura MVC**: Organização simples e eficiente com separação clara de responsabilidades.
- **Documentação de API com Swagger**: Interface amigável para explorar e testar os endpoints disponíveis.
- **Integração com Docker**: Imagem disponível no Docker Hub para fácil execução.
- **Automatização com GitHub Actions**: Build automatizado da imagem Docker sempre que o arquivo `pom.xml` for alterado.


## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **Swagger**
- **Docker**
- **MultiPartFile**
- **GitHub Actions** para CI/CD
- **Anki Connect** para integração com o Anki

## Desenho Solução

![desenho-solucao-automatizacaoanki-01](https://github.com/user-attachments/assets/be457805-7cec-49f9-b57f-ac8f8d0d5f22)
## Como Utilizar


### Pré-requisitos

1. Certifique-se de que o **Anki** está em execução e que o **Anki Connect** está instalado e configurado.
2. Para o uso da aplicação containerizada, tenha o **Docker** instalado em sua máquina.

### Rodando com Docker

1. Faça o pull da imagem no Docker Hub:  
   ```bash
   docker pull florluan/automatizacao-cards
   ```

2. Execute o container:  
   ```bash
   docker run --network host florluan/automatizacao-cards
   ```

    > **Nota:** A opção `--network host` é utilizada para permitir que o container se comunique diretamente com o Anki na máquina host.

3. Acesse o Swagger para explorar o endpoint e ralizar testes diretamente pela interface:
    ```bash
    http://localhost:8080/swagger-ui.html
    ```



## Dependências Externas

**Anki Connect**: O plugin [Anki Connect](https://foosoft.net/projects/anki-connect/) deve estar instalado no Anki para que a aplicação funcione corretamente. Certifique-se de que ele está configurado e ativado antes de rodar o projeto.


## Contribuição

Contribuições são bem-vindas! Caso queira sugerir melhorias, resolver problemas ou adicionar novas funcionalidades, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Contato

Desenvolvido por **Luan**  
📧 *[luan.flor05@gmail.com](mailto:luan.flor05@gmail.com)*


