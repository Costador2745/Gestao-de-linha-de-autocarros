import java.util.ArrayList;
import java.util.List;

public class Linha {
    private Paragem primeiraParagem;

    public Linha() {
        this.primeiraParagem = null;
    }
    public void adicionarParagem(Paragem paragem) {
        if (primeiraParagem == null) 
        {
            primeiraParagem = paragem;
        } 
        else 
        {
            Paragem atual = primeiraParagem;
            while (atual.getProximaParagem() != null) 
            {
                atual = atual.getProximaParagem();
            }
            atual.setProximaParagem(paragem);
        }
    }
    public Paragem getPrimeiraParagem() {
        return primeiraParagem;
    }
    public boolean removerParagem(String nome) {
        if (primeiraParagem == null) {
            return false;
        }
        if (primeiraParagem.getNome().equals(nome)) {
            primeiraParagem = primeiraParagem.getProximaParagem();
            return true;
        }
        Paragem atual = primeiraParagem;
        while (atual.getProximaParagem() != null) {
            if (atual.getProximaParagem().getNome().equals(nome)) {
                atual.setProximaParagem(atual.getProximaParagem().getProximaParagem());
                return true;
            }
            atual = atual.getProximaParagem();
        }
        return false;
    }
    public void listarPercurso() {
        Paragem atual = primeiraParagem;
        while (atual != null) {
            System.out.println(atual.getNome());
            atual = atual.getProximaParagem();
        }
    }
    public Paragem getInicio()
    {
        return primeiraParagem;
    }
    
    public List<Paragem> obterParagens() {
    List<Paragem> paragens = new ArrayList<>();
    Paragem atual = primeiraParagem;

    while (atual != null) {
        paragens.add(atual);
        atual = atual.getProximaParagem();
    }

    return paragens;
}

public void mostrarEstadoAtual() {
    Paragem atual = primeiraParagem;

    while (atual != null) {
        System.out.println("Paragem: " + atual.getNome());
        System.out.println("Passageiros em espera: " + atual.getNumeroPassageiros());
        System.out.println("Fila: " + atual.getFila());
        System.out.println("----------------------");

        atual = atual.getProximaParagem();
    }
}
}
