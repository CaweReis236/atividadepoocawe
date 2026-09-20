# Atividade de revisão — Orientação a Objetos (Loja de Consoles)

Disciplina **GCVA — Gestão do Ciclo de Vida da Aplicação** · Linguagem **Java**

Evolução do código `Atividade_POO_Problema.java` em 5 etapas, aplicando encapsulamento, construtores, composição, interfaces, herança e polimorfismo.

## Estrutura

```
src/
├── IConsole.java            # Parte 3 — contrato (interface)
├── DadosConsole.java        # Partes 1, 2 e 3 — dados comuns (composição)
├── Nintendo.java            # Parte 3 — implementa IConsole
├── Playstation.java         # Parte 3 — implementa IConsole (pensada para ser estendida)
├── PlaystationPortatil.java # Parte 4 — herança: extends Playstation
├── Xbox.java                # Parte 5 — novo console, sem alterar Loja
├── Loja.java                # Parte 5 — polimorfismo, sem if/else
└── Main.java                # Partes 2 e 5 — montagem da lista e execução
```

## Como executar

Requer JDK 17 ou superior (ou abra a pasta em qualquer IDE Java e execute `Main`).

```bash
mkdir out
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

Saída esperada:

```
=== Vendas iniciais ===
Nintendo ligado.
Nintendo Switch -> Preço final: R$ 2200,00
Playstation ligado.
Playstation 5 -> Preço final: R$ 3600,00
Playstation Portátil ligado.
Playstation Portátil -> Preço final: R$ 2875,00
Faturamento total: R$ 8675,00

=== Vendas após adicionar o Xbox ===
Nintendo ligado.
Nintendo Switch -> Preço final: R$ 2200,00
Playstation ligado.
Playstation 5 -> Preço final: R$ 3600,00
Playstation Portátil ligado.
Playstation Portátil -> Preço final: R$ 2875,00
Xbox ligado.
Xbox Series X -> Preço final: R$ 4720,00
Faturamento total: R$ 13395,00
```

## Resumo das etapas

| Parte | O que foi feito | Onde |
|---|---|---|
| 1 — Encapsulamento | Atributos `private final` e apenas getters, sem setters | `DadosConsole` |
| 2 — Construtores | Construtor obrigatório com validação; `Main` usa `new ...(nome, preço)` | `DadosConsole`, subclasses, `Main` |
| 3 — Interface e composição | `IConsole`; `DadosConsole`; `Nintendo` e `Playstation` "têm um" `DadosConsole` e trazem a própria regra de ligar e de preço | `IConsole`, `DadosConsole`, `Nintendo`, `Playstation` |
| 4 — Herança | `PlaystationPortatil extends Playstation`, sobrescrevendo `ligar()` e o percentual (15%) | `PlaystationPortatil` |
| 5 — Polimorfismo / OCP | `Loja` recebe `IConsole`; `venderVarios` e `calcularFaturamentoTotal`; `Xbox` (18%) sem alterar `Loja` | `Loja`, `Xbox`, `Main` |

> Após a Parte 3 a classe `Console` original deixa de existir: seus dados foram para `DadosConsole` e seu comportamento (o `if/else` por `tipo`) foi distribuído entre as classes concretas. O campo `tipo` não é mais necessário, pois o próprio tipo da classe já identifica o console.

## Respostas às perguntas do roteiro

**Parte 1 — Depois de tornar os atributos privados, o `Main` ainda compila sem outras mudanças?**
Não. Linhas como `nintendo.nome = "..."` passam a dar erro de compilação (*has private access*), e a `Loja` também deixa de poder ler `console.tipo` ou alterar `console.preco`. Sem setters, o construtor (Parte 2) passa a ser o único caminho para informar os dados. Além disso, o código original alterava o preço do próprio objeto (`console.preco = console.preco * 1.10`), de modo que vender o mesmo console duas vezes aplicaria o acréscimo duas vezes; com o atributo privado o preço passa a ser apenas calculado.

**Parte 2 — Ainda é possível criar um objeto sem informar o preço?**
Não. `new Console("Nintendo Switch", "nintendo")` não compila, pois o construtor exige todos os parâmetros. Ao declarar um construtor, o Java também deixa de gerar o construtor padrão sem argumentos. O compilador garante que o valor foi informado, e o construtor garante que ele é válido (nome não vazio e preço maior que zero, lançando `IllegalArgumentException`).

**Parte 3 — Por que usar `DadosConsole` em vez de colocar nome/precoBase dentro de `Nintendo` e `Playstation`?**
Nome e preço base são dados comuns a todo console, e a composição os reaproveita sem criar uma hierarquia só para compartilhar atributos. A validação fica escrita em um único lugar, e cada console continua livre para apenas implementar `IConsole`. Se `Nintendo` e `Playstation` herdassem de uma classe base só por isso, ficariam acopladas por um motivo fraco, e o `Xbox` seria forçado a entrar nessa árvore.

**Parte 4 — Sobrescrever métodos herdados é um problema? Qual a diferença para o `jogarDisco()` do Solucao_P1?**
Não é um problema por si só. O que importa é se a subclasse continua cumprindo o contrato da superclasse (LSP), e não a quantidade de métodos sobrescritos.
- `PlaystationPortatil` continua ligando e devolvendo um preço válido; só muda a mensagem e o percentual. Em qualquer lugar em que se espera um `Playstation`, um portátil pode ser usado sem quebrar nada.
- No `jogarDisco()` do Solucao_P1, o método era sobrescrito para **lançar `UnsupportedOperationException`**, ou seja, a subclasse **recusava** algo que a superclasse prometia. Código que recebia um `Playstation` podia quebrar em tempo de execução ao receber um portátil, o que viola o LSP. A causa real era a capacidade estar no contrato errado; a solução seria separá-la em uma interface própria (ISP), assinada apenas por quem realmente a cumpre.

Mudar **como** um método cumpre sua promessa é polimorfismo saudável; deixar de cumpri-la é violação do LSP.

**Parte 5 (desafio final) — O que mudou em `Loja` para o Xbox funcionar? O que isso demonstra sobre o OCP?**
Nada: nenhuma linha de `Loja` foi alterada. Ela depende apenas da abstração `IConsole`, então qualquer classe que a implemente é aceita, com sua própria mensagem e seu próprio percentual. No código original, cada console novo exigiria mais um `else if` em `venderConsole()`, modificando uma classe já testada. Isso demonstra o Princípio Aberto/Fechado: `Loja` está **fechada para modificação** e o sistema está **aberto para extensão**, bastando criar uma nova classe.

## Decisões de projeto

- **Herança por especialização.** Em `Playstation`, a fórmula do preço fica em `calcularPreco()` e o percentual é exposto pelo método protegido `getPercentual()`. `PlaystationPortatil` sobrescreve apenas `ligar()` e `getPercentual()`, sem duplicar a fórmula nem alterar o estado da superclasse. Por isso o atributo `dados` pôde permanecer `private`.
- **Preço nunca é alterado.** `calcularPreco()` apenas calcula e devolve; o preço base é imutável.
- **`double` para valores monetários** foi mantido por simplicidade didática; em um sistema real o ideal seria `BigDecimal`, para evitar erros de arredondamento.
