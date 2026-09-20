/**
 * Parte 3 (3.2) — Dados comuns a todo console, reaproveitados por composição.
 *
 * Partes 1 e 2 aplicadas aqui: atributos privados e imutáveis, apenas getters
 * (sem setters) e um construtor que garante que o objeto sempre nasce válido.
 */
public class DadosConsole {

    private final String nome;
    private final double precoBase;

    public DadosConsole(String nome, double precoBase) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do console é obrigatório.");
        }
        if (precoBase <= 0) {
            throw new IllegalArgumentException("O preço base deve ser maior que zero.");
        }
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }
}
