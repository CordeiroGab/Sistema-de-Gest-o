# 🛒 **Sistema de Gestão Comercial - Mercado+**  
*Controle de Estoque e Vendas com Java Swing + PostgreSQL*  

[![Java](https://img.shields.io/badge/Java-22-%23ED8B00?logo=openjdk)](https://www.oracle.com/java/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-%23336791?logo=postgresql)](https://www.postgresql.org/)
[![GitLab](https://img.shields.io/badge/Repositório-GitLab-%23FC6D26?logo=gitlab)](https://gitlab.com/GabrielEngsoft/bancodedados02)

---

## 👥 **Desenvolvedores**  
- **João Guilherme Mendonça de Oliveira**  
- **Gabriel Neves Cordeiro**  

---

## 🎯 **Objetivo do Projeto**  
Sistema desenvolvido para a disciplina **Banco de Dados 2**, com o propósito de:  
✔ Automatizar a gestão de mercados e pequenos comércios  
✔ Garantir **controle preciso** de estoque (entradas, saídas e validades)  
✔ Oferecer **relatórios comerciais** através de consultas SQL avançadas  
✔ Demonstrar na prática a integração entre:  
  - **Java Swing** (interface amigável)  
  - **PostgreSQL** (banco de dados robusto)  
  - **JDBC** (conexão segura com `PreparedStatement`)  

---

## ✨ **Funcionalidades**  
| **Módulo**       | **Recursos**                              | **Tecnologia**                  |
|------------------|------------------------------------------|---------------------------------|
| 🔐 Autenticação  | Login com criptografia de senha          | `JPasswordField` + `BCrypt`     |
| 📦 Estoque       | CRUD de produtos + movimentações         | `JTable` + Transações ACID      |
| 📊 Relatórios    | Consultas com `JOIN` e `GROUP BY`        | PostgreSQL + `ResultSet`        |
| 👥 Usuários      | Hierarquia de acesso (admin/operador)    | Chaves estrangeiras no BD       |

---

## 🚀 **Como Executar**  
1. **Pré-requisitos**:  
   - Java 22  
   - PostgreSQL 16  
   - Git  

2. **Configuração**:  
   ```bash
   git clone https://gitlab.com/GabrielEngsoft/bancodedados02.git
   cd bancodedados02