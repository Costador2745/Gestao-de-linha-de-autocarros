import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Linha linha = new Linha();
        Autocarro autocarro = new Autocarro();
        boolean sair = false;

        while (!sair)
        {
            System.out.println("\n--- Sistema de Gestão de Linha de Autocarros ---");
            System.out.println("1. Adicionar paragem");
            System.out.println("2. Remover paragem");
            System.out.println("3. Listar percurso");
            System.out.println("4. Adicionar passageiro a uma paragem");
            System.out.println("5. Simular chegada do autocarro a uma paragem");
            System.out.println("6. Ordenar paragens por nome");
            System.out.println("7. Ordenar paragens por número de passageiros");
            System.out.println("8. Mostrar estado atual da linha");
            System.out.println("9. Mostrar passageiros no autocarro");
            System.out.println("10. Desembarcar passageiro do autocarro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

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
                    System.out.println("Percurso da linha:");
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
                    List<Paragem> paragensNome = linha.obterParagens();
                    Ordenacao.bubblesortNome(paragensNome);

                    System.out.println("Paragens ordenadas por nome:");
                    for (Paragem p : paragensNome) 
                    {
                        System.out.println(p.getNome() + " - Passageiros: " + p.getNumeroPassageiros());
                    }
                    break;
                case 7:
                    List<Paragem> paragensPassageiros = linha.obterParagens();
                    Ordenacao.bubblesortPassageiros(paragensPassageiros);

                    System.out.println("Paragens ordenadas por número de passageiros:");
                    for (Paragem p : paragensPassageiros) 
                    {
                        System.out.println(p.getNome() + " - Passageiros: " + p.getNumeroPassageiros());
                    }
                    break;
                case 8:
                    System.out.println("Estado atual da linha:");
                    linha.mostrarEstadoAtual();
                    break;
                case 9:
                    System.out.println("Passageiros no autocarro:");
                    System.out.println(autocarro.getPassageiros());
                    System.out.println("Total: " + autocarro.getNumeroPassageiros());
                    break;
                
                case 10:
                    System.out.println("Digite o nome do passageiro a desembarcar: ");
                    String nomeDesembarcar = sc.nextLine();
                    Passageiro passageiroParaSair = null;

                    for (Passageiro p : autocarro.getPassageiros()) 
                    {
                        if (p.getNome().equalsIgnoreCase(nomeDesembarcar)) 
                        {
                            passageiroParaSair = p;
                            break;
                        }
                    }

                    if (passageiroParaSair != null) 
                    {
                        autocarro.desembarcarPassageiro(passageiroParaSair);
                        System.out.println("Passageiro " + passageiroParaSair.getNome() + " saiu do autocarro.");
                    } 
                    else 
                    {
                        System.out.println("Passageiro não encontrado no autocarro.");
                    }
                    break;
                case 0:
                    sair = true;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
                    break;
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
