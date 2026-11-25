package Models;

public class Pokemon {
        // Atributos privados (Encapsulamiento)
        private String id;
        private int numero;
        private String nombre;
        private TipoPokemon tipo;
        private int nivel;
        private String entrenador;

        // Constructor: crea el Pokémon y valida el nivel
        public Pokemon(String id, int numero, String nombre, TipoPokemon tipo, int nivel) {
            this.id = id;
            this.numero = numero;
            this.nombre = nombre;
            this.tipo = tipo;
            setNivel(nivel); // condicion nivel
            //this.entrenador = entrenador;
        }

        // condicion nivel: el nivel oficial debe estar entre 1 y 100
        public void setNivel(int nivel) {
            if (nivel >= 1 && nivel <= 100) {
                this.nivel = nivel;
            } else {
                this.nivel = 1; // Valor por defecto si ingresan un nivel fuera de rango
            }
        }

        // Getters y Setters
        public String getId() { return id; }
        public int getNumero() { return numero; }
        public String getNombre() { return nombre; }
        public TipoPokemon getTipo() { return tipo; }
        public int getNivel() { return nivel; }
        public String getEntrenador() { return entrenador; }
        public void setEntrenador(String entrenador) { this.entrenador = entrenador; }

        // Separacion por comas para el csv
        public String pokemonToCSV()
        {
            return "id: " + id + ", num: " + numero + ", nombre: " + nombre +
                    ", tipo: " + tipo + ", nivel: " + nivel + ", entrenador: " + entrenador;
        }

        @Override //orden bonito
        public String toString() {
            return "#" + numero + " " + nombre + " (" + tipo + " - Nv." + nivel + ")";
        }
    }

