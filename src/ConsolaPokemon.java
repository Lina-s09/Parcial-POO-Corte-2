import Models.Entrenador;
import Models.Pokemon;
import Models.TipoPokemon;
import Operaciones.Operaciones;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsolaPokemon {
    private JPanel panel;
    private JTabbedPane tabbedPane1;

    // Campos Entrenador
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JFormattedTextField formattedTextField3;
    private JFormattedTextField formattedTextField4;
    private JButton guardarEntrenadorButton;

    // Campos Pokémon
    private JTextField txtPokeId;
    private JTextField txtPokeNum;
    private JTextField txtPokeNombre;
    private JComboBox<TipoPokemon> cmbPokeTipo;
    private JTextField txtPokeNivel;
    private JTextField txtPokeEntrenador;
    private JButton guardarPokemonButton;

    // Botones generales y consola de salida
    private JButton cargarPokemonesAEntrenadoresButton;
    private JButton actualizarLosDatosButton;
    private JButton limpiarCSVButton;
    private JTextArea areaResultados;

    public ConsolaPokemon() {
        cmbPokeTipo.setModel(new DefaultComboBoxModel<>(TipoPokemon.values()));

        // 1. Guardar Entrenador
        guardarEntrenadorButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(formattedTextField1.getText().trim());
                String nombre = formattedTextField2.getText().trim();
                LocalDate fecha = LocalDate.parse(formattedTextField3.getText().trim());
                String textoMedallas = formattedTextField4.getText().trim();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "El nombre del entrenador es obligatorio.");
                    return;
                }

                String[] medallas = textoMedallas.isEmpty() ? new String[0] : textoMedallas.split(",");
                for (int i = 0; i < medallas.length; i++) {
                    medallas[i] = medallas[i].trim();
                }

                Entrenador nuevo = new Entrenador(id, nombre, fecha, medallas);
                Operaciones.guardarEntrenador(nuevo);

                areaResultados.setText("Entrenador guardado: " + nuevo.getNombre() + " (Edad: " + nuevo.getEdad() + " años)");

                formattedTextField1.setText("");
                formattedTextField2.setText("");
                formattedTextField3.setText("");
                formattedTextField4.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel,
                        "Verifica que el ID sea número y la fecha tenga formato AAAA-MM-DD.");
            }
        });

        // 2. Guardar Pokémon
        guardarPokemonButton.addActionListener(e -> {
            try {
                String id = txtPokeId.getText().trim();
                int numero = Integer.parseInt(txtPokeNum.getText().trim());
                String nombre = txtPokeNombre.getText().trim();
                TipoPokemon tipo = (TipoPokemon) cmbPokeTipo.getSelectedItem();
                int nivel = Integer.parseInt(txtPokeNivel.getText().trim());
                String entrenador = txtPokeEntrenador.getText().trim();

                if (id.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "El ID y el Nombre del Pokémon son obligatorios.");
                    return;
                }

                Pokemon pk = new Pokemon(id, numero, nombre, tipo, nivel);
                if (!entrenador.isEmpty()) {
                    pk.setEntrenador(entrenador);
                }

                Operaciones.guardarpokemones(pk);
                areaResultados.setText("Pokémon guardado: " + pk + " | Entrenador: " + (entrenador.isEmpty() ? "Salvaje" : entrenador));

                txtPokeId.setText("");
                txtPokeNum.setText("");
                txtPokeNombre.setText("");
                txtPokeNivel.setText("");
                txtPokeEntrenador.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Número de Pokédex y Nivel deben ser números enteros.");
            }
        });

        // 3. Cargar Datos Oficiales + Custom
        cargarPokemonesAEntrenadoresButton.addActionListener(e -> {
            Operaciones.cargarDatosIniciales();
            areaResultados.setText("Datos oficiales (Ash) y customizados (Fabian y Lina67 con Soachamon) guardados en CSV.");
        });

        // 4. Actualizar y mostrar toda la información desde los CSV
        actualizarLosDatosButton.addActionListener(e -> mostrarTodo());

        // 5. Limpiar archivos CSV
        limpiarCSVButton.addActionListener(e -> {
            int confirmar = JOptionPane.showConfirmDialog(
                    panel,
                    "¿Seguro que deseas borrar todos los registros de Pokemones.csv y Entrenadores.csv?",
                    "Confirmar limpieza",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmar == JOptionPane.YES_OPTION) {
                Operaciones.limpiarCSV();
                areaResultados.setText("Archivos Pokemones.csv y Entrenadores.csv limpiados correctamente.");
            }
        });
    }

    private void mostrarTodo() {
        ArrayList<Entrenador> entrenadores = Operaciones.leerEntrenadores();
        ArrayList<Pokemon> pokemones = Operaciones.leerPokemones();

        for (Entrenador ent : entrenadores) {
            for (Pokemon p : pokemones) {
                if (p.getEntrenador() != null && p.getEntrenador().equalsIgnoreCase(ent.getNombre())) {
                    ent.agregarPokemon(p);
                }
            }
        }

        StringBuilder sb = new StringBuilder("=== ENTRENADORES (Entrenadores.csv) ===\n");
        if (entrenadores.isEmpty()) {
            sb.append("Sin entrenadores registrados.\n");
        } else {
            for (Entrenador ent : entrenadores) {
                sb.append(ent).append(" | Edad: ").append(ent.getEdad()).append(" años")
                        .append(" | Medallas: ").append(Arrays.toString(ent.getMedallas())).append("\n");
            }
        }

        sb.append("\n=== POKÉMON (Pokemones.csv) ===\n");
        if (pokemones.isEmpty()) {
            sb.append("Sin Pokémon registrados.\n");
        } else {
            for (Pokemon p : pokemones) {
                String dueno = (p.getEntrenador() == null || p.getEntrenador().isEmpty()) ? "Salvaje" : p.getEntrenador();
                sb.append(p).append(" | ID: ").append(p.getId()).append(" | Entrenador: ").append(dueno).append("\n");
            }
        }

        areaResultados.setText(sb.toString());
    }

    public JPanel getPanel() {
        return panel;
    }
}