import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FaseMergesort {

    public static void main(String[] args) throws IOException {
        List<Integer> numeros = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/entrada.txt"));
        String linha;
        while ((linha = br.readLine()) != null) {
            if (!linha.trim().isEmpty()) numeros.add(Integer.parseInt(linha.trim()));
        }
        br.close();

        int n = numeros.get(0);
        int k = numeros.get(1);
        int[] pontuacoes = new int[n];
        for (int i = 0; i < n; i++) pontuacoes[i] = numeros.get(2 + i);

        // Mergesort decrescente
        mergesort(pontuacoes, 0, n - 1);

        int valorCorte = pontuacoes[Math.min(k, n) - 1];
        int classificados = 0;
        for (int v : pontuacoes) if (v >= valorCorte) classificados++;

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/saida.txt"));
        bw.write(String.valueOf(classificados));
        bw.close();

        System.out.println("Concluído! Número de classificados: " + classificados);
    }

    private static void mergesort(int[] v, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergesort(v, inicio, meio);
            mergesort(v, meio + 1, fim);
            merge(v, inicio, meio, fim);
        }
    }

    private static void merge(int[] v, int inicio, int meio, int fim) {
        int[] esquerda = new int[meio - inicio + 1];
        int[] direita = new int[fim - meio];
        System.arraycopy(v, inicio, esquerda, 0, esquerda.length);
        System.arraycopy(v, meio + 1, direita, 0, direita.length);

        int i = 0, j = 0, idx = inicio;
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] >= direita[j]) { // >= para ordem decrescente
                v[idx++] = esquerda[i++];
            } else {
                v[idx++] = direita[j++];
            }
        }
        while (i < esquerda.length) v[idx++] = esquerda[i++];
        while (j < direita.length) v[idx++] = direita[j++];
    }
}