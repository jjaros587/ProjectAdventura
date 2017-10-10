/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy.
 */
package logika;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;



/*******************************************************************************
 * Testovací třída {@code BatohTest} slouží ke komplexnímu otestování
 * třídy {@link BatohTest}.
 *
 * @author  Jakub Jaroš
 * @version pro školní rok 2015/2016
 */
public class BatohTest
{
    //== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
    //== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================
    private Vec vec1_sebratelna;
    private Vec vec2;
    private Vec vec3;
    private Vec vec4;
    private Vec vec5;
    
    private Batoh batoh;

    //== PŘÍPRAVA A ÚKLID PŘÍPRAVKU ============================================

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
        vec1_sebratelna = new Vec("klic", true, false);
        vec2            = new Vec("truhla", true, false);
        vec3            = new Vec("sekyra", true, true);
        vec4            = new Vec("neco", true, false);
        vec5            = new Vec("neco2", true, false);
   
        batoh           = new Batoh();
    }

    //##########################################################################
    //== VLASTNÍ TESTY =========================================================

    @Test
    public void testVlozVec()
    {
        assertEquals(true, batoh.vlozVec(vec1_sebratelna));
        assertEquals(true, batoh.vlozVec(vec2));
        assertEquals(true, batoh.vlozVec(vec3));
        assertEquals(true, batoh.vlozVec(vec4));
        assertEquals(false, batoh.vlozVec(vec5));

        batoh.vyhodVec("truhla");
        assertFalse(batoh.obsahujeVec("truhla"));
        assertTrue(batoh.obsahujeVec("klic"));
  
        assertEquals(vec1_sebratelna, batoh.vyberVec("klic"));
        
        assertEquals("V batohu máš: sekyra neco klic ", batoh.veciVBatohu());
    }
}
