package cedms.logic.dispositivos;

import cedms.estructuras.de.datos.Lista;
import cedms.logic.Dispositivo;

public class Cliente extends Dispositivo{
    
    public Cliente(String pId, String pPuerto){
        super._Id = pId;
        super._puerto = Integer.parseInt(pPuerto);
    }
}
