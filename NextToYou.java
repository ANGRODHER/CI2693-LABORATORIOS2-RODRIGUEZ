public class NextToYou {
    public static void main(String[] args) {
        DGrafo grafo = new DGrafo();
        String archivo = "Caracas.txt";
        grafo.cargarGrafo(archivo);
        
        grafo.repartidores();
        

    }
}
