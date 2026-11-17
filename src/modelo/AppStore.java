package modelo;

import java.util.ArrayList;
import java.util.List;

public class AppStore {
    List<Juego> juegos;

    public AppStore(){
        juegos = new ArrayList<Juego>();
    }

    public void cargarDatos(){
        agregarJuego(new Juego("FIFA 26","Deportes",15,36,50));
        agregarJuego(new Juego("Roblox","Acción",25,90,20));
    }

    public List<Juego> getJuegos(){
        return juegos;
    }

    public boolean agregarJuego(Juego j){
        if (j !=null){
            juegos.add(j);
            return true;
        }
        return false;
    }

    public Juego buscarJuego (String nombre){
        if (nombre != null && !nombre.trim().isEmpty()){
            for (int i = 0; i < juegos.size(); i++){
                if (juegos.get(i).getNombre().equalsIgnoreCase(nombre)){
                    return juegos.get(i);
                }
            }
            return null;
        }
        return null;
    }

    public boolean comprar (String nombre, int cantidad) {
        if (nombre != null && !nombre.trim().isEmpty() && cantidad > 0) {
            Juego j = buscarJuego(nombre);
            if (j != null) {
                return j.comprar(cantidad);
            }
        }
        return false;
    }

    public double vender (String nombre, int cantidad){
        Juego j = buscarJuego(nombre);
        if (j !=null){
            if (j.vender(cantidad)){
                return j.getPrecio()*cantidad;
            }else{
                return 0;
            }
        }
        return 0;
    }

    public String masVendido(){
        String nombre = "";
        int mayor;
        if (juegos.isEmpty()){
            nombre = juegos.get(0).getNombre();
            mayor = juegos.get(0).getLicenciasVendidas();
            for (Juego j: juegos){
                if (j.getLicenciasVendidas() > mayor){
                    nombre = j.getNombre();
                    mayor = j.getLicenciasVendidas();
                }
            }
        }
        return nombre;
    }
}