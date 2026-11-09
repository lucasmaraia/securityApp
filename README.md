# securityApp

API Spring Boot com autenticação JWT: cadastro de usuário, login que emite o token e endpoints protegidos que só respondem com um token válido.

> Projeto de estudo (maio/2025) para entender a fundo o Spring Security 6 — `SecurityFilterChain`, filtro customizado, `UserDetailsService` e geração/validação de JWT.

## Stack

- Java 21
- Spring Boot 3.5.0 (Web, Security, Data JPA)
- jjwt 0.12.6 (JWT)
- MySQL

## Estrutura

```
config/SecurityConfig.java        → SecurityFilterChain, sessão stateless, BCrypt
config/JwtFilter.java             → lê o header Authorization e autentica a request
service/JWTService.java           → gera e valida o token
service/MyUserDetailsService.java → carrega o usuário do banco
service/UserService.java          → cadastro e login
model/User.java                   → id, userName, password
model/UserDetailsCustom.java      → adapta User para o contrato do Spring Security
model/Student.java                → entidade de exemplo usada no recurso protegido
```

## Endpoints

| Método | Rota             | Autenticação | Descrição                        |
|--------|------------------|--------------|----------------------------------|
| `POST` | `/user/register` | Pública      | Cadastra um usuário              |
| `POST` | `/user/login`    | Pública      | Autentica e devolve o JWT        |
| `GET`  | `/welcome`       | JWT          | Endpoint de boas-vindas          |
| `GET`  | `/student`       | JWT          | Lista os registros de exemplo    |
| `POST` | `/student`       | JWT          | Cria um registro de exemplo      |

Fluxo:

```bash
# 1. cadastrar
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"lucas","password":"senha123"}'

# 2. logar e guardar o token
TOKEN=$(curl -s -X POST http://localhost:8080/user/login \
  -H "Content-Type: application/json" \
  -d '{"userName":"lucas","password":"senha123"}')

# 3. acessar recurso protegido
curl http://localhost:8080/student -H "Authorization: Bearer $TOKEN"
```

## Como rodar

Suba um MySQL e crie o schema:

```sql
CREATE DATABASE securityApp;
```

O `application.properties` aponta para `jdbc:mysql://localhost:3333/securityApp` com usuário `root`/`root` — ajuste porta e credenciais conforme o seu ambiente. O schema é criado pelo Hibernate (`ddl-auto=update`).

```bash
./mvnw spring-boot:run
```

## Limitações

As credenciais do banco e o usuário em memória (`spring.security.user.*`) estão fixos no `application.properties`, o que serve ao estudo local mas deveria virar variável de ambiente em qualquer uso real. A senha do JWT e o tempo de expiração também estão no código, não externalizados.
