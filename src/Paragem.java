import java.util.LinkedList;
import java.util.Queue;

public class Paragem {
    private String nome;
    private Queue<Passageiro> fila;
    private Paragem proximaParagem;

    public Paragem(String nome) {
        this.nome = nome;
        this.fila = new LinkedList<>();
        this.proximaParagem = null;
    }
    public void adicionarPassageiro(Passageiro passageiro) {
        fila.add(passageiro);
    }
    public Passageiro embarcarPassageiro() {
        return fila.poll();
    }
    public int getNumeroPassageiros() {
        return fila.size();
    }
    public Queue<Passageiro> getFila() {
        return fila;
    }
    public Paragem getProximaParagem() {
        return proximaParagem;
    }
    public void setProximaParagem(Paragem proximaParagem) {
        this.proximaParagem = proximaParagem;
    }
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Paragem{" + "nome='" + nome + '\'' + ", fila=" + fila + ", proximaParagem=" + (proximaParagem != null ? proximaParagem.getNome() : "null") + '}';
    }
}
