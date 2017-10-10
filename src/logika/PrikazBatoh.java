package logika;

/**
 *  Třída PrikazBatoh implementuje pro hru příkaz batoh.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazBatoh implements IPrikaz {
    private static final String NAZEV = "batoh";
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param batoh, do kterého se ukládají předměty 
    */    
    public PrikazBatoh(Batoh batoh) {
        this.batoh = batoh;
    }
    /**
     *  Provádí příkaz "batoh". Najde věci v batohu a vypíše je
     *
     *@param parametry - bez parametrů
     *
     *@return seznam věcí v batohu
     */ 
    @Override
    public String proved(String... parametry) {
        return batoh.veciVBatohu()+"\n"; 
    }    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
