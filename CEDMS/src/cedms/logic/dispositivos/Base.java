package cedms.logic.dispositivos;

import cedms.logic.Dispositivo;

public class Base  extends  Dispositivo{
    
    public Base(String pId, String pPuerto){
        super._Id = pId;
        super._puerto = Integer.parseInt(pPuerto);
    }
}
