/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */
package logika;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code PostavaTest} slouží ke komplexnímu otestování
 * třídy {@link PostavaTest}.
 *
 * @author  jméno autora
 * @version pro školní rok 2015/2016
 */
public class PostavaTest
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Postava medved;

    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        medved = new Postava("medvěd", "Pokud zabiješ medvěda, dostaneš 2 zlaťáky", true, true, 2, 2, 2);
    }

    @Test
    public void testPostava()
    {
        assertEquals("medvěd", medved.getJmeno());
        assertEquals("Pokud zabiješ medvěda, dostaneš 2 zlaťáky", medved.getPopis());
        assertTrue(medved.jdeZabit());
        
        assertTrue(medved.getZije());
            medved.setZije(false);
        assertFalse(medved.getZije());
        
        assertTrue(medved.jdeZabit());
        
        assertEquals(true, medved.getJmeno().equals("medvěd"));
        assertEquals(false, medved.getJmeno().equals("postava"));
        
        assertEquals(2, medved.getPocetZivotu());
        
        assertEquals(2, medved.getUtok());
           
        assertEquals(2, medved.getOdmena());
    }
}
