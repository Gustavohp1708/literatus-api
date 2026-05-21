# Desafio Técnico: API REST para Gestão de Biblioteca 

---

# 2. Requisitos Funcionais (O que a API deve fazer)

## Gestão de Categorias

### RF01 - Criar Categoria
Permitir registar uma nova categoria.

Exemplos:
- Ficção Científica
- História
- Tecnologia

---

### RF02 - Listar Categorias
Listar todas as categorias registadas no sistema.

---

## Gestão de Livros

### RF03 - Registar Livro
Permitir o registo de um livro associando-o obrigatoriamente a uma categoria existente por meio do ID dela.

---

### RF04 - Listar Livros
Devolver todos os livros registados.

---

### RF05 - Buscar Livro por ID
Devolver os detalhes de um livro específico.

---

### RF06 - Atualizar Livro
Permitir alterar as informações de um livro existente:
- título
- autor
- isbn
- ano de publicação
- associação de categoria

A atualização pode ser:
- parcial
- total

---

### RF07 - Remover Livro
Permitir excluir um livro do catálogo pelo seu ID.

---

### RF08 - Filtrar Livros por Categoria (Bónus)
Permitir listar livros filtrando:
- pelo nome da categoria
- ou pelo ID da categoria

---

# 3. Requisitos Técnicos e Arquitetura

## Tecnologias
- Java 17+ (ou 21)
- Spring Boot 3.x

---

## Gestor de Dependências
- Maven
- ou Gradle

---

## Base de Dados
- H2 Database (em memória, para facilitar os testes rápidos)

---

## Persistência
- Spring Data JPA

---

# Arquitetura em Camadas

## `controller`
Responsável por:
- exposição dos endpoints REST
- ativação das validações com `@Valid`

### Exemplo
```java
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(
            @RequestBody @Valid LivroRequestDTO dto) {
        return null;
    }
}
```

---

## `service`
Responsável por:
- regras de negócio
- transações (`@Transactional`)
- validações
- comunicação com a persistência

### Exemplo
```java
@Service
public class LivroService {

    @Transactional
    public LivroResponseDTO criar(LivroRequestDTO dto) {
        return null;
    }
}
```

---

## `repository`
Interfaces que estendem `JpaRepository`.

### Exemplo
```java
public interface LivroRepository extends JpaRepository<Livro, Long> {
}
```

---

## `domain` (ou `model`)
Entidades JPA mapeadas para as tabelas da base de dados.

### Exemplo
```java
@Entity
@Table(name = "livros")
public class Livro {
}
```

---

## `dto`
Objetos de transferência de dados (Records) para:
- entrada de dados
- saída de dados

### Exemplo
```java
public record LivroRequestDTO(
        String titulo,
        String autor,
        String isbn,
        Integer anoPublicacao,
        Long categoriaId
) {
}
```

---

## `exception`
Manipulador global de erros com:
```java
@RestControllerAdvice
```

---

# 4. Modelo de Dados Sugerido

# Entidade Categoria

| Campo | Tipo | Regras |
|---|---|---|
| id | Long | Chave Primária, gerada automaticamente |
| nome | String | Obrigatório e único |

---

## Exemplo da Entidade Categoria

```java
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

}
```

---

# Entidade Livro

| Campo | Tipo | Regras |
|---|---|---|
| id | Long | Chave Primária, gerada automaticamente |
| titulo | String | Obrigatório |
| autor | String | Obrigatório |
| isbn | String | Obrigatório e único |
| anoPublicacao | Integer | Opcional |
| categoria | Categoria | Relacionamento Many-to-One |

---

## Relacionamento

```java
@ManyToOne
@JoinColumn(name = "categoria_id", nullable = false)
private Categoria categoria;
```

---

## Exemplo da Entidade Livro

```java
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false, unique = true)
    private String isbn;

    private Integer anoPublicacao;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

}
```

---

# 5. Endpoints Esperados e Códigos de Resposta

