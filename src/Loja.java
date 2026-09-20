import java.util.List;
import java.util.Locale;

/**
 * Parte 5 — A loja depende apenas da abstração IConsole. Não há if/else nem
 * instanceof: cada console sabe ligar-se e calcular o próprio preço
 * (polimorfismo), então novos consoles não exigem mudanças aqui (OCP).
 */
public class Loja {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    // 5.1
    public void venderConsole(IConsole console) {
        console.ligar();
        System.out.println(String.format(PT_BR, "%s -> Preço final: R$ %.2f",
                console.getNome(), console.calcularPreco()));
    }

    // 5.2
    public void venderVarios(List<IConsole> consoles) {
        for (IConsole console : consoles) {
            venderConsole(console);
        }
    }

    // 5.3
    public double calcularFaturamentoTotal(List<IConsole> consoles) {
        double total = 0;
        for (IConsole console : consoles) {
            total += console.calcularPreco();
        }
        return total;
    }
}
