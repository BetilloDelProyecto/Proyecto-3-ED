package Ciudad;

public class Conexion {
    String nomCiudad;
    int fuerzaMilitar;
    int bienes;
    int distancia;

    public Conexion(String nomCiudad, int fuerzaMilitar, int bienes, int distancia) {
        this.nomCiudad = nomCiudad;
        this.fuerzaMilitar = fuerzaMilitar;
        this.bienes = bienes;
        this.distancia = distancia;
    }

    public String getNomCiudad() {
        return nomCiudad;
    }

    public void setNomCiudad(String nomCiudad) {
        this.nomCiudad = nomCiudad;
    }

    public int getFuerzaMilitar() {
        return fuerzaMilitar;
    }

    public void setFuerzaMilitar(int fuerzaMilitar) {
        this.fuerzaMilitar = fuerzaMilitar;
    }

    public int getBienes() {
        return bienes;
    }

    public void setBienes(int bienes) {
        this.bienes = bienes;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    @Override
    public String toString() {
        return "Conexion{" + "nomCiudad=" + nomCiudad + ", fuerzaMilitar=" + fuerzaMilitar + ", bienes=" + bienes + ", distancia=" + distancia + '}';
    }
    
    
    
}
