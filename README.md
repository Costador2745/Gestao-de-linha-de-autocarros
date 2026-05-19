# Gestão de Linha de Autocarros

## Funcionamento interno do código

Esta secção explica como o código está organizado internamente, quais são as responsabilidades de cada ficheiro e como as classes se ligam entre si.

O projeto está dividido em várias classes Java, onde cada uma representa uma parte diferente do sistema. A classe `Main` controla o programa e faz a ligação entre as outras classes. As classes `Linha`, `Paragem`, `Passageiro` e `Autocarro` guardam e manipulam os dados principais. A classe `Ordenacao` contém métodos auxiliares para ordenar paragens. A classe `grafo` ainda não tem implementação.

---

## Mapa geral das ligações entre classes

```
Main
├── usa Linha
│   └── usa Paragem
│       └── usa Passageiro
│
├── usa Autocarro
│   └── usa Passageiro
│
├── cria objetos Paragem
├── cria objetos Passageiro
└── pode futuramente usar Ordenacao

Ordenacao
└── usa Paragem

grafo
└── ainda não está implementado
```

---

## Exemplos de fluxo

### Adicionar uma paragem

```
Main
 |
 | cria uma nova Paragem
 v
Linha.adicionarParagem()
 |
 | adiciona a paragem ao fim da lista ligada
 v
Paragem fica ligada à linha
```

### Adicionar um passageiro

```
Main
 |
 | procura a paragem pelo nome
 v
encontrarParagem()
 |
 | cria um novo Passageiro
 v
Paragem.adicionarPassageiro()
 |
 | adiciona o passageiro à fila da paragem
 v
Passageiro fica à espera nessa paragem
```

### Embarcar um passageiro

```
Main
 |
 | encontra a paragem
 v
Paragem.embarcarPassageiro()
 |
 | remove o primeiro passageiro da fila
 v
Autocarro.embarcarPassageiro()
 |
 | adiciona o passageiro à lista do autocarro
 v
Passageiro fica dentro do autocarro
```
