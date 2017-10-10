package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jakub Jaroš
 * @version   pro školní rok 2015/2016
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }
    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testVyhraAPrubeh() {
        
        HerniPlan plan  = hra1.getHerniPlan();
        Batoh batoh     = hra1.getBatoh();
        
        assertEquals(true, batoh.obsahujeVec("meč")); 
        assertEquals("chaloupka", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi rozcestí");
        assertEquals(false, hra1.konecHry());
        assertEquals("rozcestí", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi jeskyně");
        assertEquals(false, hra1.konecHry());
        assertEquals("rozcestí", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        assertEquals("les", plan.getAktualniProstor().getNazev());
        assertTrue(plan.getAktualniProstor().obsahujePostavu("medvěd"));
        hra1.zpracujPrikaz("zabij medvěd");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi příkop");
        assertEquals(false, hra1.konecHry());
        assertEquals("příkop", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi hrad");
        assertEquals(false, hra1.konecHry());
        assertEquals("příkop", plan.getAktualniProstor().getNazev());
        assertTrue(plan.getAktualniProstor().obsahujePostavu("krokodýl"));
        hra1.zpracujPrikaz("zabij krokodýl");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi hrad");
        assertEquals(false, hra1.konecHry());
        assertEquals("hrad", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi zbrojnice");
        assertEquals(false, hra1.konecHry());
        assertEquals("zbrojnice", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber lano");
        hra1.zpracujPrikaz("seber přilba");
        hra1.zpracujPrikaz("seber štít");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, batoh.obsahujeVec("meč"));
        assertEquals(true, batoh.obsahujeVec("lano"));
        assertEquals(true, batoh.obsahujeVec("přilba"));
        assertEquals(true, batoh.obsahujeVec("štít"));
        hra1.zpracujPrikaz("jdi hrad");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi kovárna");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("nabrousit meč");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi hrad");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi příkop");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi rozcestí");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi hospoda");
        assertEquals(false, hra1.konecHry());
        assertEquals("hospoda", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("pivo");
        assertEquals(false, hra1.konecHry());
        assertEquals(4, hra1.getHrac().getZivoty());
        hra1.zpracujPrikaz("jdi rozcestí");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi doupě");
        assertEquals(false, hra1.konecHry());
        assertEquals("rozcestí", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("lano doupě");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi doupě");
        assertEquals(false, hra1.konecHry());
        assertEquals("doupě", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi skrýš");
        assertEquals(false, hra1.konecHry());
        assertEquals("doupě", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("mluv trpaslík");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi rozcestí");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi jeskyně");
        assertEquals(false, hra1.konecHry());
        assertEquals("jeskyně", plan.getAktualniProstor().getNazev());
        assertEquals(true, plan.getAktualniProstor().obsahujePostavu("drak"));
        hra1.zpracujPrikaz("jdi pokladnice");
        assertEquals(false, hra1.konecHry());
        assertEquals("jeskyně",plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("zabij drak");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi pokladnice");
        assertEquals(false, hra1.konecHry());
        assertEquals("pokladnice", plan.getAktualniProstor().getNazev());
        assertEquals(true, batoh.obsahujeVec("dračí_sklo"));
        hra1.zpracujPrikaz("prozkoumej truhla");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber diamant");
        assertEquals(false, hra1.konecHry());
        assertEquals(false, batoh.obsahujeVec("diamant"));
        hra1.zpracujPrikaz("vyhod přilba");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("seber diamant");
        assertEquals(false, hra1.konecHry());
        assertEquals(true, batoh.obsahujeVec("diamant"));
        hra1.zpracujPrikaz("jdi jeskyně");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi rozcestí");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi doupě");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skrýš");
        assertEquals(false, hra1.konecHry());
        assertEquals("doupě", plan.getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("dej diamant trpaslík");
        assertEquals(false, hra1.konecHry());
        assertEquals(false, batoh.obsahujeVec("diamant"));
        assertEquals(true, batoh.obsahujeVec("klíč"));
        hra1.zpracujPrikaz("odemkni skrýš");
        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("jdi skrýš");
        assertEquals(true, hra1.konecHry());
    }
    @Test
    public void testProhra() {
       
        hra1.zpracujPrikaz("jdi rozcestí");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("zabij medvěd");
        hra1.zpracujPrikaz("jdi příkop");
        hra1.zpracujPrikaz("jdi hrad");
        hra1.zpracujPrikaz("zabij krokodýl");
        hra1.zpracujPrikaz("jdi hrad");
        hra1.zpracujPrikaz("jdi zbrojnice");
        hra1.zpracujPrikaz("seber lano");
        hra1.zpracujPrikaz("jdi hrad");
        hra1.zpracujPrikaz("jdi příkop");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi rozcestí");
        hra1.zpracujPrikaz("lano doupě");
        hra1.zpracujPrikaz("jdi doupě");
        hra1.zpracujPrikaz("mluv trpaslík");
        hra1.zpracujPrikaz("jdi rozcestí");
        hra1.zpracujPrikaz("jdi jeskyně");
        hra1.zpracujPrikaz("zabij drak");
        assertEquals(true, hra1.konecHry());
        
    }
    @Test
    public void testPrikazKonec() {

        assertEquals(false, hra1.konecHry());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
}
