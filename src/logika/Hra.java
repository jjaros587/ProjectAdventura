package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2015/2016
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;
    private HerniPlan herniPlan;
    private Batoh batoh;
    private Hrac hrac;
    private Hra hra;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan), batoh a seznam platných příkazů.
     */
    public Hra() {
        
        hrac        = new Hrac();
        herniPlan   = new HerniPlan(); 
        batoh       = new Batoh();
                    
            Vec mec = new Vec("meč", true, true, 1);
            batoh.vlozVec(mec);
            
        //--- PŘÍKAZY =========================
        platnePrikazy = new SeznamPrikazu();
        
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBatoh(batoh));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, batoh, hrac));
        platnePrikazy.vlozPrikaz(new PrikazVyhod(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazOdemkni(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazLano(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazPivo(herniPlan, hrac));
        platnePrikazy.vlozPrikaz(new PrikazZabij(herniPlan, batoh, hrac, hra));
        platnePrikazy.vlozPrikaz(new PrikazNabrousit(herniPlan, batoh, hrac));
        platnePrikazy.vlozPrikaz(new PrikazDej(herniPlan, batoh));
        platnePrikazy.vlozPrikaz(new PrikazPlanek(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloha(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInfo(herniPlan, hrac, batoh));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        
    }
    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
               "Toto je hra o mladíkovi, který chce získat dědictví po svém zemřelém otci.\n\n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               herniPlan.getAktualniProstor().dlouhyPopis();
    }
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        
        
        if(herniPlan.vyhra()){
            
            return "VÝHRA. Dík, že jste si zahráli";
        }
        if(herniPlan.prohra(hrac)){
            
            return "PROHRÁL JSI!!!!!";
        }
        
            return "Hra byla ukončena.";
    }
    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }
    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            
            if(herniPlan.vyhra()){
                
                konecHry = true;
            }
            if(herniPlan.prohra(hrac)){
                
                konecHry = true;
            }
        }
        else {
            textKVypsani="Nevím co tím myslíš. Tento příkaz neznám.\n";
        }
        return textKVypsani;
    }
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    /**
    *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
    *  kde se jejím prostřednictvím získává aktualní místnost hry.
    *  
    *  @return     odkaz na herní plán
    */
    public HerniPlan getHerniPlan(){
       return herniPlan;
    }
     /**
     * metoda vrací batoh hráče
     * 
     * @return   batoh
     */
    public Batoh getBatoh(){
        return batoh;
    }
    /**
     * metoda vrací hráče
     * 
     * @return   hrac
     */
    public Hrac getHrac(){
        return hrac;
    }
}