| Método | Endpoint | Payload | Código de Sucesso | Casos de Erro |
|---|---|---|---|---|
| POST | `/api/categorias` | JSON com nome | `201 Created` | `400 Bad Request` |
| GET | `/api/categorias` | Nenhum | `200 OK` | - |
| POST | `/api/livros` | JSON com dados do livro e `categoriaId` | `201 Created` | `400 Bad Request`, `404 Not Found` |
| GET | `/api/livros` | Nenhum | `200 OK` | - |
| GET | `/api/livros/{id}` | Nenhum | `200 OK` | `404 Not Found` |
| PUT | `/api/livros/{id}` | JSON parcial ou total | `200 OK` | `400 Bad Request`, `404 Not Found` |
| DELETE | `/api/livros/{id}` | Nenhum | `204 No Content` | `404 Not Found` |

---

# Exemplos de Payloads

## Criar Categoria

### Request
```json
{
  "nome": "Tecnologia"
}
```

### Response
```json
{
  "id": 1,
  "nome": "Tecnologia"
}
```

---

## Criar Livro

### Request
```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008,
  "categoriaId": 1
}
```

### Response
```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008,
  "categoria": {
    "id": 1,
    "nome": "Tecnologia"
  }
}
```

---

## Atualizar Livro

### Request
```json
{
  "titulo": "Clean Architecture"
}
```

### Response
```json
{
  "id": 1,
  "titulo": "Clean Architecture",
  "autor": "Robert C. Martin",
  "isbn": "9780132350884",
  "anoPublicacao": 2008
}
```

---

# 6. Regras de Validação e Tratamento de Erros

# Validação de Entradas (Bean Validation)

## Livro

Os seguintes campos:
- título
- autor
- isbn

não podem ser:
- nulos
- vazios

Utilizar:
```java
@NotBlank
```

---

## categoriaId

Na criação do livro:
- obrigatório
- validado com `@NotNull`

### Exemplo
```java
@NotNull
private Long categoriaId;
```

---

## Categoria

O nome da categoria:
- não pode ser nulo
- não pode ser vazio

Utilizar:
```java
@NotBlank
```

---

# Exemplo de DTO com Validação

```java
public record LivroRequestDTO(

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "O autor é obrigatório")
        String autor,

        @NotBlank(message = "O ISBN é obrigatório")
        String isbn,

        Integer anoPublicacao,

        @NotNull(message = "A categoria é obrigatória")
        Long categoriaId

) {
}
```

---

# Tratamento Global de Exceções

Utilizar:
```java
@RestControllerAdvice
```

---

## Exemplo Completo

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResponseDTO> handleNotFound(
            EntityNotFoundException ex) {

        ErroResponseDTO erro = new ErroResponseDTO(
                ex.getMessage(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
```

---

# Casos Esperados

## Recurso Não Encontrado

Se o cliente tentar:
- buscar
- atualizar
- deletar

um livro/categoria inexistente, a API deve retornar:

```http
404 Not Found
```

---

## Exemplo de resposta

```json
{
  "erro": "Livro com ID 1 não encontrado",
  "timestamp": "2026-05-20T20:00:00"
}
```

---

# Erros de Validação

Se as validações falharem:
- título em branco
- categoriaId nulo
- autor vazio
- isbn vazio

a API deve retornar:

```http
400 Bad Request
```

Detalhando:
- quais campos falharam
- quais regras foram violadas

---

## Exemplo de resposta de validação

```json
{
  "status": 400,
  "erros": [
    {
      "campo": "titulo",
      "mensagem": "O título é obrigatório"
    },
    {
      "campo": "categoriaId",
      "mensagem": "A categoria é obrigatória"
    }
  ]
}
```

---

# Estrutura de Pastas Sugerida

```text
src/main/java/com/literatus/api
│
├── controller
│   ├── CategoriaController.java
│   └── LivroController.java
│
├── service
│   ├── CategoriaService.java
│   └── LivroService.java
│
├── repository
│   ├── CategoriaRepository.java
│   └── LivroRepository.java
│
├── domain
│   ├── Categoria.java
│   └── Livro.java
│
├── dto
│   ├── request
│   └── response
│
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ErroResponseDTO.java
│
└── LiteratusApiApplication.java
```

---

# Objetivo do Desafio

O objetivo deste desafio é avaliar:
- organização de código
- arquitetura REST
- boas práticas com Spring Boot
- modelagem de entidades
- uso correto de DTOs
- validações
- tratamento de erros
- separação em camadas
- clareza e legibilidade do código
- capacidade de estruturar uma API profissional nível júnior