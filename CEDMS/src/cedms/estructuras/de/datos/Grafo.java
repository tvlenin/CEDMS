package cedms.estructuras.de.datos;

import cedms.logic.Dispositivo;
import cedms.logic.Enlace;
import cedms.logic.dispositivos.*;
import cedms.logic.floyd;
import static cedms.logic.floyd.shortestpath;

public class Grafo {
    protected Lista<Dispositivo> _listaDeNodosEnGrafo = new Lista<>();
    protected int[][] _matrizDeAdyacencia = new int[0][0];
    protected int[][] _matrizRutaMasCorta;
    protected int[][] _matrizDeCostosMinimos;
    
    int valorDeRutaInaccesible = 9000000;
    
    /**
     * Aca se inserta un nodo nuevo al grafo, sin conexciones.
     * @param pId identificador unico del nodo
     * @param pTipo hub, cliente, o base
     * @param pPuerto el puerto que va a utilizar para la comunicacion atravez de la red
     * @return true en caso de exito
     */
    public boolean insertarAlGrafo(String pId, String pTipo, String pPuerto){
        if(this.idEstaEnElGrafo(pId)){
            System.out.println("No pueden haber dos ID iguales en el grafo, disculpe las molestias, vuelva mas tarde");
            return false;
        }
        Dispositivo nuevoDispositivo;
        if("base".equals(pTipo))
            nuevoDispositivo = new Base(pId, pPuerto);
        else if("hub".equals(pTipo))
            nuevoDispositivo = new Hub(pId, pPuerto);
        else if("cliente".equals(pTipo))
            nuevoDispositivo = new Cliente(pId, pPuerto);
        else{//entonces es un cliente
            System.out.println("No existe un dispositivo de tipo:"+pTipo);
            return false;
        }
        this._listaDeNodosEnGrafo.insertarFinal(nuevoDispositivo);
        this.actualizarMatrizAdyacencia();
        return true;
    }
    
    /**
     * Para hacer una conexcion entre nodos, solamente se indica sus ID, la conexion es unidireccional 
     * por esto el orden de los argumentos importa, se asume que la conexcion va en direccion nodo1 hacia nodo2 solamente.
     * @param nodo1
     * @param nodo2
     * @param pCosto
     * @return 
     */
    public boolean conectarDireccion(String nodo1, String nodo2,int pCosto) {
        if(nodo1 == nodo2){
            System.out.println("Grafo-54. ID iguales, no se permiten bucles");
            return false;}
        Dispositivo nodoA = this.getNodoDelGrafoConId(nodo1);
        Dispositivo nodoB = this.getNodoDelGrafoConId(nodo2);
        if( nodoA == null){
            System.out.println("Grafo-55. No existe el nodo con ID: "+nodo1);
            return false;
        }
        if( nodoB == null){
            System.out.println("Grafo-55. No existe el nodo con ID: "+nodo2);
            return false;
        }
        Enlace nuevoEnlace = new Enlace(nodoB,pCosto);
        nodoA.insertarNuevaConexcion(nuevoEnlace);
        this._matrizDeAdyacencia[this._listaDeNodosEnGrafo.getPos(nodoA)][this._listaDeNodosEnGrafo.getPos(nodoB)] = pCosto;
        return true;
    }
    
    /**
     * Se utilizan Arrays para mantener la matriz de adyacencia, por esto al añadir un nuevo nodo la matriz debe crecer
     */
    public void actualizarMatrizAdyacencia(){
        int[][] nuevaMatrizAdyacencia = new int[_matrizDeAdyacencia.length+1][_matrizDeAdyacencia.length+1];
        for (int i=0; i<_matrizDeAdyacencia.length; i++)
            for (int j=0; j<_matrizDeAdyacencia.length; j++)
                nuevaMatrizAdyacencia[i][j] = _matrizDeAdyacencia[i][j];
        this._matrizDeAdyacencia = nuevaMatrizAdyacencia;
    }
    
