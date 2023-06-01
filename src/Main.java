import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String rutaArchivo = "/Users/danielgomezperez/Desktop/JSON/src/employees.json";
        Gson gson = new Gson();
        ArrayList<Persona> trabajadores = null;
        BufferedReader br;
        ArrayPersonas empleados = new ArrayPersonas();
        FileWriter escritor;
        try {
            br = new BufferedReader(new FileReader(rutaArchivo));
            empleados = gson.fromJson(br, ArrayPersonas.class);
            trabajadores = empleados.getTrabajadores();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (trabajadores != null) {
            for (Persona trabajador : trabajadores) {
                System.out.println("Nombre: " + trabajador.getFirstName() + " Apellido: " + trabajador.getLastName());
            }
        }
        Persona nuevaPersona = new Persona("Maria","Jimenez");
        trabajadores.add(nuevaPersona);
        empleados.setTrabajadores(trabajadores);

        try{
            escritor = new FileWriter(rutaArchivo);
            gson.toJson(empleados,escritor);
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
