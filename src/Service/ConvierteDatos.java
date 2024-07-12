package Service;

import com.google.gson.Gson;

public class ConvierteDatos implements IConvierteDatos{


    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        Gson gson= new Gson();

        var moneda= gson.fromJson(json, clase);
        return moneda;
    }
}