    /**
     * Busca entre todos los nodos el identificador dado
     * @param pData identificador a buscar
     * @return  retorna true si esta, en caso contrario false.
     */
    private boolean idEstaEnElGrafo(String pData) {
        boolean respuesta = false;
        if(_listaDeNodosEnGrafo != null){
            for(ListaNodo<Dispositivo> i = _listaDeNodosEnGrafo.getHead(); i != null;i = i.getSiguiente()){
                if( i.getDato().getId() == pData){
                    respuesta = true;
                    break;
                }
            }
        }
        return respuesta;
    }
    
    /**
     * Retorna el nodo con un Id deseado
     * @param pData string que contiene el Id del nodod deseado
     * @return  retora un tipo de dispositivo (base,cliente,hub)
     */
    private Dispositivo getNodoDelGrafoConId(String pData){
        for(ListaNodo<Dispositivo> i = _listaDeNodosEnGrafo.getHead(); i!=null; i = i.getSiguiente())
            if(i.getDato().getId() == pData)
                return i.getDato();
        return null;
    }
    
    public Dispositivo getElementoAzarGrafo(){
        return this._listaDeNodosEnGrafo.getElementoAlAzar().getDato();
    }
    
    public int[][] getMatrizDeAdyacencia(){
        return this._matrizDeAdyacencia;
    }
      
    /**
     * Con la matriz de adyacencia se realiza floyd
     */
    public void doFloyd(){
        this._matrizRutaMasCorta = new int[_matrizDeAdyacencia.length][_matrizDeAdyacencia.length];//mediante la clase floyd, 
      //  actualiza esa variable, que va a contener la ruta optima de nodo a nodo.
        for (int i=0; i<_matrizDeAdyacencia.length; i++)
            for (int j=0; j<_matrizDeAdyacencia.length;  j++)
                if(_matrizDeAdyacencia[i][j] == 0 && i != j)
                    _matrizDeAdyacencia[i][j] = valorDeRutaInaccesible; // se asume como inaccesible una ruta con valor 9000000
                else if( i==j)
                    _matrizDeAdyacencia[i][j] = 0; // ruta de un nodod hasta el mismo
        for (int i=0; i<_matrizDeAdyacencia.length; i++)
            for (int j=0; j<_matrizDeAdyacencia.length;  j++)
                if (_matrizDeAdyacencia[i][j] == valorDeRutaInaccesible){
                    _matrizRutaMasCorta[i][j] = -1;
                }else
                    _matrizRutaMasCorta[i][j] = i;
        _matrizDeCostosMinimos = shortestpath(_matrizDeAdyacencia, _matrizRutaMasCorta);
    }
    
    /**
     * 
     * @param A 
     * @param B 
     */
    public void getRutaOptima(String A, String B){
        if(this.idEstaEnElGrafo(A) && this.idEstaEnElGrafo(B)){
            int start =_listaDeNodosEnGrafo.getPos(this.getNodoDelGrafoConId(A));
            int end =_listaDeNodosEnGrafo.getPos(this.getNodoDelGrafoConId(B));
            if(this._matrizDeCostosMinimos[start][end] == valorDeRutaInaccesible){
                System.out.println("No hay ruta disponible desde: "+_listaDeNodosEnGrafo.getNodoXpos(start).getDato().getId()+
                        ", hasta: "+_listaDeNodosEnGrafo.getNodoXpos(end).getDato().getId() );
                return;
            }
            String myPath = this._listaDeNodosEnGrafo.getNodoXpos(end).getDato().getId() + "";
            while (this._matrizRutaMasCorta[start][end] != start) {
                myPath =_listaDeNodosEnGrafo.getNodoXpos(_matrizRutaMasCorta[start][end] ).getDato().getId() + " -> " + myPath; 
                end = this._matrizRutaMasCorta[start][end];
            }
            myPath = this._listaDeNodosEnGrafo.getNodoXpos(start).getDato().getId() + " -> " + myPath;
            System.out.println("La mejor ruta es:"+myPath);   
        }
    }

    
}
