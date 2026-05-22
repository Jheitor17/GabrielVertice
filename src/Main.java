import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        linha();
        System.out.println("Problema War Room - Cobertura de Vértices (Vertex Cover)");
        System.out.println("Heurística do Caixeiro Viajante");
        linha();
        System.out.println();

        Grafo grafo = lerGrafo(scanner);

        int totalVertices = grafo.tamanho();
        int totalArestas  = contarArestas(grafo);

        System.out.println();
        linha();
        System.out.println("Executando heurística do Caixeiro-Viajante...");
        linha();
        System.out.println();

        long inicio = System.nanoTime();
        List<Integer> cobertura = VertexCover.executar(grafo);
        long fim = System.nanoTime();

        System.out.println();
        imprimirResultado(cobertura, grafo, totalVertices, totalArestas, fim - inicio);

        scanner.close();
    }

    private static Grafo lerGrafo(Scanner scanner) {
        System.out.print("Quantos dispositivos (vértices) estão na rede? ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] nomes = new String[n];
        System.out.println("\nDigite o nome de cada dispositivo:");
        for (int i = 0; i < n; i++) {
            System.out.printf("  Dispositivo [%d]: ", i);
            nomes[i] = scanner.nextLine().trim();
        }

        Grafo grafo = new Grafo(n, nomes);

        System.out.print("\nQuantas conexões (arestas) entre os dispositivos existem? ");
        int m = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nReferencie os dispositivos com os índices:");
        for (int i = 0; i < n; i++) {
            System.out.printf("  [%d] %s%n", i, nomes[i]);
        }
        System.out.println();

        for (int i = 0; i < m; i++) {
            System.out.printf("  Conexão %d | De   (índice): ", i + 1);
            int u = scanner.nextInt();
            System.out.print("            | Para (índice): ");
            int v = scanner.nextInt();
            scanner.nextLine();
            grafo.adicionarAresta(u, v);
        }

        return grafo;
    }

    private static void imprimirResultado(List<Integer> cobertura,
                                          Grafo grafo,
                                          int totalVertices,
                                          int totalArestas,
                                          long tempoNano) {
        linha();
        System.out.println("RESULTADO FINAL");
        linha();
        System.out.println("\nDispositivos na cobertura mínima (aproximada):");
        for (int v : cobertura) {
            System.out.println("  [OK] " + grafo.nome(v));
        }
        System.out.println("\nTamanho da cobertura : " + cobertura.size() + " dispositivo(s)");
        System.out.printf("Tempo de execução    : %.3f ms%n", tempoNano / 1_000_000.0);

        System.out.println();
        System.out.println("ANÁLISE DE COMPLEXIDADE");
        linha();
        System.out.println("Problema    : Vertex Cover (Cobertura de Vértices)");
        System.out.println("Heurística utilizada  : Caixeiro-Viajante adaptada");
        System.out.printf ("  Vértices    : V = %d%n", totalVertices);
        System.out.printf ("  Arestas     : E = %d%n", totalArestas);
        System.out.println("Complexidade: O(V x E)");
        System.out.println("Busca local : O(grau do vertice atual)");
        System.out.println("Reinício    : O(V) apenas quando necessário");
        System.out.println();
        linha();
    }

    private static int contarArestas(Grafo grafo) {
        int total = 0;
        for (int v = 0; v < grafo.tamanho(); v++) {
            total += grafo.grau(v);
        }
        return total / 2;
    }

    private static void linha() {
        System.out.println("--------------------------------------------------");
    }
}
