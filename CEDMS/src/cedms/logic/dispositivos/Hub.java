package cedms.logic.dispositivos;

import cedms.logic.Dispositivo;

public class Hub extends Dispositivo{
    
    public Hub(String pId, String pPuerto ){
        super._Id = pId;
        super._puerto = Integer.parseInt(pPuerto);
    }
}
