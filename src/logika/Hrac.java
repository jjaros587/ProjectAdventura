package logika;


/*******************************************************************************
 * Instance třídy {@code Hrac} představuje hráče
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class Hrac
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private int zivoty = 4;
    private int penize = 0;
    private int utok   = 1;

    //##########################################################################
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================

    /***************************************************************************
     *Konstruktor pro vytvoření instance třídy hráč,
     *která uchovává počet životů a zlaťáků hráče
     */
    public Hrac()
    {
        this.zivoty = zivoty;
        this.penize = penize;
        this.utok   = utok;
    }
    
    //== NESOUKROMÉ METODY INSTANCÍ ====================================
    /**
     *  Metoda nastaví životy hráče
     *
     *@param  počet životů
     */ 
    public void setZivoty(int zivoty){
        
        this.zivoty = zivoty;
    }
    /**
     *  Metoda nastaví počet zlaťáků, která hráč získal
     *
     *@param  počet zlaťáků
     */ 
    public void setPenize(int penize){
        
        this.penize = penize;
    }
    /**
     *  Metoda pro získání počtu životů hráče
     *
     *@return  počet životů hráče
     */ 
    public int getZivoty(){
        
        return zivoty;
    }
    /**
     *  Metoda pro získání počtu zlaťáků
     *
     *@return  počet zlaťáků, které hráč má
     */ 
     public int getPenize(){
        
        return penize;
    }
    /**
     *  Metoda pro hodnoty útoku hráče
     *
     *@return  útok hráče
     */ 
     public int getUtok(){
        
        return utok;
    }
    /**
     *  Metoda nastaví hodnotu útoku hráče
     *
     *@param  útok hráče
     */ 
    public void setUtok(int utok){
        
        this.utok = utok;
    }
}
