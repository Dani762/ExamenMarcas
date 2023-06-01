import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArrayPersonas {
    @SerializedName("employees")
    private ArrayList<Persona> trabajadores;

    public ArrayList<Persona> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(ArrayList<Persona> trabajadores) {
        this.trabajadores = trabajadores;
    }
}
