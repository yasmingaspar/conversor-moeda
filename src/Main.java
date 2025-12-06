import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Main {
    private static final ApiClient API_CLIENT = new ApiClient();
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("🎉 BEM-VINDO(A) ao Conversor de Moedas!");

        while (opcao != 7) {
            exibirMenu();

            try {
                opcao = Integer.parseInt(scanner.nextLine());


                switch (opcao) {
                    case 1: converterMoeda("USD", "BRL", scanner); break;
                    case 2: converterMoeda("BRL", "USD", scanner); break;
                    case 3: converterMoeda("EUR", "BRL", scanner); break;
                    case 4: converterMoeda("JPY", "USD", scanner); break;
                    case 5: converterMoeda("EUR", "JPY", scanner); break;
                    case 6: converterPersonalizada(scanner); break;
                    case 7: System.out.println("Obrigado por usar o conversor. Encerrando..."); break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido para a opção.");
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }



    private static void exibirMenu() {
        System.out.println("\n--- Escolha uma Opção de Conversão ---");
        System.out.println("1. USD (Dólar Americano) -> BRL (Real Brasileiro)");
        System.out.println("2. BRL (Real Brasileiro) -> USD (Dólar Americano)");
        System.out.println("3. EUR (Euro) -> BRL (Real Brasileiro)");
        System.out.println("4. JPY (Iene Japonês) -> USD (Dólar Americano)");
        System.out.println("5. EUR (Euro) -> JPY (Iene Japonês)");
        System.out.println("6. Outras Moedas (Inserir códigos)");
        System.out.println("7. Sair");
        System.out.print("Sua opção: ");
    }

    private static void converterMoeda(String base, String alvo, Scanner scanner) {
        System.out.printf("Digite o valor em %s para converter para %s: ", base, alvo);
        try {
            double valor = Double.parseDouble(scanner.nextLine());
            processarConversao(base, alvo, valor);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor inválido. Use apenas números.");
        }
    }

    private static void converterPersonalizada(Scanner scanner) {
        System.out.print("Digite o código da Moeda Base (Ex: USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Digite o código da Moeda Alvo (Ex: EUR): ");
        String alvo = scanner.nextLine().toUpperCase();

        System.out.printf("Digite o valor em %s para converter para %s: ", base, alvo);

        try {
            double valor = Double.parseDouble(scanner.nextLine());
            processarConversao(base, alvo, valor);
        } catch (NumberFormatException e) {
            System.out.println("Erro: Valor inválido. Use apenas números.");
        }
    }

    // Lógica principal de conversão e exibição
    private static void processarConversao(String base, String alvo, double valor) {
        String jsonResponse = API_CLIENT.buscaTaxa(base, alvo);

        if (jsonResponse != null) {
            try {
                TaxaConversao taxa = GSON.fromJson(jsonResponse, TaxaConversao.class);

                if (taxa.getResult() != null && !taxa.getResult().equals("success")) {
                    System.err.println("Erro da API: A requisição não foi bem-sucedida. Verifique os códigos das moedas e sua chave.");
                    return;
                }

                double taxaValor = taxa.getConversionRate();
                double valorConvertido = valor * taxaValor;

                System.out.println("---------------------------------------------");
                System.out.println(taxa.toString());
                System.out.printf("Resultado da Conversão: %.2f %s = %.2f %s\n",
                        valor, base, valorConvertido, alvo);
                System.out.println("---------------------------------------------");

            } catch (JsonSyntaxException e) {
                System.err.println("Erro ao processar a resposta da API. Verifique se o JSON é válido.");
            }
        }
    }
}