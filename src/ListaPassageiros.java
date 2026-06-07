public class ListaPassageiros {
    private NoPassageiro inicio;
    private int tamanho;

    public ListaPassageiros() 
    {
        inicio = null;
        tamanho = 0;
    }

    public void adicionar(Passageiro passageiro) 
    {
        NoPassageiro novo = new NoPassageiro(passageiro);

        if (inicio == null) 
        {
            inicio = novo;
        } 
        else 
        {
            NoPassageiro atual = inicio;

            while (atual.proximo != null) 
            {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
        tamanho++;
    }

    public Passageiro procurarPorNome(String nome) 
    {
        NoPassageiro atual = inicio;

        while (atual != null) 
        {
            if (atual.passageiro.getNome().equalsIgnoreCase(nome)) 
            {
                return atual.passageiro;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean removerPorNome(String nome) 
    {
        if (inicio == null) 
        {
            return false;
        }

        if (inicio.passageiro.getNome().equalsIgnoreCase(nome)) 
        {
            inicio = inicio.proximo;
            tamanho--;
            return true;
        }

        NoPassageiro atual = inicio;

        while (atual.proximo != null) 
        {
            if (atual.proximo.passageiro.getNome().equalsIgnoreCase(nome)) 
            {
                atual.proximo = atual.proximo.proximo;
                tamanho--;
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public int tamanho() 
    {
        return tamanho;
    }

    public void mostrar() 
    {
        if (inicio == null) 
        {
            System.out.println("Nenhum passageiro no autocarro.");
            return;
        }

        NoPassageiro atual = inicio;

        while (atual != null) 
        {
            System.out.println("- " + atual.passageiro.getNome());
            atual = atual.proximo;
        }
    }

    @Override
    public String toString() 
    {
        if (inicio == null) 
        {
            return "Nenhum passageiro";
        }

        String texto = "";
        NoPassageiro atual = inicio;

        while (atual != null) 
        {
            texto += atual.passageiro.getNome();

            if (atual.proximo != null) 
            {
                texto += " -> ";
            }
            atual = atual.proximo;
        }
        return texto;
    }

    private class NoPassageiro {
        Passageiro passageiro;
        NoPassageiro proximo;

        NoPassageiro(Passageiro passageiro) 
        {
            this.passageiro = passageiro;
            this.proximo = null;
        }
    }
}
