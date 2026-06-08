# Gestão de Linha de Autocarros

## 1. Introdução

Este projeto foi desenvolvido no âmbito da unidade curricular de **Algoritmos e Estruturas de Dados**.

O objetivo do projeto é simular um sistema simples de gestão de uma linha de autocarros, permitindo criar paragens, gerir passageiros em espera, simular a chegada do autocarro, embarcar passageiros, desembarcar passageiros, mostrar o estado atual da linha e ordenar as paragens.

O sistema foi implementado em **Java** e funciona através de um menu na consola.

Nesta versão, as estruturas de dados principais foram implementadas manualmente, sem usar estruturas prontas como `ArrayList`, `List`, `Queue`, `LinkedList` ou `Collections`.

Foram criadas estruturas próprias para representar:

- a linha de autocarro através de uma linked list;
- a fila de passageiros em cada paragem;
- a lista de passageiros dentro do autocarro.

---

## 2. Objetivo do projeto

O programa permite:

- adicionar paragens a uma linha de autocarro;
- remover paragens da linha;
- listar o percurso completo;
- adicionar passageiros a uma determinada paragem;
- gerir passageiros em espera através de uma fila FIFO criada manualmente;
- simular a chegada do autocarro a uma paragem;
- embarcar passageiros no autocarro;
- mostrar os passageiros atualmente dentro do autocarro;
- desembarcar passageiros do autocarro;
- ordenar paragens por nome;
- ordenar paragens por número de passageiros;
- mostrar o estado atual da linha.

---

## 3. Estruturas de dados utilizadas

### 3.1 Linked list da linha

A linha de autocarro é representada através de uma **linked list de paragens**.

Cada objeto `Paragem` guarda uma referência para a próxima paragem:

```java
private Paragem proximaParagem;
```

A classe `Linha` guarda a primeira paragem da lista:

```java
private Paragem primeiraParagem;
```

A partir da primeira paragem, o programa consegue percorrer todas as outras usando um ciclo `while`.

Exemplo:

```text
Paragem1 -> Paragem2 -> Paragem3 -> null
```

Esta estrutura foi usada porque o enunciado pedia que o percurso da linha fosse representado por uma linked list.

---

### 3.2 Fila de passageiros

Em cada paragem existe uma fila de passageiros.

Esta fila segue o princípio **FIFO**:

```text
First In, First Out
```

Ou seja, o primeiro passageiro a entrar na fila é o primeiro passageiro a sair.

A fila foi implementada manualmente na classe:

```java
FilaPassageiros
```

A classe `FilaPassageiros` usa nós internos ligados entre si.

Atributos principais:

```java
private NoPassageiro inicio;
private NoPassageiro fim;
private int tamanho;
```

O atributo `inicio` aponta para o primeiro passageiro da fila.  
O atributo `fim` aponta para o último passageiro da fila.  
O atributo `tamanho` guarda o número de passageiros na fila.

Quando um passageiro é adicionado, entra no fim da fila.  
Quando um passageiro embarca, sai do início da fila.

---

### 3.3 linked list de passageiros do autocarro

Os passageiros que estão dentro do autocarro são guardados numa linked list própria.

Essa lista foi implementada manualmente na classe:

```java
ListaPassageiros
```

A classe `Autocarro` usa essa lista:

```java
private ListaPassageiros passageiros;
```

Esta estrutura permite:

- adicionar passageiros ao autocarro;
- remover passageiros pelo nome;
- procurar passageiros pelo nome;
- mostrar todos os passageiros dentro do autocarro;
- contar o número total de passageiros.

---

### 3.4 Array auxiliar para ordenação

A linha principal continua a ser uma linked list.

No entanto, para ordenar as paragens, o programa cria um array auxiliar de paragens:

```java
Paragem[] paragens
```

Este array é usado apenas para aplicar os algoritmos de ordenação de forma simples.

A linked list original não é destruída. O array serve apenas para mostrar as paragens ordenadas.

---

## 4. Classes do projeto

### 4.1 Classe `Passageiro`

A classe `Passageiro` representa uma pessoa que pode estar numa fila ou dentro do autocarro.

Atributo principal:

```java
private String nome;
```

Métodos principais:

```java
public Passageiro(String nome)
```

Cria um passageiro com o nome indicado.

```java
public String getNome()
```

Devolve o nome do passageiro.

```java
public String toString()
```

Devolve uma representação em texto do passageiro.

---

### 4.2 Classe `Paragem`

