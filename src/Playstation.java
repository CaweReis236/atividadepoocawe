/**
 * Parte 3 (3.3 e 3.4) — Playstation "tem um" DadosConsole (composição).
 *
 * Parte 4: a classe foi pensada para ser estendida. A fórmula do preço fica em
 * um único lugar (calcularPreco) e o que varia entre as versões é apenas o
 * percentual, exposto pelo gancho protegido getPercentual().
 */
public class Playstation implements IConsole {

    private static final double PERCENTUAL = 0.20;

    private final DadosConsole dados;

    public Playstation(String nome, double precoBase) {
        this.dados = new DadosConsole(nome, precoBase);
    }

    @Override
    public String getNome() {
        return dados.getNome();
    }

    public double getPrecoBase() {
        return dados.getPrecoBase();
    }

    /** Percentual de acréscimo sobre o preço base. Subclasses podem especializá-lo. */
    protected double getPercentual() {
        return PERCENTUAL;
    }

    @Override
    public void ligar() {
        System.out.println("Playstation ligado.");
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * (1 + getPercentual());
    }
}
