import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FaseHeapsort {

    public static void main(String[] args) {
        String arquivoEntrada = "src/entrada.txt";
        String arquivoSaida   = "src/saida.txt";


        try {
            int[] dados = lerEntrada(arquivoEntrada);

            int n = dados[0];
            int k = dados[1];

            // vetor apenas com as pontuações
            int[] pontuacoes = new int[n];
            for (int i = 0; i < n; i++) {
                pontuacoes[i] = dados[2 + i];
            }

            int classificados = calcularClassificados(pontuacoes, k);

            escreverSaida(arquivoSaida, classificados);

            System.out.println("Concluído! Número de classificados: " + classificados);
            System.out.println("Resultado salvo em: " + arquivoSaida);

        } catch (IOException e) {
            System.out.println("Erro ao ler/escrever arquivo: " + e.getMessage());
        }
    }


    private static int[] lerEntrada(String caminho) throws IOException {
        List<Integer> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    numeros.add(Integer.parseInt(linha));
                }
            }
        }
        int[] resultado = new int[numeros.size()];
        for (int i = 0; i < numeros.size(); i++) {
            resultado[i] = numeros.get(i);
        }
        return resultado;
    }


    private static int calcularClassificados(int[] pontuacoes, int k) {
        if (pontuacoes.length == 0 || k <= 0) {
            return 0;
        }

        int[] ordenado = pontuacoes.clone();
        heapsortDecrescente(ordenado);

        int indiceCorte = Math.min(k, ordenado.length) - 1;
        int valorCorte = ordenado[indiceCorte];

        int total = 0;
        for (int v : ordenado) {
            if (v >= valorCorte) {
                total++;
            }
        }
        return total;
    }

    // ---------- Heapsort (ordem decrescente) ----------

    private static void heapsortDecrescente(int[] vetor) {
        int n = vetor.length;

        // constrói um heap MÍNIMO (para obter ordem decrescente ao final)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyMin(vetor, n, i);
        }

        // extrai elementos do heap um a um
        for (int i = n - 1; i > 0; i--) {
            trocar(vetor, 0, i);
            heapifyMin(vetor, i, 0);
        }
    }

    private static void heapifyMin(int[] vetor, int tamanho, int raiz) {
        int menor = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;

        if (esquerda < tamanho && vetor[esquerda] < vetor[menor]) {
            menor = esquerda;
        }
        if (direita < tamanho && vetor[direita] < vetor[menor]) {
            menor = direita;
        }
        if (menor != raiz) {
            trocar(vetor, raiz, menor);
            heapifyMin(vetor, tamanho, menor);
        }
    }

    private static void trocar(int[] vetor, int i, int j) {
        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    private static void escreverSaida(String caminho, int valor) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caminho))) {
            bw.write(String.valueOf(valor));
            bw.newLine();
        }
    }
}