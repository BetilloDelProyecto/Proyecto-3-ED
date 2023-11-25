package Graphing;


public class Tupla {
    String ciudad1,ciudad2;
    String res; 
    int cont = 0;
    public Tupla(String ciudad1, String ciudad2) {
        this.ciudad1 = ciudad1;
        this.ciudad2 = ciudad2;
        res = "(" + ciudad1 + " : " + ciudad2 +")";
    }

    public String getCiudad1() {
        return ciudad1;
    }

    public String getCiudad2() {
        return ciudad2;
    }
    
    
    
    public String getRes(){
        return res;
    }
    
    public int getPos(String ciudad){
        if(ciudad.equals(ciudad1) || ciudad.equals(ciudad2)){
            if( res.indexOf(ciudad) < res.indexOf(":"))
                return 0;
            else
                return 1;
        }else{
            return -1;
        }
    }
    
    
    
    public boolean estaCiudad(String ciudad){
        return res.indexOf(ciudad) != -1;
    }
    
    public void swap(){
        if(cont++%2 == 0 )
            res = "(" + ciudad2 + " : " + ciudad1 +")";
        else 
            res = "(" + ciudad1 + " : " + ciudad2 +")";
    }
    
    public void imprimir(){
        System.out.println(res);
    }
    
    @Override
    public String toString(){
        return res;
    }
}
