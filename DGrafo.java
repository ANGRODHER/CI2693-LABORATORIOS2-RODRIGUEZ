import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.nio.charset.StandardCharsets;

public class DGrafo implements Graph<Comercio> {
    // Declaramos las variables, arreglos y tablas que vayamos a utilizar
    private HashMap<String, Comercio> vertices;
    private ArrayList<edges> connect;
    public static final int inf = Integer.MAX_VALUE;

    public DGrafo() {
        // Constructor de la clase.
        vertices = new HashMap<>();
        connect = new ArrayList<>();
    }

    // Método para agregar un vértice al grafo
    public boolean add(Comercio vertex) {

        if (!vertices.containsKey(vertex.getNombre())) {
            // Agregar el vértice al hashtable
            vertices.put(vertex.getNombre(), vertex);
            // Vértice agregado exitosamente
            return true;
        }
        // El vértice ya existe en el grafo
        return false;
    }

    // Método para verificar si un vértice existe en el grafo dado su identificador
    public boolean contains(Comercio vertex) {
        if (vertices.containsKey(vertex.getNombre())) {
            // El vértice existe en el grafo
            return true;
        }
        // El vértice no existe en el grafo
        return false;
    }

    // Método para verificar si existe un lado entre dos vértices
    public boolean containsconnect(Comercio from, Comercio to) {
        // Iterar sobre todos los lados
        for (edges a : connect) {
            // Verificar si los vértices extremos coinciden en cualquier dirección
            if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                // Existe un lado entre los vértices
                return true;
            }
        }
        // No existe un lado entre los vértices
        return false;
    }

    public boolean connect(Comercio from, Comercio to) {
        // Esta función establece la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (!containsconnect(from, to)) {
            edges arista = new edges("" + from.getNombre() + to.getNombre() + "", from, to);
            // System.out.println(arista);
            connect.add(arista);
            return true;
        }
        return false;
    }

    public boolean disconnect(Comercio from, Comercio to) {
        // Esta función elimina la relación entre dos vértices si existe una conexión
        // entre ellos.
        if (contains(from) && contains(to) && containsconnect(from, to)) {
            for (edges a : connect) {
                if (a.getExtremoInicial().equals(from) && a.getExtremoFinal().equals(to)) {
                    connect.remove(a);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Comercio> getInwardEdges(Comercio to) {
        // Esta función devuelve una lista de vértices predecesores que tienen una
        // conexión con el vértice dado.
        List<Comercio> inwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoFinal().getNombre().equals(to.getNombre())) {
                inwardEdges.add(a.getExtremoInicial());
            }
        }
        return inwardEdges;
    }

    public List<Comercio> getOutwardEdges(Comercio from) {
        // Esta función devuelve una lista de vértices sucesores que tienen una conexión
        // con el vértice dado.
        List<Comercio> outwardEdges = new ArrayList<>();
        for (edges a : connect) {
            if (a.getExtremoInicial().getNombre().equals(from.getNombre())) {
                outwardEdges.add(a.getExtremoFinal());
            }
        }
        return outwardEdges;
    }

    public List<Comercio> getVerticesConnectedTo(Comercio vertex) {
        // Esta función devuelve una lista de vértices que tienen una conexión con el
        // vértice dado.
        List<Comercio> verticesConnectedTo = new ArrayList<>();
        for (edges a : connect) {

            if (a.getExtremoInicial().equals(vertex)) {
                verticesConnectedTo.add(a.getExtremoFinal());

            } else if (a.getExtremoFinal().equals(vertex)) {
                verticesConnectedTo.add(a.getExtremoInicial());
            }

        }
        return verticesConnectedTo;
    }

    public List<Comercio> getAllVertices() {
        // Esta función devuelve una lista de todos los vértices del grafo.
        List<Comercio> allVertices = new ArrayList<>();
        for (String key : vertices.keySet()) {
            allVertices.add(vertices.get(key));
        }
        return allVertices;
    }

    public boolean remove(Comercio vertex) {
        // Esta función elimina un vértice del grafo.
        if (contains(vertex)) {
            vertices.remove(vertex.getNombre());
            return true;
        }
        return false;
    }

    public int size() {
        // Esta función devuelve el número de vértices en el grafo.
        return vertices.size();
    }

    @Override
    public String toString() {
        // Este método devuelve una representación en forma de cadena del grafo.
        StringBuilder sb = new StringBuilder();
        // Iterar sobre todos los vertices en el grafo
        for (Comercio a : vertices.values()) {
            sb.append("Nombre: ").append(a.getNombre()).append("\n");
        }
        // Iterar sobre todas las aristas en el grafo
        for (edges l : connect) {
            sb.append(l).append("\n");
        }
        // Devolver la representación en forma de cadena del grafo
        return sb.toString();
    }

    public boolean cargarGrafo(String archivo) {
        // Esta función cargará los datos de un .txt
        try (BufferedReader lista = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lista.readLine()) != null) {

                String[] datos = linea.split(", ");
                String comercio1 = datos[0];
                String comercio2 = datos[1];
                Comercio a = new Comercio(comercio1);
                Comercio b = new Comercio(comercio2);
                add(a);
                add(b);
                connect(a, b);
            }

            return true;
        } catch (IOException | NumberFormatException e) {
            return false;
        }
    }

    public void BFS(Comercio inicio) {
        Queue<Comercio> cola = new LinkedList<>();
        HashMap<Comercio, Integer> visitados = new HashMap<>();

        cola.add(inicio);
        visitados.put(inicio, 1);
        externo: while (!cola.isEmpty()) {
            Comercio actual = cola.poll();
            actual.setVisitar(true);
            System.out.println("Visitando comercio: " + actual.getNombre());
            int valor = 0;

            List<Comercio> adyacentes = getOutwardEdges(actual);
            System.out.println("Adyacente a: " + actual);
            System.out.println("impreime adyacentes: " + adyacentes + "\n");
            if (adyacentes != null) {
                for (Comercio adyacente : adyacentes) {
                    if (visitados.get(inicio) < 2) {
                        if ((!visitados.containsKey(adyacente)) && (adyacente.getVisitar() == false)) {
                            cola.add(adyacente);
                            visitados.put(adyacente, 1);
                            valor++;
                        } else if (visitados.containsKey(adyacente) || adyacente.getVisitar() == true) {
                            int suma1 = visitados.get(adyacente);
                            visitados.put(adyacente, suma1 + 1);
                        }
                    } else if (visitados.get(inicio) == 2) {
                        System.out.println("Existe una localidad en: " + inicio);
                        continue;

                    }
                }
            }

        }
    }

    public void repartidores() {
        int[] sumatoria;
        int j = 0;
        for (Comercio vert : vertices.values()) {
            BFS(vert);

            j++;
        }
        /*
         * for (int itera = 0; itera < sumatoria.length; itera++) {
         * if ((0 < sumatoria[itera]) && (sumatoria[itera] <= 2)) {
         * 
         * } else if ((sumatoria[itera] >= 3) && (sumatoria[itera] <= 5)) {
         * 
         * } else if (sumatoria[itera] >= 6) {
         * 
         * }
         * }
         */

        /* Es mucho más sencillo implementarlo con matriz de adyacencia */
    }
    // end
}