/**
 * Parte 3 (3.3 e 3.4) — Nintendo "tem um" DadosConsole (composição) e carrega
 * a sua própria regra de ligar e de preço (acréscimo de 10%).
 */
public class Nintendo implements IConsole {

    private static final double PERCENTUAL = 0.10;

    private final DadosConsole dados;

    public Nintendo(String nome, double precoBase) {
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
        System.out.println("Nintendo ligado.");
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * (1 + PERCENTUAL);
    }
}
