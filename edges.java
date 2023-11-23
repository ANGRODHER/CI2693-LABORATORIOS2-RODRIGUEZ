public class edges extends connect {
  // Variables de Arista.
  private Comercio extremoInicial;
  private Comercio extremoFinal;

  // Crear Arista.
  public edges(String id, Comercio extremoInicial, Comercio extremoFinal) {
    super(id);
    this.extremoInicial = extremoInicial;
    this.extremoFinal = extremoFinal;
  }

  // Obtener Extremo1.
  public Comercio getExtremoInicial() {
    return this.extremoInicial;
  }

  // Obtener Extremo2.
  public Comercio getExtremoFinal() {
    return this.extremoFinal;
  }

  // Mostrar la arista.
  @Override
  public String toString() {
    return "Aristas: (" + extremoInicial.getNombre() + " -> " + extremoFinal.getNombre() + ")";
  }
}
