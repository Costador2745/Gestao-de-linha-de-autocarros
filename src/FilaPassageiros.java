public class FilaPassageiros {
    private NoPassageiro inicio;
    private NoPassageiro fim;
    private int tamanho;

    public FilaPassageiros() 
    {
        inicio = null;
        fim = null;
        tamanho = 0;
    }

    public void adicionar(Passageiro passageiro) 
    {
        NoPassageiro novo = new NoPassageiro(passageiro);

        if (inicio == null) 
        {
            inicio = novo;
            fim = novo;
        } 
        else 
        {
            fim.proximo = novo;
            fim = novo;
        }
        tamanho++;
    }

    public Passageiro remover() 
    {
        if (inicio == null) 
        {
            return null;
        }

        Passageiro passageiro = inicio.passageiro;
        inicio = inicio.proximo;

        if (inicio == null) 
        {
            fim = null;
        }
        tamanho--;
        return passageiro;
    }

    public int tamanho() 
    {
        return tamanho;
    }

    public boolean estaVazia() 
    {
        return tamanho == 0;
    }

    public void mostrar() 
    {
        if (inicio == null) 
        {
            System.out.println("Fila vazia");
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
            return "Fila vazia";
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

    private class NoPassageiro 
    {
        Passageiro passageiro;
        NoPassageiro proximo;

        NoPassageiro(Passageiro passageiro) 
        {
            this.passageiro = passageiro;
            this.proximo = null;
        }
    }
}
