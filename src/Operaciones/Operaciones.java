package Operaciones;
import Models.Entrenador;
import Models.Pokemon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Models.TipoPokemon;
import java.time.LocalDate;

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
    //Operación de guardar entrenador en CSV
    public static void guardarEntrenador(Entrenador entrenador)
    {
        try (FileWriter escribir = new FileWriter("Entrenadores.csv", true))
        {
            escribir.write(entrenador.EntrenadoresToCSV() + "\n");

        } catch (IOException e) {
            System.out.println("Error al guardar Entrenador: " + e.getMessage());
        }
    }

    //Para leer los pokemones CSV
    public static ArrayList<Pokemon> leerPokemones() {
        ArrayList<Pokemon> pokemones = new ArrayList<>();

        try {
            FileReader leer = new FileReader("Pokemones.csv");
            BufferedReader buffer = new BufferedReader(leer);

            String pokemonReader;

            while ((pokemonReader = buffer.readLine()) != null) {

                String[] data_readed = pokemonReader.split(",");

                String id = data_readed[0].trim();
                int numero = Integer.parseInt(data_readed[1].trim());
                String nombre = data_readed[2].trim();
                TipoPokemon tipo = TipoPokemon.valueOf(data_readed[3].trim()); //el value.of es para convertir el texto en un tipo enum, como lo que hace name() pero al revés
                int nivel = Integer.parseInt(data_readed[4].trim());
                String entrenador = data_readed[5].trim();

                Pokemon pokemonLeido = new Pokemon(id, numero, nombre, tipo, nivel);

                pokemonLeido.setEntrenador(entrenador);

                pokemones.add(pokemonLeido);
            }

            buffer.close();

            System.out.println("Todos los Pokemon fueron leídos correctamente");

        } catch (IOException e) {
            System.out.println("Algo salio mal: " + e.getMessage());
        }

        return pokemones;
    }

    public static ArrayList<Entrenador> leerEntrenadores() {

        ArrayList<Entrenador> entrenadores = new ArrayList<>();

        try {
            FileReader leer = new FileReader("Entrenadores.csv");
            BufferedReader buffer = new BufferedReader(leer);

            String entrenadorReader;

            while ((entrenadorReader = buffer.readLine()) != null) {

                String[] data_readed = entrenadorReader.split(",");

                int id = Integer.parseInt(data_readed[0].trim());
                String nombre = data_readed[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(data_readed[2].trim());
                String[] medallas = data_readed[3].split(";");

                Entrenador entrenador = new Entrenador(
                        id, nombre, fechaNacimiento, medallas
                );

                entrenadores.add(entrenador);
            }

            buffer.close();

            System.out.println("Todos los entrenadores fueron leídos correctamente");

        } catch (IOException e) {
            System.out.println("Algo salió mal: " + e.getMessage());
        }

        return entrenadores;
    }

}

