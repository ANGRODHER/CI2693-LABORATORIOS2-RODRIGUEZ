public class Comercio {
      // variables de la clase.
      private String Nombre;
      private Boolean Visitar;

      // Constructor de la clase.
      public Comercio(String id) {
            this.Nombre = id;
            this.Visitar = false;

      }

      // obtener id del vertice.
      public String getNombre() {
            return this.Nombre;
      }

      public Boolean getVisitar() {
            return this.Visitar;
      }

      public boolean setVisitar(boolean p) {
            this.Visitar = p;
            return this.Visitar;
      }

      // Devuelve una representación en forma de cadena del vértice.
      public String toString() {
            return " " + getNombre();
      }
}