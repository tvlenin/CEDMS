package cedms.estructuras.de.datos;

public class Lista<E> { 
    
    protected  ListaNodo<E> cabeza;
    protected ListaNodo<E> cola;
    protected int talla;
    
    public Lista(){
        cabeza = null;
        cola = null;
        talla = 0;
    }
    public Lista(Lista<String> pHashCodes){
        if(pHashCodes.getTalla() == 0)
            return;
        ListaNodo nuevoA = new ListaNodo(null, pHashCodes.getHead().getDato());
        cabeza = nuevoA;
        cola = nuevoA;
        talla = 0;
        for(ListaNodo<String> i = pHashCodes.getHead().getSiguiente(); i!= null; i = i.getSiguiente()){
            ListaNodo nuevoB = new ListaNodo(null, i.getDato());
            nuevoA.siguiente = nuevoB;
            nuevoB.previo = nuevoA;
            talla++;
            nuevoA = nuevoA.getSiguiente();
        }
    }
    
    public void insertar(E pData){
        ListaNodo<E> nuevo = new ListaNodo<>(pData);        
        if (talla == 0 ){
            cola = nuevo;
        }
        else{
            cabeza.previo = nuevo;
        }
        nuevo.siguiente = cabeza;
        nuevo.previo = null;
        cabeza = nuevo;
        this.talla++;
    }
    
    public void insertarFinal(E pData) {
        ListaNodo<E> nuevo = new ListaNodo<>(pData);
        if( talla == 0){
            cabeza = nuevo;
        }
        else{
            cola.siguiente = nuevo;
        }
        nuevo.previo = cola;
        nuevo.siguiente = null;
        cola = nuevo;
        talla++;
    }
    public void insertarEnMedio( E pData, ListaNodo<E> pNodoPrevio){
        if (pNodoPrevio == cabeza){
            this.insertar(pData);
            return;
        }
        ListaNodo<E> nuevoNodo = new ListaNodo<>(pData);
        ListaNodo<E> nodoTmp = pNodoPrevio.getSiguiente();
        
        pNodoPrevio.siguiente = nuevoNodo;
        nuevoNodo.previo = pNodoPrevio;
        nuevoNodo.siguiente = nodoTmp;
        nodoTmp.previo = nuevoNodo;
        
        this.talla++;
    }
      
    public boolean eliminar(E x){
        if(cabeza == null)
            return false;
        if (talla == 1){ //no se puede eliminar una lista con un nodo
            cabeza = null;
            cola= null;
            talla = 0;
        }
        ListaNodo<E> tmp = cabeza, anterior = null;
        boolean res = false;
        while ( tmp != null && !tmp.dato.equals( x ) ){
            anterior = tmp;
            tmp=tmp.siguiente;
        }
        if ( tmp != null ){
            res = true;
            this.talla--;
            if( anterior == null ){
                this.cabeza = tmp.siguiente;
            }
            else{
                anterior.siguiente=tmp.siguiente;
            }                 
        }
        return res;
    }
    
    public ListaNodo<E> getDato(){
        ListaNodo<E> nodoResp = null;
        nodoResp = nodoResp.siguiente;
        return nodoResp;
    }
    
    public ListaNodo<E> getCentro(ListaNodo<E> A, ListaNodo<E> B){
        if (cabeza == null)
            return null;
        if (talla == 3)
            return cabeza.getSiguiente();
        if(A.getSiguiente() == B)
            return A;
        ListaNodo resp = A;
        int contador = 0;
        for(ListaNodo<E> i = A ; i != B; i = i.getSiguiente()){
            resp = resp.getSiguiente();
            contador++;
        }
        resp = A;
        for(int i=contador/2 ; contador > 0 ; contador--){
            resp = resp.getSiguiente();
        }
        return resp;
    }
    public int getPos(ListaNodo<E> pNodo){
        int resp = 0;
        for (ListaNodo<E>i = pNodo; pNodo != null; pNodo=pNodo.getSiguiente())
            resp++;
        return resp;
    }
    public ListaNodo<E> getCentro(){
        if (cabeza == null)
            return null;
        
        ListaNodo<E> centro = cabeza;
        for(int i =0; i < talla/2;i++){
            centro= centro.getSiguiente();
        }
        return centro;
    }
    
    public boolean buscar(E x){
        boolean resp = false;
        for ( ListaNodo<E> tmp = cabeza; tmp != null; tmp = tmp.siguiente ) {
            if ( x.equals(tmp.dato) ) {
            resp = true;
            }
        }    
        return resp;
    }
    
    public ListaNodo<E> getAndQuitarElementoAzar(){
        ListaNodo<E> nodoTmp;
        int posicionAzar = (int )(Math.random() * talla);
        
        ListaNodo<E> datoEscogido = cabeza;
        for ( int cont = 0; cont < posicionAzar; cont++ ) {
            datoEscogido = datoEscogido.siguiente;
        }
        nodoTmp=datoEscogido;
        this.eliminar(datoEscogido.getDato());
        return nodoTmp;
    }
    
    
    public ListaNodo<E> getHead(){
        return cabeza;
    }
        
