import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Linha linha = new Linha();
        Autocarro autocarro = new Autocarro();
        boolean sair = false;

        while (!sair)
        {
            System.out.println("\n=== Sistema de Gestão de Linha de Autocarros ===");
            System.out.println("1. Adicionar paragem");
            System.out.println("2. Remover paragem");
            System.out.println("3. Listar percurso");
            System.out.println("4. Adicionar passageiro a uma paragem");
            System.out.println("5. Simular chegada do autocarro a uma paragem");
            System.out.println("6. Ordenar paragens por nome");
            System.out.println("7. Ordenar paragens por número de passageiros");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // Limpar buffer

            switch (opcao)
            {
                case 1:
                    System.out.println("Digite o nome da paragem: ");
                    String nomeParagem = sc.nextLine();
                    linha.adicionarParagem(new Paragem(nomeParagem));
                    System.out.println("Paragem adicionada com sucesso!");
                    break;
                case 2:
                    System.out.println("Digite o nome da paragem a remover: ");
                    String nomeParagemRemover = sc.nextLine();
                    if (linha.removerParagem(nomeParagemRemover))
                    {
                        System.out.println("Paragem removida com sucesso!");
                    } 
                    else 
                    {
                        System.out.println("Paragem não encontrada.");
                    }
                    break;
                case 3:
                    System.out.println("Percruso da linha:");
                    linha.listarPercurso();
                    break;
                case 4:
                    System.out.println("Digite o nome da paragem para adicionar o passageiro: ");
                    String nomeParagemPassageiro = sc.nextLine();
                    Paragem paragem = encontrarParagem(linha, nomeParagemPassageiro);
                    if (paragem != null) 
                    {
                        System.out.println("Digite o nome do passageiro: ");
                        String nomePassageiro = sc.nextLine();
                        paragem.adicionarPassageiro(new Passageiro(nomePassageiro));
                        System.out.println("Passageiro adicionado com sucesso!");
                    } 
                    else 
                    {
                        System.out.println("Paragem não encontrada.");
                    }
                    break;
                case 5:
                    System.out.println("Digite o nome da paragem para simular a chegada do autocarro: ");
                    String nomeParagemChegada = sc.nextLine();
                    Paragem paragemChegada = encontrarParagem(linha, nomeParagemChegada);
                    if (paragemChegada != null)
                    {
                        Passageiro embarcado = paragemChegada.embarcarPassageiro();
                        if (embarcado != null)
                        {
                            autocarro.embarcarPassageiro(embarcado);
                            System.out.println("Passageiro " + embarcado.getNome() + " embarcou no autocarro.");
                        }
                        else
                        {
                            System.out.println("Não há passageiros para embarcar nesta paragem.");
                        }
                    }
                    else
                    {
                        System.out.println("Paragem não encontrada.");
                    }
                    break;
                case 6:

            }
            
        }


        sc.close();
        System.out.println("Sistema de Gestão de Linha de Autocarros");
    }

    private static Paragem encontrarParagem(Linha linha, String nome)
    {
        Paragem atual = linha.getInicio();
        while (atual != null)
        {
            if(atual.getNome().equalsIgnoreCase(nome))
                return atual;

            atual = atual.getProximaParagem();
        }
        return atual;
    }
}
