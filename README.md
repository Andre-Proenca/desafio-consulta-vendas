# Desafio: Consulta Vendas

## Formação Desenvolvedor Moderno
**Módulo**: Back End  
**Capítulo**: JPA, consultas SQL e JPQL  

[DevSuperior](https://devsuperior.com.br)

---

## Descrição do Projeto
Este projeto é parte da Formação Desenvolvedor Moderno e tem como objetivo aplicar conhecimentos em JPA, SQL e JPQL para implementar consultas em um sistema de vendas. O sistema gerencia **vendas (Sale)** e **vendedores (Seller)**, sendo que cada venda está associada a um vendedor, e um vendedor pode ter várias vendas.

Repositório do desafio: [devsuperior/desafio-consulta-vendas](https://github.com/devsuperior/desafio-consulta-vendas)

---

## Funcionalidades Implementadas

### 1. Relatório de Vendas
**Entrada**:
- Data inicial (opcional).
- Data final (opcional).
- Trecho do nome do vendedor (opcional).

**Saída**:
- Listagem paginada contendo:
  - ID da venda.
  - Data da venda.
  - Quantia vendida.
  - Nome do vendedor.

### 2. Sumário de Vendas por Vendedor
**Entrada**:
- Data inicial (opcional).
- Data final (opcional).

**Saída**:
- Listagem contendo:
  - Nome do vendedor.
  - Soma das vendas realizadas por cada vendedor no período informado.

---

## Tecnologias Utilizadas
- **Java**: Linguagem principal do projeto.
- **Spring Boot**: Framework para desenvolvimento rápido e robusto de aplicações Java.
- **JPA**: Mapeamento objeto-relacional.
- **Hibernate**: Implementação do JPA utilizada no projeto.
- **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- **Maven**: Gerenciador de dependências e build.

---

## Estrutura do Código
- **`Sale`**: Entidade que representa uma venda.
- **`Seller`**: Entidade que representa um vendedor.
- **Repositórios**:
  - `SaleRepository`: Contém as consultas específicas para vendas.
- **Serviços**:
  - `SaleService`: Implementa a lógica de negócios das consultas solicitadas no desafio.

---

## Exemplos de Consultas
### Relatório de Vendas (JPQL):
```java
@Query("SELECT new com.devsuperior.dto.SaleReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) " +
       "FROM Sale obj " +
       "WHERE (:minDate IS NULL OR obj.date >= :minDate) " +
       "AND (:maxDate IS NULL OR obj.date <= :maxDate) " +
       "AND (:name IS NULL OR LOWER(obj.seller.name) LIKE LOWER(CONCAT('%', :name, '%')))")
Page<SaleReportDTO> searchSales(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
```

### Sumário de Vendas (JPQL):
```java
@Query("SELECT new com.devsuperior.dto.SaleSummaryDTO(obj.seller.name, SUM(obj.amount)) " +
       "FROM Sale obj " +
       "WHERE (:minDate IS NULL OR obj.date >= :minDate) " +
       "AND (:maxDate IS NULL OR obj.date <= :maxDate) " +
       "GROUP BY obj.seller.name")
List<SaleSummaryDTO> summarizeSales(LocalDate minDate, LocalDate maxDate);
```

---

## Andre Leiva Proenca
Este desafio foi implementado como parte do curso da DevSuperior.

**Contato**:  
- [LinkedIn](https://www.linkedin.com/in/andreleivaproenca/)
