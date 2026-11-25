package Models;

//Librerías
import java.time.LocalDate;
import java.time.Period;

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
}
