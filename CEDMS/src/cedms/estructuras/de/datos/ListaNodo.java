package cedms.estructuras.de.datos;

public class ListaNodo<E>{
    public E dato;
    private String _indice;
    public ListaNodo<E> siguiente;
    public ListaNodo<E> previo;
    
    public ListaNodo(E dato){
        this(dato,null,null);
    }
    
    public ListaNodo(E pDato, ListaNodo<E> pSiguiente, ListaNodo<E> pPrevio){
        this.dato = pDato;
        this.siguiente = pSiguiente;
        this.previo = pPrevio;
    }
    public ListaNodo(E dato, String Indice){
        this._indice = Indice;
        this.dato = dato;
        this.previo = null;
        this.siguiente = null;
    }
    
    public void setData(E pData){
        this.dato = pData;
    }
    public void setSiguiente(ListaNodo<E> pNodo){
        this.siguiente = pNodo;
    }
    
    public E getDato(){
        return dato;
    }
    
    public ListaNodo<E> getSiguiente(){
        return siguiente;
    }
    public ListaNodo<E> getPrevio(){
        return previo;
    }

    public boolean tienePrevio(){
        boolean resp = false;
        if(previo != null)
            resp = true;
        return resp;
    }
    public boolean estaEnExtremos(){
        boolean resp= false;
        if (siguiente==null || previo == null)
            resp = true;
        return resp;
    }
    public boolean tieneSiguiente(){
        boolean resp = false;
        if(siguiente != null)
            resp = true;
        return resp;
    }
    
    public String getIndex(){
        return _indice;
    }
    
}