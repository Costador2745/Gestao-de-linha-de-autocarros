import java.util.ArrayList;
import java.util.List;

public class Autocarro {
    private List<Passageiro> passageiros;

    public Autocarro() {
        this.passageiros = new ArrayList<>();
    }
    public void embarcarPassageiro(Passageiro passageiro) {
        passageiros.add(passageiro);
    }
    public void desembarcarPassageiro(Passageiro passageiro) {
        passageiros.remove(passageiro);
    }
    public List<Passageiro> getPassageiros() {
        return passageiros;
    }
    public int getNumeroPassageiros() {
        return passageiros.size();
    }
}
