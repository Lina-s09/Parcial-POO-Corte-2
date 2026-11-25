package Operaciones;
import Models.Entrenador;
import Models.Pokemon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Operaciones {

    //Establecer la operacion que guardará los pokemones en el csv
    public static void guardarpokemones (Pokemon pk)
    {
        try (FileWriter escribir = new FileWriter("Pokemones.csv", true))
        {
            escribir.write(pk.pokemonToCSV() + "\n");
        }
        catch (IOException e)
        {
            System.out.println("Algo salio mal al guardar el pokemon" + e.getMessage());
        }
    }
    public static void guardarEntrenador(Entrenador entrenador) {
        try (FileWriter escribir = new FileWriter("Entrenadores.csv", true)) {
            escribir.write(entrenador.EntrenadoresToCSV() + "\n");

        } catch (IOException e) {
            System.out.println("Error al guardar Entrenador: " + e.getMessage());
        }
    }
}
