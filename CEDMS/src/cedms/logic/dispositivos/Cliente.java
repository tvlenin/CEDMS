package cedms.logic.dispositivos;

import cedms.estructuras.de.datos.Lista;
import cedms.logic.Dispositivo;

public class Cliente extends Dispositivo{
    
    public Cliente(String pId){
        this._Id = pId;
        this._dispositivosConectados = new Lista<>();
    }
}
