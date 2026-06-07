public class Ordenacao {
    public static void bubbleSortNome(Paragem[] paragens) 
    {
        int n = paragens.length;

        for (int i = 0; i < n - 1; i++) 
        {
            for (int j = 0; j < n - i - 1; j++) 
            {
                if (paragens[j].getNome().compareToIgnoreCase(paragens[j + 1].getNome()) > 0) 
                {
                    trocar(paragens, j, j + 1);
                }
            }
        }
    }

    public static void selectionSortPassageiros(Paragem[] paragens) 
    {
        int n = paragens.length;

        for (int i = 0; i < n - 1; i++) 
        {
            int indiceMenor = i;

            for (int j = i + 1; j < n; j++) 
            {
                if (paragens[j].getNumeroPassageiros() < paragens[indiceMenor].getNumeroPassageiros()) 
                {
                    indiceMenor = j;
                }
            }
            trocar(paragens, i, indiceMenor);
        }
    }

    private static void trocar(Paragem[] paragens, int a, int b) 
    {
        Paragem temp = paragens[a];
        paragens[a] = paragens[b];
        paragens[b] = temp;
    }
}
