import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class Ordenacao {
    public static void bubblesortNome(List<Paragem> paragens) {
        int n = paragens.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (paragens.get(j).getNome().compareTo(paragens.get(j + 1).getNome()) > 0) {
                    Collections.swap(paragens, j, j + 1);
                }
            }
        }
    }
    public static void bubblesortPassageiros(List<Paragem> paragens) {
        int n = paragens.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (paragens.get(j).getNumeroPassageiros() > paragens.get(j + 1).getNumeroPassageiros()) {
                    Collections.swap(paragens, j, j + 1);
                }
            }
        }
    }
}
