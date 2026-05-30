# Gestão de Linha de Autocarros

## 1. Introdução

Este projeto foi desenvolvido no âmbito da unidade curricular de Algoritmos e Estruturas de Dados. O objetivo é simular um sistema simples de gestão de uma linha de autocarros, permitindo criar paragens, gerir passageiros em espera, simular a chegada do autocarro, embarcar passageiros, mostrar o estado atual da linha e ordenar as paragens.

O sistema foi implementado em Java e funciona através de um menu na consola. O projeto utiliza estruturas de dados importantes da disciplina, como lista ligada, fila e lista dinâmica.

---

## 2. Objetivo do projeto

O programa permite:

- adicionar paragens a uma linha de autocarro;
- remover paragens da linha;
- listar o percurso completo;
- adicionar passageiros a uma determinada paragem;
- gerir passageiros em espera através de uma fila;
- simular a chegada do autocarro a uma paragem;
- embarcar passageiros no autocarro;
- mostrar os passageiros atualmente dentro do autocarro;
- ordenar paragens por nome;
- ordenar paragens por número de passageiros;
- mostrar o estado atual da linha.

---

## 3. Estruturas de dados utilizadas

### 3.1 Lista ligada

A linha de autocarro é representada através de uma lista ligada de paragens.

Cada objeto `Paragem` guarda uma referência para a próxima paragem através do atributo:

```java
private Paragem proximaParagem;
```

Isto significa que cada paragem conhece apenas a paragem seguinte. A classe `Linha` guarda a primeira paragem da lista:

```java
private Paragem primeiraParagem;
```

A partir dessa primeira paragem, é possível percorrer todas as outras usando um ciclo `while`.

Esta estrutura foi usada porque o enunciado pedia que o percurso da linha fosse representado por uma lista ligada.

---

### 3.2 Fila

Em cada paragem existe uma fila de passageiros. Esta fila segue o princípio FIFO, ou seja, o primeiro passageiro a entrar na fila é o primeiro a sair.

Na classe `Paragem`, a fila é representada por:

```java
private Queue<Passageiro> fila;
```

A implementação concreta usada é:

```java
this.fila = new LinkedList<>();
```

Quando um passageiro é adicionado, ele vai para o fim da fila. Quando o autocarro chega e embarca um passageiro, é removido o primeiro passageiro da fila.

---

### 3.3 Lista dinâmica

Na classe `Autocarro`, os passageiros transportados são guardados numa lista dinâmica:

```java
private List<Passageiro> passageiros;
```

A implementação usada é:

```java
this.passageiros = new ArrayList<>();
```

Esta lista permite guardar todos os passageiros que entraram no autocarro e consultar quantos passageiros estão atualmente dentro dele.

---

## 4. Classes do projeto

### 4.1 Classe `Passageiro`

A classe `Passageiro` representa uma pessoa que pode estar numa fila ou dentro do autocarro.

Atributo principal:

```java
private String nome;
```


---

### 4.2 Classe `Paragem`

A classe `Paragem` representa uma paragem da linha de autocarro.

Atributos principais:

```java
private String nome;
private Queue<Passageiro> fila;
private Paragem proximaParagem;
```

O atributo `nome` guarda o nome da paragem.  
O atributo `fila` guarda os passageiros que estão à espera.  
O atributo `proximaParagem` aponta para a próxima paragem da lista ligada.



#### `adicionarPassageiro(Passageiro passageiro)`

Adiciona um passageiro à fila da paragem.

Como é uma fila, o passageiro entra no fim da fila:

```java
fila.add(passageiro);
```

#### `embarcarPassageiro()`

Remove e devolve o primeiro passageiro da fila:

```java
return fila.poll();
```

Este método representa o embarque de um passageiro quando o autocarro chega à paragem. Como usa `poll()`, se a fila estiver vazia, o método devolve `null`.

#### `getNumeroPassageiros()`

Devolve o número de passageiros em espera na paragem:

```java
return fila.size();
```

#### `getFila()`

Devolve a fila de passageiros da paragem. É usado para consultar ou mostrar quem está à espera.

#### `getProximaParagem()` e `setProximaParagem()`

Estes métodos são importantes para a lista ligada.  
`getProximaParagem()` devolve a próxima paragem.  
`setProximaParagem()` altera a ligação para a próxima paragem.


---

### 4.3 Classe `Linha`

A classe `Linha` representa o percurso completo do autocarro.

Atributo principal:

```java
private Paragem primeiraParagem;
```

Este atributo guarda o primeiro nó da lista ligada. A partir dele, o programa consegue percorrer todas as paragens.

#### `Linha()`

Construtor da classe. Inicializa a linha sem paragens:

```java
this.primeiraParagem = null;
```

#### `adicionarParagem(Paragem paragem)`

Adiciona uma nova paragem ao fim da lista ligada.

Funcionamento:

1. Se a linha estiver vazia, a nova paragem passa a ser a primeira.
2. Caso contrário, o método percorre a lista ligada até encontrar a última paragem.
3. Quando encontra a última paragem, liga essa paragem à nova.

Esta função usa lista ligada porque vai passando de paragem em paragem através de `getProximaParagem()`.

#### `removerParagem(String nome)`

Remove uma paragem da lista ligada através do nome.

Funcionamento:

1. Se a lista estiver vazia, devolve `false`.
2. Se a paragem a remover for a primeira, a primeira paragem passa a ser a seguinte.
3. Caso contrário, percorre a lista até encontrar a paragem anterior à que deve ser removida.
4. Quando encontra, altera a ligação para saltar a paragem removida.

