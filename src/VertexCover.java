import java.util.*;

public class VertexCover {
    public static List<Integer> executar(Grafo grafo) {
        List<Integer> cobertura = new ArrayList<>();
        boolean[] naCobertura = new boolean[grafo.tamanho()];

        int atual = maiorGrauGlobal(grafo);

        System.out.println("Ponto de partida: " + grafo.nome(atual) + " (grau " + grafo.grau(atual) + ")");
        System.out.println();

        while (grafo.temArestas()) {
            cobertura.add(atual);
            naCobertura[atual] = true;

            System.out.printf("[COBRE] %-20s (grau %d)%n", grafo.nome(atual), grafo.grau(atual));

            List<Integer> vizinhosAtuais = new ArrayList<>(grafo.vizinhos(atual));
            grafo.removerArestasDoVertice(atual);

            if (!grafo.temArestas()) break;

            int proximo = -1;
            int melhorGrau = -1;

            for (int vizinho : vizinhosAtuais) {
                int grauVizinho = grafo.grau(vizinho);
                if (grauVizinho > melhorGrau && !naCobertura[vizinho]) {
                    melhorGrau = grauVizinho;
                    proximo = vizinho;
                }
            }

            if (proximo == -1 || melhorGrau == 0) {
                proximo = maiorGrauGlobal(grafo);
                if (proximo != -1) {
                    System.out.println("[REINICIO] Saltando para: " + grafo.nome(proximo));
                }
            } else {
                System.out.printf("[VIAJA] %-20s -> %-20s (grau %d)%n", grafo.nome(atual), grafo.nome(proximo), melhorGrau);
            }
            atual = proximo;
        }
        return cobertura;
    }

    public static int maiorGrauGlobal(Grafo grafo) {
        int melhor = -1;
        int maiorGrau = -1;
        for (int v = 0; v < grafo.tamanho(); v++) {
            if (grafo.grau(v) > maiorGrau) {
                maiorGrau = grafo.grau(v);
                melhor = v;
            }
        }
        return melhor;
    }
}
