package logika;

/**
 *  Třída PrikazInfo implementuje pro hru příkaz info.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jakub Jaroš
 *@version    pro školní rok 2015/2016
 */
class PrikazInfo implements IPrikaz {
    private static final String NAZEV = "info";
    private HerniPlan plan;
    private Hrac hrac;
    private Batoh batoh;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    *  @param batoh, do kterého se vkládají věci
    *  @param hráč
    */    
    public PrikazInfo(HerniPlan plan, Hrac hrac, Batoh batoh) {
        this.plan   = plan;
        this.hrac   = hrac;
        this.batoh  = batoh;
    }

    /**
     *  Provádí příkaz "info". Pokud příkaz neobsahuje parametr, vypíší se údaje o hráči.
     *  Pokud obsahuje parametr (postava), vypíší se údaje o postavě
     *
     *@param parametry - bez parametrů nebo postava, o které chceme něco zjistit
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry) {
        
        if (parametry.length == 0) {
            
            return "Životy: " + hrac.getZivoty() +"\n"
                    + "Útok: " + hrac.getUtok() +"\n"
                    + "Zlaťáky: " + hrac.getPenize() +"\n";
        }
        
        String jmenoPostavy = parametry[0];
        
        Postava postava = plan.getAktualniProstor().vyberPostavu(jmenoPostavy);
        
        String odpoved = "";

        if (postava == null) {
            
            odpoved = "Taková postava se v prostoru nevyskytuje\n";
        }
        if(postava.jdeZabit() & postava.getZije()){
           
            odpoved =  postava.getPopis() + "\n"
                    + "Životy: "+ postava.getPocetZivotu() + "\n"
                    + "Útok: "  + postava.getPocetZivotu() + "\n"; 
        }
        if(!postava.jdeZabit()){
            odpoved =  postava.getPopis()+ "\n";      
        }
        if (postava.jdeZabit() & !postava.getZije()){
            odpoved = jmenoPostavy + " je již mrtvý";
        }
        return odpoved;
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
    
    @Override
    public void updateHerniPlan() {
        plan.notifyObservers();
    }

}
