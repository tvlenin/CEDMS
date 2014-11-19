package cedms;

import cedms.estructuras.de.datos.Grafo;
import cedms.estructuras.de.datos.Lista;
import cedms.logic.Dispositivo;
import cedms.logic.dispositivos.Base;
import cedms.logic.dispositivos.Cliente;
import cedms.logic.dispositivos.Hub;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class CEDMS {
    public CEDMS(){}
    public static void printMatriz(int[][] matrix){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                System.out.print(matrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws UnknownHostException{

        Grafo grafo = new Grafo();
        grafo.insertarAlGrafo("nodo1", "cliente","9000");
        grafo.insertarAlGrafo("nodo2", "base","9000");
        grafo.insertarAlGrafo("nodo3", "cliente","9000");
        grafo.insertarAlGrafo("nodo4", "cliente","9000");
        grafo.insertarAlGrafo("nodo5", "cliente","9000");
        grafo.insertarAlGrafo("nodo6", "cliente","9000");
        grafo.insertarAlGrafo("nodo8", "cliente","9000");
        grafo.insertarAlGrafo("nodo7", "cliente","9000");
          
        grafo.conectarDireccion("nodo1", "nodo2",3);
       // grafo.conectarDireccion("nodo1", "nodo3",40);
        grafo.conectarDireccion("nodo2", "nodo1",4);
        grafo.conectarDireccion("nodo2", "nodo3",8);
        grafo.conectarDireccion("nodo3", "nodo2",5);
        grafo.conectarDireccion("nodo3", "nodo4",15);
        grafo.conectarDireccion("nodo4", "nodo3",2);
        grafo.conectarDireccion("nodo4", "nodo5",2);
        grafo.conectarDireccion("nodo5", "nodo4",3);
        grafo.conectarDireccion("nodo1", "nodo7",1);
        grafo.conectarDireccion("nodo7", "nodo1",1);
        grafo.conectarDireccion("nodo7", "nodo8",1);
        grafo.conectarDireccion("nodo8", "nodo7",1);
        grafo.conectarDireccion("nodo8", "nodo3",1);
        grafo.conectarDireccion("nodo3", "nodo8",1);

         grafo.doFloyd();
         grafo.getRutaOptima("nodo1", "nodo8");
    }
}
