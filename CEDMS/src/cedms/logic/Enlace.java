package cedms.logic;

import cedms.estructuras.de.datos.Grafo;

public class Enlace extends Grafo{

    private Dispositivo _destino;
    private int _costo;
    
    /**
     * Constructor del Enlance entre dos nodos del grafo
     * Asume la direccion desde A hasta B solamente
     * Es un grafo dirigido
     * @param pNodoA Desde aca puede ir hacia B
     * @param pNodoDestino Pero no hay ruta hacia B
     * @param pCosto es el costo de la ruta 
     */
    public Enlace(Dispositivo pNodoDestino, int pCosto){
        this._costo = pCosto;
        this._destino = pNodoDestino;
    }
    
    public void setCosto(int pData){
        this._costo = pData;
    }
    
    public Dispositivo getConexionDestino(){
        return _destino;
    }
    
    public int getCosto(){
        return _costo;
    }
    
}


