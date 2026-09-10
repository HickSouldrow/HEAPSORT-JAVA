import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FaseInsertionsort {

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

        // Insertion sort decrescente
        for (int i = 1; i < n; i++) {
            int atual = pontuacoes[i];
            int j = i - 1;
            while (j >= 0 && pontuacoes[j] < atual) {
                pontuacoes[j + 1] = pontuacoes[j];
                j--;
            }
            pontuacoes[j + 1] = atual;
        }

        int valorCorte = pontuacoes[Math.min(k, n) - 1];
        int classificados = 0;
        for (int v : pontuacoes) if (v >= valorCorte) classificados++;

        BufferedWriter bw = new BufferedWriter(new FileWriter("src/saida.txt"));
        bw.write(String.valueOf(classificados));
        bw.close();

        System.out.println("Concluído! Número de classificados: " + classificados);
    }
}