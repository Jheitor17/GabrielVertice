import java.util.*;

public class Grafo {

    private final int numVertices;
    private final List<Set<Integer>> adjacencia;
    private final String[] nomes;

    public Grafo(int n, String[] nomes) {
        this.numVertices = n;
        this.nomes = nomes;
        this.adjacencia = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencia.add(new HashSet<>());
        }
    }

    public void adicionarAresta(int u, int v) {
        adjacencia.get(u).add(v);
        adjacencia.get(v).add(u);
    }

    public int grau(int v) {
        return adjacencia.get(v).size();
    }

    public Set<Integer> vizinhos(int v) {
        return adjacencia.get(v);
    }

    public void removerArestasDoVertice(int v) {
        for (int vizinho : adjacencia.get(v)) {
            adjacencia.get(vizinho).remove(v);
        }
        adjacencia.get(v).clear();
    }

    public boolean temArestas() {
        for (int i = 0; i < numVertices; i++) {
            if (!adjacencia.get(i).isEmpty()) return true;
        }
        return false;
    }

    public String nome(int v) { return nomes[v]; }
    public int tamanho()      { return numVertices; }
}