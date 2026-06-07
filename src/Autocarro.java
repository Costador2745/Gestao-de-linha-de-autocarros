public class Autocarro {
    private ListaPassageiros passageiros;

    public Autocarro() 
    {
        passageiros = new ListaPassageiros();
    }

    public void embarcarPassageiro(Passageiro passageiro) 
    {
        passageiros.adicionar(passageiro);
    }

    public boolean desembarcarPassageiro(String nome) 
    {
        return passageiros.removerPorNome(nome);
    }

    public Passageiro procurarPassageiro(String nome) 
    {
        return passageiros.procurarPorNome(nome);
    }

    public void mostrarPassageiros() 
    {
        passageiros.mostrar();
    }

    public int getNumeroPassageiros() 
    {
        return passageiros.tamanho();
    }
}
