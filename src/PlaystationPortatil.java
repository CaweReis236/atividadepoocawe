/**
 * Parte 4 — Herança usada de forma apropriada.
 *
 * Um Playstation Portátil É UM Playstation: continua ligando, tendo nome e
 * devolvendo um preço válido. A subclasse apenas especializa detalhes (a
 * mensagem de ligar e o percentual), sem remover nem recusar nenhum
 * comportamento herdado — portanto respeita o Princípio de Substituição de
 * Liskov (LSP).
 */
public class PlaystationPortatil extends Playstation {

    private static final double PERCENTUAL_PORTATIL = 0.15;

    public PlaystationPortatil(String nome, double precoBase) {
        super(nome, precoBase);
    }

    @Override
    public void ligar() {
        System.out.println("Playstation Portátil ligado.");
    }

    @Override
    protected double getPercentual() {
        return PERCENTUAL_PORTATIL;
    }
}
