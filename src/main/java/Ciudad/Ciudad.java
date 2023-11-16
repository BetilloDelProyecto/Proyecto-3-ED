package Ciudad;

import java.util.ArrayList;

public class Ciudad {
    String nomCiudad;
    int soldados;
    int misilies;
    int nivelTecnologico;
    ArrayList<Conexion> conexiones = new ArrayList<>();

    //--------------------------------------------CONSTRUCTOR--------------------------------------------
    public Ciudad(String nomCiudad, int soldados, int misilies, int nivelTecnologico) {
        this.nomCiudad = nomCiudad;
        this.soldados = soldados;
        this.misilies = misilies;
        this.nivelTecnologico = nivelTecnologico;
    }
    //--------------------------------------------CONSTRUCTOR--------------------------------------------
    
    //--------------------------------------------GET & SET--------------------------------------------
    public String getNomCiudad() {
        return nomCiudad;
    }
    
    public void setNomCiudad(String nomCiudad) {
        this.nomCiudad = nomCiudad;
    }

    public int getSoldados() {
        return soldados;
    }

    public void setSoldados(int soldados) {
        this.soldados = soldados;
    }

    public int getMisilies() {
        return misilies;
    }

    public void setMisilies(int misilies) {
        this.misilies = misilies;
    }

    public int getNivelTecnologico() {
        return nivelTecnologico;
    }

    public void setNivelTecnologico(int nivelTecnologico) {
        this.nivelTecnologico = nivelTecnologico;
    }

    public ArrayList<Conexion> getConexiones() {
        return conexiones;
    }
    
    @Override
    public String toString() {
        return "Ciudad{" + "nomCiudad=" + nomCiudad + ", soldados=" + soldados + ", misilies=" + misilies + ", nivelTecnologico=" + nivelTecnologico + '}';
    }
    
    public void imprimir(){
        System.out.println("Ciudad{" + "nomCiudad=" + nomCiudad + ", soldados=" + soldados + ", misilies=" + misilies + ", nivelTecnologico=" + nivelTecnologico + '}');
        for (int i = 0; i < conexiones.size(); i++) {
            Conexion get = conexiones.get(i);
            System.out.println(get);
        }
        System.out.println("\n\n");
    }
    
    //--------------------------------------------GET & SET--------------------------------------------
    
    //--------------------------------------------METHODS--------------------------------------------
    public void agregarConexion(Conexion c){
        conexiones.add(c);
    }
    //--------------------------------------------METHODS--------------------------------------------

    
}