Exemplo simplificado:

```text
A -> B -> C
```

Se removermos `B`, a paragem `A` passa a apontar diretamente para `C`:

```text
A -> C
```

#### `listarPercurso()`

Percorre a lista ligada desde a primeira paragem até ao fim e imprime o nome de cada paragem.

#### `getInicio()`

Devolve a primeira paragem da linha. Este método é usado no `Main` para procurar paragens pelo nome.

#### `obterParagens()`

Converte a lista ligada de paragens numa `List<Paragem>`.

Isto é necessário porque os métodos de ordenação trabalham com listas do tipo `List<Paragem>`. Assim, o programa percorre a lista ligada e adiciona cada paragem a uma lista auxiliar.

#### `mostrarEstadoAtual()`

Mostra todas as paragens da linha, o número de passageiros em espera e a fila de passageiros.

Este método também percorre a lista ligada desde a primeira paragem até ao fim.

---

### 4.4 Classe `Autocarro`

A classe `Autocarro` representa o autocarro que transporta passageiros.

Atributo principal:

```java
private List<Passageiro> passageiros;
```

Esta lista guarda os passageiros que estão dentro do autocarro.

#### `Autocarro()`

Construtor da classe. Inicializa a lista de passageiros vazia.

#### `embarcarPassageiro(Passageiro passageiro)`

Adiciona um passageiro à lista de passageiros do autocarro.

#### `desembarcarPassageiro(Passageiro passageiro)`

Remove um passageiro da lista do autocarro.

#### `getPassageiros()`

Devolve a lista de passageiros que estão no autocarro.

#### `getNumeroPassageiros()`

Devolve o número de passageiros dentro do autocarro.

---

### 4.5 Classe `Ordenacao`

A classe `Ordenacao` contém métodos estáticos para ordenar as paragens.

Foram implementados algoritmos de ordenação básicos, usando Bubble Sort.

#### `bubblesortNome(List<Paragem> paragens)`

Ordena as paragens por ordem alfabética do nome.

Funcionamento:

1. Percorre a lista várias vezes.
2. Compara duas paragens vizinhas.
3. Se estiverem fora de ordem, troca as posições.
4. Repete até a lista ficar ordenada.

A comparação é feita com:

```java
compareTo()
```

#### `bubblesortPassageiros(List<Paragem> paragens)`

Ordena as paragens pelo número de passageiros em espera.

Funcionamento:

1. Percorre a lista de paragens.
2. Compara o número de passageiros de duas paragens vizinhas.
3. Se a primeira tiver mais passageiros do que a segunda, troca-as.
4. No final, as paragens ficam ordenadas de forma crescente pelo número de passageiros.

---

### 4.6 Classe `Main`

A classe `Main` contém o método principal do programa e o menu de interação com o utilizador.

É nesta classe que são criados os objetos principais:

```java
Linha linha = new Linha();
Autocarro autocarro = new Autocarro();
```

O programa usa um ciclo `while` para manter o menu ativo até o utilizador escolher a opção de sair.

#### Opção 1 - Adicionar paragem

Lê o nome da paragem, cria um novo objeto `Paragem` e adiciona-o à linha através de `linha.adicionarParagem()`.

#### Opção 2 - Remover paragem

Lê o nome da paragem a remover e chama `linha.removerParagem()`.

#### Opção 3 - Listar percurso

Chama `linha.listarPercurso()` para mostrar todas as paragens da linha.

#### Opção 4 - Adicionar passageiro a uma paragem

Primeiro procura a paragem pelo nome. Se encontrar, cria um novo passageiro e adiciona-o à fila dessa paragem.

#### Opção 5 - Simular chegada do autocarro

Procura a paragem indicada pelo utilizador.  
Se a paragem existir, remove o primeiro passageiro da fila da paragem e adiciona-o ao autocarro.

Esta opção junta duas estruturas de dados:

- a fila da paragem, de onde sai o passageiro;
- a lista do autocarro, onde o passageiro entra.

#### Opção 6 - Ordenar paragens por nome

Obtém as paragens da linha através de `obterParagens()` e depois chama `Ordenacao.bubblesortNome()`.

#### Opção 7 - Ordenar paragens por número de passageiros

Obtém as paragens da linha através de `obterParagens()` e depois chama `Ordenacao.bubblesortPassageiros()`.

#### Opção 8 - Mostrar estado atual da linha

Chama `linha.mostrarEstadoAtual()` para mostrar cada paragem, o número de passageiros em espera e a fila.

#### Opção 9 - Mostrar passageiros no autocarro

Mostra a lista de passageiros atualmente dentro do autocarro e o total de passageiros.

#### Opção 10 - Desembarcar passageiro do autocarro

Procura a pessoa indicada pelo utilizador.
Caso a pessoa exista, vai ser chamada a `desembarcarPassageiro(passageiroParaSair)` e vai remover o passageiro do autocarro.

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

Este método percorre a lista ligada da linha.

---

### 4.7 Classe `grafo`

A classe `grafo` ainda não está implementada.

Esta classe poderia ser usada futuramente para funcionalidades extra, como calcular caminhos entre paragens. No entanto, o projeto principal funciona sem esta classe, porque a representação obrigatória da linha já é feita através da lista ligada.


---

## 5. Conclusão

O projeto cumpre os principais requisitos pedidos: representação da linha através de lista ligada, gestão dos passageiros com fila, simulação de entrada de passageiros no autocarro e ordenação das paragens com algoritmos básicos.

A classe `grafo` ficou preparada para possíveis melhorias futuras, mas não é necessária para o funcionamento obrigatório do sistema.
