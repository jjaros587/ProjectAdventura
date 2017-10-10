package logika;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 * 
 * @author    Luboš Pavlíček
 * @version   pro školní rok 2015/2016
 */
public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prikazKonec;
    private PrikazJdi prikazJdi;
    
    @Before
    public void setUp() {
        hra = new Hra();
        prikazKonec = new PrikazKonec(hra);
        prikazJdi = new PrikazJdi(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznamPrikazu = new SeznamPrikazu();
        seznamPrikazu.vlozPrikaz(prikazKonec);
        seznamPrikazu.vlozPrikaz(prikazJdi);
        assertEquals(prikazKonec, seznamPrikazu.vratPrikaz("konec"));
        assertEquals(prikazJdi, seznamPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznamPrikazu.vratPrikaz("napoveda"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznamPrikazu = new SeznamPrikazu();
        seznamPrikazu.vlozPrikaz(prikazKonec);
        seznamPrikazu.vlozPrikaz(prikazJdi);
        assertEquals(true, seznamPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznamPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznamPrikazu.jePlatnyPrikaz("napoveda"));
        assertEquals(false, seznamPrikazu.jePlatnyPrikaz("Konec"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznamPrikazu = new SeznamPrikazu();
        seznamPrikazu.vlozPrikaz(prikazKonec);
        seznamPrikazu.vlozPrikaz(prikazJdi);
        String nazvy = seznamPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("napoveda"));
        assertEquals(false, nazvy.contains("Konec"));
    }
    
}
