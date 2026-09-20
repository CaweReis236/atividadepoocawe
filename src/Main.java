import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        Locale ptBr = Locale.forLanguageTag("pt-BR");
        Loja loja = new Loja();

        // 2.2 — objetos criados pelo construtor, já completos e válidos.
        // 5.5 — ArrayList (e não List.of) porque o Xbox será adicionado depois.
        List<IConsole> consoles = new ArrayList<>();
        consoles.add(new Nintendo("Nintendo Switch", 2000));
        consoles.add(new Playstation("Playstation 5", 3000));
        consoles.add(new PlaystationPortatil("Playstation Portátil", 2500));

        System.out.println("=== Vendas iniciais ===");
        loja.venderVarios(consoles);
        System.out.println(String.format(ptBr, "Faturamento total: R$ %.2f",
                loja.calcularFaturamentoTotal(consoles)));

        // Novo console na lista — a classe Loja continua exatamente igual.
        consoles.add(new Xbox("Xbox Series X", 4000));

        System.out.println();
        System.out.println("=== Vendas após adicionar o Xbox ===");
        loja.venderVarios(consoles);
        System.out.println(String.format(ptBr, "Faturamento total: R$ %.2f",
                loja.calcularFaturamentoTotal(consoles)));
    }
}
