package Models;

//Librerías
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;

public class Entrenador {
    //Atributos que contiene entrenador
    private int id;
    private String nombre;
    private LocalDate fechaNacimiento;

    //Indicar la cantidad de pokemones que le pertenece a cada entrenador
    private Pokemon [] pokemones = new Pokemon[6];

    //Indicar los trofeos o medallas que ha ganado también en un arreglo
    private String [] medallas;

    private int cantidadActual = 0; //Cantidad prestablecida para indicar la cantidad de pokemones con la que incia el entrenador

    public void setFechaNacimiento(LocalDate nacimiento) {
        this.fechaNacimiento = nacimiento;
    }

    public int getEdad() //calcular la edad del jugador a partir de la fecha de nacimiento y FABIAN NO ME VA A DECIR NI MIERDA POR MI MARAVILLOSA DECISIÓN SOLO LE METÍ UNA EDAD PRO Y AHÍ ESTÁ MÁS QUE BIEN PERROTA
    {
        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }

    //Crear el constructor para la parte del entrenador y la información que reciba
    public Entrenador (int id, String nombre, LocalDate fechaNacimiento, String[] medallas)
    {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.medallas = medallas;
    }

    public boolean agregarPokemon(Pokemon pk) {
        if (cantidadActual < 6) {
            pokemones[cantidadActual] = pk; // Guarda el Pokemon en la posición libre
            pk.setEntrenador(this.nombre);  // Asigna este entrenador al Pokémon
            cantidadActual++;              // Aumenta el contador de ocupados
            return true;
        }
        return false; // Retorna false si el entrenador ya tiene 6 Pokémon
    }

    // Establcer los getters para los atributos privados
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Pokemon[] getPokemones() { return pokemones; }
    public String[] getMedallas() { return medallas; }
    public int getCantidadActual() { return cantidadActual; }

    // Convierte los datos del entrenador sus Pokémon y medallas a texto CSV donde se almacenan
    // Convierte los datos del entrenador separados por coma (y medallas por ';') para que leerEntrenadores() los entienda
    public String EntrenadoresToCSV() {
        String medallasTexto = String.join(";", medallas); // Une el arreglo con ';'
        return id + "," + nombre + "," + fechaNacimiento + "," + medallasTexto;
    }
    // Remplaza para mostrar la salida o impresion de una forma mas ordenada y clara
    @Override
    public String toString() {
        return nombre + " (ID: " + id + " | Pokémon: " + cantidadActual + "/6)";
    }
}