    //metodo para obtener elemento al azar solamente
    public ListaNodo<E> mostrarElementoAzar(){ //muestra solamente 
    
        int posicionAzar = (int )(Math.random() * talla);
        
        ListaNodo datoEscogido = cabeza;
        
        for (int cont = 0; cont < posicionAzar; cont++) {
            datoEscogido = datoEscogido.siguiente;
        }
        ListaNodo <E> tmp2 = datoEscogido;
        return tmp2;
    }
   
    public void intercambiarNodos(ListaNodo<E> i, ListaNodo<E> j){
        if(i == j)
            return ;//son iguales
        if( i.estaEnExtremos() || j.estaEnExtremos() ){
            E tmp = i.getDato();
            i.dato= j.dato;
            j.dato=tmp;
        }
        else if( i.getSiguiente() == j || j.getSiguiente()==i ){
            i.previo.siguiente = j;
            j.previo = i.previo;
            
            j.siguiente.previo = i;
            i.siguiente = j.siguiente;
            
            i.previo = j;
            j.siguiente = i;
        }
        else{
            if(! (i==cabeza))  i.previo.siguiente = j;
            
            if(! (j==cabeza)){  
                if (j.previo.siguiente != i){
                    j.previo.siguiente = i;
                }
            }
            
            if(!(i==cola)) i.siguiente.previo= j;
            if(!(j==cola)) j.siguiente.previo= i;

            if(i == cabeza) {cabeza = j;}
            else if(j ==cabeza) cabeza = i;
            if(i == cola) cola = j;
            else if(j == cola) cola = i;

            ListaNodo<E> temp = i.previo;

            i.previo = j.previo;    
            j.previo = temp;
            temp = i.siguiente;
            i.siguiente = j.siguiente;    
            j.siguiente = temp;
        }
}  
       
    public void mezclarTodosLosNodos(){
        ListaNodo<E> cambiandoNodoA = cabeza;
        ListaNodo<E> cambiandoNodoB;
        
        for (int i = 0; i < talla; i++){
            int posicionAzar = (int )(1+(Math.random() * (talla-1))); //numeros [1 , talla-1]
            cambiandoNodoB = cabeza;
            while( posicionAzar >= 1 ){
                cambiandoNodoB = cambiandoNodoB.getSiguiente();
                posicionAzar--;
            }
            this.intercambiarNodos(cambiandoNodoA, cambiandoNodoB);
            cambiandoNodoA = cambiandoNodoA.getSiguiente();
        }
    }
    
    public ListaNodo<E> getTail(){
        return cola;
    }
    public int getTalla(){
        return this.talla;
    } 
    
    
    public ListaNodo<E> getNodoXpos(int x){
        ListaNodo<E> resp = cabeza;
        for(int i=0 ; i < x ;i++){
            if(resp != cola)
                resp = resp.getSiguiente();
            else{
                System.out.println("indice fuera de rango");
                return null;
            }
        }
        return resp;
    }
    public int getDistancia(ListaNodo<E> pNodoA, ListaNodo<E> pNodoB){
        int resp=0;
        ListaNodo<E> iterador=pNodoA;
        while(iterador != pNodoB || iterador != null){
            iterador= iterador.getSiguiente();
            resp++;
        }
        if(iterador==null){
            resp=0;
            iterador=pNodoB;
            while(iterador != pNodoA || iterador == null){
                resp++;
                iterador= iterador.getSiguiente();
            }
        }
        return resp;
    }
    
    public void concatenar(Lista<E> pLista){
        if(talla == 0){
            cabeza = pLista.getHead();
            talla = pLista.getTalla();
            cola = pLista.getTail();
            return;
        }
        cola.siguiente = pLista.getHead();
        pLista.getHead().previo = cola;
        cola = pLista.getTail();
        talla = talla + pLista.getTalla();
    }
    
    public void insertarEnIndex(String pIndex, E pData){
        for(ListaNodo<E> i = cabeza; i != null; i = i.getSiguiente())
            if(pIndex == i.getIndex())
                i.setData(pData);
    }
    public E getIndice(String pIndex){
        E resp = null;
        for (ListaNodo<E> i = cabeza; i != null; i = i.getSiguiente() )
            if(i.getIndex() == pIndex)
                resp = i.getDato();
        return resp;
    }
    
    public E getDataIndex(String pIndex){
        E resp = null;
        for(ListaNodo<E> i = cabeza; i != null; i=i.getSiguiente()){
            if(i.getIndex() == pIndex)
                resp = i.getDato();
        }
        return resp;
    }
}