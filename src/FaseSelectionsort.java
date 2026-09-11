import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FaseSelectionsort {

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

        // Selection sort decrescente
        for (int i = 0; i < n - 1; i++) {
            int maior = i;
            for (int j = i + 1; j < n; j++) {
                if (pontuacoes[j] > pontuacoes[maior]) maior = j;
            }
            int temp = pontuacoes[i];
            pontuacoes[i] = pontuacoes[maior];
            pontuacoes[maior] = temp;
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