A classe `Paragem` representa uma paragem da linha de autocarro.

Atributos principais:

```java
private String nome;
private FilaPassageiros fila;
private Paragem proximaParagem;
```

O atributo `nome` guarda o nome da paragem.  
O atributo `fila` guarda os passageiros que estão à espera.  
O atributo `proximaParagem` aponta para a próxima paragem da linked list.

#### `Paragem(String nome)`

Construtor da classe.

Cria uma paragem com o nome indicado, cria uma fila vazia de passageiros e coloca a próxima paragem como `null`.

#### `adicionarPassageiro(Passageiro passageiro)`

Adiciona um passageiro à fila da paragem.

Internamente chama:

```java
fila.adicionar(passageiro);
```

Como é uma fila FIFO, o passageiro entra no fim da fila.

#### `embarcarPassageiro()`

Remove e devolve o primeiro passageiro da fila.

Internamente chama:

```java
return fila.remover();
```

Este método representa o embarque de um passageiro quando o autocarro chega à paragem.

Se a fila estiver vazia, devolve `null`.

#### `getNumeroPassageiros()`

Devolve o número de passageiros em espera na paragem.

Internamente chama:

```java
return fila.tamanho();
```

#### `mostrarFila()`

Mostra todos os passageiros que estão na fila da paragem.

#### `getFila()`

Devolve a fila de passageiros da paragem.

Este método é usado para mostrar a fila no estado atual da linha.

#### `getProximaParagem()` e `setProximaParagem()`

Estes métodos são usados para controlar a linked list da linha.

`getProximaParagem()` devolve a próxima paragem.  
`setProximaParagem()` altera a ligação para a próxima paragem.

---

### 4.3 Classe `FilaPassageiros`

A classe `FilaPassageiros` implementa manualmente uma fila FIFO.

Esta classe substitui o uso de:

```java
Queue<Passageiro>
LinkedList<Passageiro>
```

Atributos principais:

```java
private NoPassageiro inicio;
private NoPassageiro fim;
private int tamanho;
```

#### `FilaPassageiros()`

Construtor da classe.

Inicializa a fila vazia:

```java
inicio = null;
fim = null;
tamanho = 0;
```

#### `adicionar(Passageiro passageiro)`

Adiciona um passageiro ao fim da fila.

Funcionamento:

1. Cria um novo nó com o passageiro.
2. Se a fila estiver vazia, o `inicio` e o `fim` passam a apontar para esse novo nó.
3. Se a fila já tiver passageiros, o antigo `fim` passa a apontar para o novo nó.
4. O `fim` é atualizado.
5. O tamanho aumenta.

Exemplo:

```text
Antes:
João -> Maria

Adicionar Pedro:

Depois:
João -> Maria -> Pedro
```

#### `remover()`

Remove o primeiro passageiro da fila.

Funcionamento:

1. Se a fila estiver vazia, devolve `null`.
2. Guarda o passageiro que está no início.
3. O `inicio` passa para o nó seguinte.
4. Se a fila ficar vazia, o `fim` também passa para `null`.
5. O tamanho diminui.
6. Devolve o passageiro removido.

Exemplo:

```text
Antes:
João -> Maria -> Pedro

Remover:

Depois:
Maria -> Pedro
```

O passageiro removido foi `João`.

#### `tamanho()`

Devolve o número de passageiros na fila.

#### `estaVazia()`

Verifica se a fila está vazia.

#### `mostrar()`

Mostra todos os passageiros da fila.

#### `toString()`

Devolve a fila em formato de texto.

Exemplo:

```text
João -> Maria -> Pedro
```

#### Classe interna `NoPassageiro`

Dentro de `FilaPassageiros`, existe uma classe interna chamada `NoPassageiro`.

Ela representa cada nó da fila.

```java
private class NoPassageiro 
{
    Passageiro passageiro;
    NoPassageiro proximo;
}
```

Cada nó guarda:

- um passageiro;
- a ligação para o próximo nó.

---

### 4.4 Classe `ListaPassageiros`

A classe `ListaPassageiros` implementa manualmente uma linked list simples de passageiros.

Esta classe substitui o uso de:

```java
ArrayList<Passageiro>
List<Passageiro>
```

É usada pela classe `Autocarro`.

Atributos principais:

```java
private NoPassageiro inicio;
private int tamanho;
```

#### `ListaPassageiros()`

Construtor da classe.

Inicializa a lista vazia:

```java
inicio = null;
tamanho = 0;
```

