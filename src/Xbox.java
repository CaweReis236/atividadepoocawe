/**
 * Parte 5 (5.4) — Novo console adicionado SEM alterar nenhuma linha de Loja.
 */
public class Xbox implements IConsole {

    private static final double PERCENTUAL = 0.18;

    private final DadosConsole dados;

    public Xbox(String nome, double precoBase) {
        this.dados = new DadosConsole(nome, precoBase);
    }

    @Override
    public String getNome() {
        return dados.getNome();
    }

    public double getPrecoBase() {
        return dados.getPrecoBase();
    }

    @Override
    public void ligar() {
        System.out.println("Xbox ligado.");
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * (1 + PERCENTUAL);
    }
}
