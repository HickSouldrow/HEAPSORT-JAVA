# Classificados

Programa Java que lê uma lista de participantes com suas pontuações e calcula quantos deles se classificam, considerando **empates na última posição de corte**.

## Como funciona

O programa lê um arquivo `entrada.txt` e escreve o resultado em `saida.txt`.

### Formato do `entrada.txt`

Um número por linha, na seguinte ordem:

| Linha | Valor | Descrição |
|-------|-------|-----------|
| 1ª | `n` | Número de participantes |
| 2ª | `k` | Número disponível de classificações (pode aumentar em caso de empate) |
| 3ª em diante | `n` valores | Pontuação de cada participante |

**Exemplo:**

```
10
3
5
4
4
4
3
2
1
1
1
1
```

- `n = 10` → 10 participantes
- `k = 3` → seriam classificados os 3 primeiros colocados
- As 10 linhas seguintes são as pontuações de cada participante

### Formato do `saida.txt`

Um único número: a quantidade de participantes classificados.

**Exemplo de saída para a entrada acima:**

```
4
```

> Mesmo com `k = 3`, o resultado deu **4** porque a 3ª colocação (nota `4`) está empatada entre três participantes. Todos os empatados no corte entram na classificação.
