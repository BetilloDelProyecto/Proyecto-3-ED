package Ciudad;

import java.util.*;
import java.io.*;

import Graphing.Grafos;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Mundo {
    ArrayList<Ciudad> mundo;

    public Mundo() {
        mundo = new ArrayList();
    }

    public ArrayList<Ciudad> getMundo() {
        return mundo;
    }

    public void setMundo(ArrayList<Ciudad> mundo) {
        this.mundo = mundo;
    }

    public Grafos grafo;
    
    public void imprimir(){
        for (int i = 0; i < mundo.size(); i++) {
            Ciudad get = mundo.get(i);
            get.imprimir();
        }
    }
    
    
    public void cargarMundo(){
        JSONParser jsonParser = new JSONParser();
        try(FileReader reader = new FileReader("C:\\Users\\Amerc\\Documents\\TEC\\POO\\Proyecto-3-ED\\src\\main\\java\\Ciudad\\Grafo.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray array = (JSONArray) obj;
            for (int i = 0; i < array.size() ; i++) {
                JSONObject ciudadJson = (JSONObject) array.get(i);
                
                long sold = (long)ciudadJson.get("Soldados");
                int soldados = (int)sold;
                long misil = (long)ciudadJson.get("Misiles");
                int misiles = (int)misil;
                long niv = (long)ciudadJson.get("Nivel Tecnologico");
                int nivel = (int)niv;
                Ciudad c = new Ciudad((String)ciudadJson.get("Ciudad"), soldados, misiles, nivel);
                
                JSONArray conexiones = (JSONArray) ciudadJson.get("Conexiones");
                
                for (int j = 0; j < conexiones.size(); j++) {
                    JSONObject conexionJson = (JSONObject) conexiones.get(j);
                    long fue = (long)conexionJson.get("Fuerza Militar");
                    int fuerzaMilitar = (int)fue;
                    long bien = (long)conexionJson.get("Bienes");
                    int bienes = (int)bien;
                    long dis = (long)conexionJson.get("Distancia");
                    int distacia = (int)dis;
                    Conexion conex = new Conexion((String)conexionJson.get("Ciudad"), fuerzaMilitar, bienes, distacia);
                    c.agregarConexion(conex);
                }
                getMundo().add(c);
                
            }
            grafo = new Grafos(getMundo());
            
            
        }catch(FileNotFoundException e){
            System.out.println("NO SE ENCONTRO EL ARCHIVO");
        }catch(IOException e){
            System.out.println("El archivo esta corrupto");
        }catch(ParseException e){
            System.out.println("El parseo esta mal");
        }
    }
}