#### `adicionar(Passageiro passageiro)`

Adiciona um passageiro ao fim da lista.

Funcionamento:

1. Cria um novo nó.
2. Se a lista estiver vazia, o novo nó passa a ser o início.
3. Caso contrário, percorre a lista até ao último nó.
4. Liga o último nó ao novo nó.
5. Aumenta o tamanho.

#### `procurarPorNome(String nome)`

Procura um passageiro pelo nome.

Funcionamento:

1. Começa no início da lista.
2. Compara o nome do passageiro atual com o nome procurado.
3. Se encontrar, devolve o passageiro.
4. Se não encontrar, passa para o próximo nó.
5. Se chegar ao fim, devolve `null`.

#### `removerPorNome(String nome)`

Remove um passageiro da lista pelo nome.

Funcionamento:

1. Se a lista estiver vazia, devolve `false`.
2. Se o passageiro estiver no início, o início passa para o nó seguinte.
3. Caso contrário, percorre a lista até encontrar o nó anterior ao passageiro a remover.
4. Quando encontra, altera a ligação para saltar o passageiro removido.
5. Diminui o tamanho.
6. Devolve `true`.

Exemplo:

```text
Antes:
João -> Maria -> Pedro
```

Se removermos `Maria`:

```text
Depois:
João -> Pedro
```

#### `tamanho()`

Devolve o número de passageiros na lista.

#### `mostrar()`

Mostra todos os passageiros dentro do autocarro.

#### `toString()`

Devolve a lista em formato de texto.

#### Classe interna `NoPassageiro`

Dentro de `ListaPassageiros`, existe uma classe interna chamada `NoPassageiro`.

Ela representa cada nó da linked list.

```java
private class NoPassageiro {
    Passageiro passageiro;
    NoPassageiro proximo;
}
```

Cada nó guarda:

- um passageiro;
- a ligação para o próximo nó.

---

### 4.5 Classe `Linha`

A classe `Linha` representa o percurso completo do autocarro.

Atributos principais:

```java
private Paragem primeiraParagem;
private int totalParagens;
```

O atributo `primeiraParagem` guarda o primeiro nó da linked list.  
O atributo `totalParagens` guarda o número de paragens existentes na linha.

#### `Linha()`

Construtor da classe.

Inicializa a linha sem paragens:

```java
primeiraParagem = null;
totalParagens = 0;
```

#### `adicionarParagem(Paragem paragem)`

Adiciona uma nova paragem ao fim da linked list.

Funcionamento:

1. Se a linha estiver vazia, a nova paragem passa a ser a primeira.
2. Caso contrário, o método percorre a linked list até encontrar a última paragem.
3. Quando encontra a última paragem, liga essa paragem à nova.
4. Aumenta o total de paragens.

Exemplo:

```text
Antes:
A -> B
```

Adicionar `C`:

```text
Depois:
A -> B -> C
```

#### `removerParagem(String nome)`

Remove uma paragem da linked list através do nome.

Funcionamento:

1. Se a lista estiver vazia, devolve `false`.
2. Se a paragem a remover for a primeira, a primeira paragem passa a ser a seguinte.
3. Caso contrário, percorre a lista até encontrar a paragem anterior à que deve ser removida.
4. Quando encontra, altera a ligação para saltar a paragem removida.
5. Diminui o total de paragens.
6. Devolve `true`.

Exemplo:

```text
Antes:
A -> B -> C
```

Se removermos `B`:

```text
Depois:
A -> C
```

#### `listarPercurso()`

Percorre a linked list desde a primeira paragem até ao fim e imprime o nome de cada paragem.

#### `encontrarParagem(String nome)`

Procura uma paragem pelo nome dentro da linha.

Funcionamento:

1. Começa na primeira paragem.
2. Compara o nome da paragem atual com o nome procurado.
3. Se encontrar, devolve a paragem.
4. Se não encontrar, passa para a próxima.
5. Se chegar ao fim, devolve `null`.

#### `obterParagens()`

Converte a linked list de paragens num array:

```java
Paragem[] paragens
```

Isto é usado para aplicar os algoritmos de ordenação.

Funcionamento:

1. Cria um array com o tamanho `totalParagens`.
2. Percorre a linked list.
3. Guarda cada paragem numa posição do array.
4. Devolve o array.

#### `mostrarEstadoAtual()`

Mostra todas as paragens da linha, o número de passageiros em espera e a fila de passageiros.

Este método percorre a linked list desde a primeira paragem até ao fim.

