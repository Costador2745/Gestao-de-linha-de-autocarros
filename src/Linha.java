public class Linha {
    private Paragem primeiraParagem;
    private int totalParagens;

    public Linha() 
    {
        primeiraParagem = null;
        totalParagens = 0;
    }

    public void adicionarParagem(Paragem paragem) 
    {
        if (primeiraParagem == null) 
            primeiraParagem = paragem;
        else 
        {
            Paragem atual = primeiraParagem;

            while (atual.getProximaParagem() != null) 
            {
                atual = atual.getProximaParagem();
            }
            atual.setProximaParagem(paragem);
        }
        totalParagens++;
    }

    public boolean removerParagem(String nome) {
        if (primeiraParagem == null) 
        {
            return false;
        }

        if (primeiraParagem.getNome().equalsIgnoreCase(nome)) 
        {
            primeiraParagem = primeiraParagem.getProximaParagem();
            totalParagens--;
            return true;
        }

        Paragem atual = primeiraParagem;

        while (atual.getProximaParagem() != null) 
        {
            if (atual.getProximaParagem().getNome().equalsIgnoreCase(nome)) 
            {
                atual.setProximaParagem(atual.getProximaParagem().getProximaParagem());
                totalParagens--;
                return true;
            }
            atual = atual.getProximaParagem();
        }

        return false;
    }

    public void listarPercurso() 
    {
        if (primeiraParagem == null) 
        {
            System.out.println("A linha ainda não tem paragens.");
            return;
        }

        Paragem atual = primeiraParagem;

        while (atual != null) 
        {
            System.out.println("- " + atual.getNome());
            atual = atual.getProximaParagem();
        }
    }

    public Paragem encontrarParagem(String nome) 
    {
        Paragem atual = primeiraParagem;

        while (atual != null) 
        {
            if (atual.getNome().equalsIgnoreCase(nome)) 
            {
                return atual;
            }
            atual = atual.getProximaParagem();
        }

        return null;
    }

    public Paragem[] obterParagens() 
    {
        Paragem[] paragens = new Paragem[totalParagens];
        Paragem atual = primeiraParagem;
        int i = 0;

        while (atual != null) 
        {
            paragens[i] = atual;
            atual = atual.getProximaParagem();
            i++;
        }
        return paragens;
    }

    public void mostrarEstadoAtual() 
    {
        if (primeiraParagem == null) 
        {
            System.out.println("A linha ainda não tem paragens.");
            return;
        }

        Paragem atual = primeiraParagem;

        while (atual != null) 
        {
            System.out.println("Paragem: " + atual.getNome());
            System.out.println("Passageiros em espera: " + atual.getNumeroPassageiros());
            System.out.println("Fila: " + atual.getFila());
            System.out.println("----------------------");
            atual = atual.getProximaParagem();
        }
    }

    public Paragem getInicio() 
    {
        return primeiraParagem;
    }
}
