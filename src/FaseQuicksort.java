import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FaseQuicksort {

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

        // Quicksort decrescente
        quicksort(pontuacoes, 0, n - 1);

        int valorCorte = pontuacoes[Math.min(k, n) - 1];
        int classificados = 0;
        for (int v : pontuacoes) if (v >= valorCorte) classificados++;

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/saida.txt"));
        bw.write(String.valueOf(classificados));
        bw.close();

        System.out.println("Concluído! Número de classificados: " + classificados);
    }

    private static void quicksort(int[] v, int inicio, int fim) {
        if (inicio < fim) {
            int p = particionar(v, inicio, fim);
            quicksort(v, inicio, p - 1);
            quicksort(v, p + 1, fim);
        }
    }

    private static int particionar(int[] v, int inicio, int fim) {
        int pivo = v[fim];
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (v[j] > pivo) { // > para ordem decrescente
                i++;
                int temp = v[i];
                v[i] = v[j];
                v[j] = temp;
            }
        }
        int temp = v[i + 1];
        v[i + 1] = v[fim];
        v[fim] = temp;
        return i + 1;
    }
}