package cedms.logic;

import cedms.estructuras.de.datos.Lista;
import cedms.estructuras.de.datos.ListaNodo;

public class Dispositivo {

    protected String _Id;
    protected Lista<Enlace> _listaDeDispositivosConectados = new Lista<>();
    protected int _puerto;

    public void insertarNuevaConexcion(Enlace pNuevoEnlace){
        this._listaDeDispositivosConectados.insertarAlInicio(pNuevoEnlace);
    }
    
    public String getId(){
        return this._Id;
    }
    
    public int getPuerto(){
        return _puerto;
    }
    
    public void printIdDispositivosConectados(){
        System.out.println("\n Imprimiento Dispositivos conectados a:"+_Id);
        for(ListaNodo<Enlace> i = _listaDeDispositivosConectados.getHead(); i != null; i= i.getSiguiente())
            System.out.println(i.getDato().getConexionDestino().getId());
    }
    
}
