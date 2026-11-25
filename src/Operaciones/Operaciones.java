package Operaciones;

import Models.Entrenador;
import Models.Pokemon;
import Models.TipoPokemon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Operaciones {

    // Guarda un Pokémon en Pokemones.csv
    public static void guardarpokemones(Pokemon pk) {
        try (FileWriter escribir = new FileWriter("Pokemones.csv", true)) {
            escribir.write(pk.pokemonToCSV() + "\n");
        } catch (IOException e) {
            System.out.println("Algo salio mal al guardar el pokemon: " + e.getMessage());
        }
    }

    // Guarda un Entrenador en Entrenadores.csv
    public static void guardarEntrenador(Entrenador entrenador) {
        try (FileWriter escribir = new FileWriter("Entrenadores.csv", true)) {
            escribir.write(entrenador.EntrenadoresToCSV() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar Entrenador: " + e.getMessage());
        }
    }

    // Lee todos los Pokémon desde Pokemones.csv y los reconstruye como objetos
    public static ArrayList<Pokemon> leerPokemones() {
        ArrayList<Pokemon> pokemones = new ArrayList<>();
        File archivo = new File("Pokemones.csv");
        if (!archivo.exists()) return pokemones;

        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            String pokemonReader;
            while ((pokemonReader = buffer.readLine()) != null) {
                if (pokemonReader.trim().isEmpty()) continue;

                // El -1 evita error si el Pokémon aún no tiene entrenador asignado
                String[] data_readed = pokemonReader.split(",", -1);

                String id = data_readed[0].trim();
                int numero = Integer.parseInt(data_readed[1].trim());
                String nombre = data_readed[2].trim();
                TipoPokemon tipo = TipoPokemon.valueOf(data_readed[3].trim());
                int nivel = Integer.parseInt(data_readed[4].trim());
                String entrenador = data_readed[5].trim();

                Pokemon pokemonLeido = new Pokemon(id, numero, nombre, tipo, nivel);
                if (!entrenador.isEmpty()) {
                    pokemonLeido.setEntrenador(entrenador);
                }

                pokemones.add(pokemonLeido);
            }
            System.out.println("Todos los Pokemon fueron leídos correctamente");
        } catch (IOException e) {
            System.out.println("Algo salio mal: " + e.getMessage());
        }

        return pokemones;
    }

    // Lee todos los Entrenadores desde Entrenadores.csv y los reconstruye como objetos
    public static ArrayList<Entrenador> leerEntrenadores() {
        ArrayList<Entrenador> entrenadores = new ArrayList<>();
        File archivo = new File("Entrenadores.csv");
        if (!archivo.exists()) return entrenadores;

        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            String entrenadorReader;
            while ((entrenadorReader = buffer.readLine()) != null) {
                if (entrenadorReader.trim().isEmpty()) continue;

                // El -1 evita error si el entrenador no tiene medallas registradas
                String[] data_readed = entrenadorReader.split(",", -1);

                int id = Integer.parseInt(data_readed[0].trim());
                String nombre = data_readed[1].trim();
                LocalDate fechaNacimiento = LocalDate.parse(data_readed[2].trim());
                String[] medallas = data_readed[3].trim().isEmpty()
                        ? new String[0]
                        : data_readed[3].split(";");

                Entrenador entrenador = new Entrenador(id, nombre, fechaNacimiento, medallas);
                entrenadores.add(entrenador);
            }
            System.out.println("Todos los entrenadores fueron leídos correctamente");
        } catch (IOException e) {
            System.out.println("Algo salió mal: " + e.getMessage());
        }

        return entrenadores;
    }
}

