public class Paragem {
    private String nome;
    private FilaPassageiros fila;
    private Paragem proximaParagem;

    public Paragem(String nome) 
    {
        this.nome = nome;
        this.fila = new FilaPassageiros();
        this.proximaParagem = null;
    }

    public void adicionarPassageiro(Passageiro passageiro) 
    {
        fila.adicionar(passageiro);
    }

    public Passageiro embarcarPassageiro() 
    {
        return fila.remover();
    }

    public int getNumeroPassageiros() 
    {
        return fila.tamanho();
    }

    public void mostrarFila() {
        fila.mostrar();
    }

    public FilaPassageiros getFila() 
    {
        return fila;
    }

    public Paragem getProximaParagem() 
    {
        return proximaParagem;
    }

    public void setProximaParagem(Paragem proximaParagem) 
    {
        this.proximaParagem = proximaParagem;
    }

    public String getNome() 
    {
        return nome;
    }

    @Override
    public String toString() 
    {
        return nome + " - Passageiros em espera: " + getNumeroPassageiros();
    }
}