#### `getInicio()`

Devolve a primeira paragem da linha.

Este método é usado no `Main` para percorrer a linked list.

---

### 4.6 Classe `Autocarro`

A classe `Autocarro` representa o autocarro que transporta passageiros.

Atributo principal:

```java
private ListaPassageiros passageiros;
```

Esta lista guarda os passageiros que estão dentro do autocarro.

#### `Autocarro()`

Construtor da classe.

Inicializa a lista de passageiros vazia:

```java
passageiros = new ListaPassageiros();
```

#### `embarcarPassageiro(Passageiro passageiro)`

Adiciona um passageiro ao autocarro.

Internamente chama:

```java
passageiros.adicionar(passageiro);
```

#### `desembarcarPassageiro(String nome)`

Remove um passageiro do autocarro através do nome.

Internamente chama:

```java
return passageiros.removerPorNome(nome);
```

Se o passageiro existir, devolve `true`.  
Se o passageiro não existir, devolve `false`.

#### `procurarPassageiro(String nome)`

Procura um passageiro dentro do autocarro pelo nome.

#### `mostrarPassageiros()`

Mostra todos os passageiros dentro do autocarro.

#### `getNumeroPassageiros()`

Devolve o número total de passageiros dentro do autocarro.

---

### 4.7 Classe `Ordenacao`

A classe `Ordenacao` contém métodos estáticos para ordenar as paragens.

Foram implementados dois algoritmos de ordenação básicos:

- Bubble Sort;
- Selection Sort.

A ordenação é feita sobre um array auxiliar de paragens:

```java
Paragem[] paragens
```

#### `bubbleSortNome(Paragem[] paragens)`

Ordena as paragens por ordem alfabética do nome.

Funcionamento:

1. Percorre o array várias vezes.
2. Compara duas paragens vizinhas.
3. Se estiverem fora de ordem, troca as posições.
4. Repete até o array ficar ordenado.

A comparação é feita com:

```java
compareToIgnoreCase()
```

Isto permite comparar nomes ignorando maiúsculas e minúsculas.

Exemplo:

```text
Antes:
Santarém, Lisboa, Alverca

Depois:
Alverca, Lisboa, Santarém
```

#### `selectionSortPassageiros(Paragem[] paragens)`

Ordena as paragens pelo número de passageiros em espera.

Funcionamento:

1. Assume que a posição atual tem o menor valor.
2. Percorre o resto do array à procura da paragem com menos passageiros.
3. Quando encontra uma paragem com menos passageiros, guarda o índice.
4. No fim da passagem, troca a posição atual com a posição onde está o menor valor.
5. Repete até o array ficar ordenado.

Exemplo:

```text
Antes:
A: 5 passageiros
B: 2 passageiros
C: 8 passageiros

Depois:
B: 2 passageiros
A: 5 passageiros
C: 8 passageiros
```

#### `trocar(Paragem[] paragens, int a, int b)`

Método auxiliar que troca duas posições do array.

```java
Paragem temp = paragens[a];
paragens[a] = paragens[b];
paragens[b] = temp;
```

Este método substitui o uso de `Collections.swap`.

---

### 4.8 Classe `Main`

A classe `Main` contém o método principal do programa e o menu de interação com o utilizador.

É nesta classe que são criados os objetos principais:

```java
Linha linha = new Linha();
Autocarro autocarro = new Autocarro();
```

O programa usa um ciclo `while` para manter o menu ativo até o utilizador escolher a opção de sair.

#### Opção 1 - Adicionar paragem

Lê o nome da paragem, cria um novo objeto `Paragem` e adiciona-o à linha através de:

```java
linha.adicionarParagem(new Paragem(nomeParagem));
```

#### Opção 2 - Remover paragem

Lê o nome da paragem a remover e chama:

```java
linha.removerParagem(nomeParagemRemover);
```

#### Opção 3 - Listar percurso

Chama:

```java
linha.listarPercurso();
```

Mostra todas as paragens da linha.

#### Opção 4 - Adicionar passageiro a uma paragem

Primeiro procura a paragem pelo nome.

Se encontrar, cria um novo passageiro e adiciona-o à fila dessa paragem.

```java
paragem.adicionarPassageiro(new Passageiro(nomePassageiro));
```

#### Opção 5 - Simular chegada do autocarro

Procura a paragem indicada pelo utilizador.

Se a paragem existir, remove o primeiro passageiro da fila da paragem:

```java
Passageiro embarcado = paragemChegada.embarcarPassageiro();
```

