# 📱 AppLogin

## 📌 Descrição
Aplicação Android desenvolvida em Java que permite o cadastro e autenticação de usuários utilizando banco de dados SQLite.

---

## 🛠️ Tecnologias Utilizadas
- Java
- Android Studio
- SQLite

---

## 📱 Funcionalidades
- Cadastro de usuário
- Validação de senha
- Armazenamento em banco local
- Login com verificação de credenciais
- Exibição de mensagens com Toast

---

## 📲 Estrutura do Projeto

### 📁 Pacote:
`br.com.senac.applogin`

### 📄 Classes:
- `MainActivity` → Tela inicial
- `LoginActivity` → Tela de login
- `RegistrarActivity` → Tela de cadastro
- `DBHelper` → Controle do banco de dados

---

## 🗂️ Banco de Dados

### Nome:
`BancoDados.db`

### Tabela:
```sql
CREATE TABLE utilizador(
    username TEXT PRIMARY KEY,
    password TEXT
);
