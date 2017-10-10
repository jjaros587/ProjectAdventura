/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */
package logika;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code HracTest} slouží ke komplexnímu otestování
 * třídy {@link HracTest}.
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class HracTest
{
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Hrac hrac;
    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        hrac = new Hrac();
    }

    //##########################################################################
    //== VLASTNÍ TESTY =========================================================
    //
 
    @Test
    public void testHrac()
    {
        hrac.setZivoty(8);
        assertEquals(8, hrac.getZivoty());
        
        hrac.setPenize(6);
        assertEquals(6, hrac.getPenize());
    }
}
