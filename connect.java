
public abstract class connect {
  // Clase abstracta

  // Propiedades de la clase
  private String id;

  public connect(String id) {
    // Constructor de la clase abstracta
    this.id = id;

  }

  public String getId() {
    // obtener el id
    return id;
  }

  public abstract String toString();
}