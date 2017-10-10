package logika;

/**
 *  Třída PrikazPivo implementuje pro hru příkaz pivo.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazPivo implements IPrikaz {
    private static final String NAZEV = "pivo";
    private HerniPlan plan;
    private Hrac hrac;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param hráč
    */    
    public PrikazPivo(HerniPlan plan, Hrac hrac) {
        this.plan   = plan;
        this.hrac   = hrac;
    }

    /**
     *  Provádí příkaz "pivo". Pokud má hráč peníze na pivo, doplní se mu životy
     *
     *@param parametry - bez parametrů
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
    
        Prostor aktualniProstor = plan.getAktualniProstor();

        if (aktualniProstor.getNazev().equals("hospoda")) {
            
            if (hrac.getPenize() < 2){
                
                return "Bez peněz do hospody NELEZ!!!!!! Můžeš je získat zabitím medvěda a krokodýla\n";
            }
            else {
                
                hrac.setZivoty(4);
                hrac.setPenize(hrac.getPenize()-2);
                
                return "Doplnil sis životy\n";
            }
        }
        else {
            
            return "Na pivo si musíš zajít do hospody\n";
        }
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