Depois adiciona esse passageiro ao autocarro:

```java
autocarro.embarcarPassageiro(embarcado);
```

Esta opção junta duas estruturas de dados:

- a fila da paragem, de onde sai o passageiro;
- a linked list do autocarro, onde o passageiro entra.

#### Opção 6 - Ordenar paragens por nome

Obtém as paragens da linha através de:

```java
Paragem[] paragensNome = linha.obterParagens();
```

Depois chama:

```java
Ordenacao.bubbleSortNome(paragensNome);
```

#### Opção 7 - Ordenar paragens por número de passageiros

Obtém as paragens da linha através de:

```java
Paragem[] paragensPassageiros = linha.obterParagens();
```

Depois chama:

```java
Ordenacao.selectionSortPassageiros(paragensPassageiros);
```

#### Opção 8 - Mostrar estado atual da linha

Chama:

```java
linha.mostrarEstadoAtual();
```

Mostra cada paragem, o número de passageiros em espera e a fila.

#### Opção 9 - Mostrar passageiros no autocarro

Mostra a lista de passageiros atualmente dentro do autocarro e o total de passageiros.

#### Opção 10 - Desembarcar passageiro do autocarro

Lê o nome do passageiro a desembarcar.

Depois chama:

```java
autocarro.desembarcarPassageiro(nomeDesembarcar);
```

Se o passageiro existir dentro do autocarro, é removido.

#### Opção 0 - Sair

Termina o ciclo principal e encerra o programa.

#### `encontrarParagem(Linha linha, String nome)`

Método auxiliar que procura uma paragem pelo nome.

Funcionamento:

1. Começa na primeira paragem da linha.
2. Compara o nome da paragem atual com o nome escrito pelo utilizador.
3. Se encontrar, devolve essa paragem.
4. Se não encontrar, passa para a próxima paragem.
5. Se chegar ao fim sem encontrar, devolve `null`.

Este método percorre a linked list da linha.

---

### 4.9 Classe `grafo`

A classe `grafo` ainda não está implementada.

Esta classe poderia ser usada futuramente para funcionalidades extra, como calcular caminhos entre paragens.

No entanto, o projeto principal funciona sem esta classe, porque a representação obrigatória da linha já é feita através da linked list.

---

## 5. Como executar o projeto

Para evitar erros como `NoClassDefFoundError`, todos os ficheiros `.java` devem estar na mesma pasta.

Os nomes dos ficheiros devem corresponder ao nome das classes:

```text
Autocarro.java
FilaPassageiros.java
Linha.java
ListaPassageiros.java
Main.java
Ordenacao.java
Paragem.java
Passageiro.java
grafo.java
```

Depois, no terminal, dentro da pasta do projeto, compilar todos os ficheiros:

```bash
javac *.java
```

Executar o programa:

```bash
java Main
```

---

## 6. O que foi alterado nesta versão

Nesta versão, foram feitas alterações para cumprir melhor o objetivo da unidade curricular.

Antes, o projeto usava estruturas prontas do Java, como:

```java
ArrayList
List
Queue
LinkedList
Collections.swap
```

Agora, essas estruturas foram removidas.

Foram adicionadas as seguintes classes próprias:

```java
FilaPassageiros.java
ListaPassageiros.java
```

Também foram feitas alterações em:

```java
Autocarro.java
Paragem.java
Linha.java
Ordenacao.java
Main.java
```

### Principais alterações

- A fila de passageiros deixou de usar `Queue` e `LinkedList`.
- A fila passou a ser implementada manualmente com nós ligados.
- A lista de passageiros do autocarro deixou de usar `ArrayList` e `List`.
- A lista do autocarro passou a ser uma linked list própria.
- A ordenação deixou de usar `Collections.swap`.
- A troca de posições passou a ser feita manualmente no método `trocar`.
- A classe `Linha` passou a controlar também o número total de paragens.
- A função `obterParagens()` passou a devolver um array de `Paragem`.
- O código ficou mais adequado à disciplina de Algoritmos e Estruturas de Dados.

---

## 7. Conclusão

O projeto cumpre os principais requisitos pedidos no enunciado:

- representação da linha através de linked list;
- gestão dos passageiros em espera através de fila FIFO;
- simulação da chegada do autocarro;
- entrada de passageiros no autocarro;
- saída de passageiros do autocarro;
- ordenação das paragens por nome;
- ordenação das paragens por número de passageiros;
- menu funcional na consola.

