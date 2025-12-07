package stream.techygrrrl.sf6mrcalc.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class SF6UtilsTest {

    @Test
    fun `calculateResetMr returns the correct MR after the rebalance`() {
        // Phase 9 data into Phase 10
        assertEquals(1532, SF6Utils.calculateResetMr(1605)) // Manon
        assertEquals(1486, SF6Utils.calculateResetMr(1454)) // Cammy
        assertEquals(1485, SF6Utils.calculateResetMr(1451)) // Elena
        assertEquals(1481, SF6Utils.calculateResetMr(1436)) // C. Viper
        assertEquals(1478, SF6Utils.calculateResetMr(1426)) // Juri
    }

    @Test
    fun `calculateWinnableMr returns the correct MR the player can win`() {
        assertEquals(5, SF6Utils.calculateWinnableMr(2096, 1938))
        assertEquals(5, SF6Utils.calculateWinnableMr(2085, 1949))
        assertEquals(5, SF6Utils.calculateWinnableMr(2090, 1944))
        assertEquals(8, SF6Utils.calculateWinnableMr(2095, 2078))
        assertEquals(7, SF6Utils.calculateWinnableMr(2103, 2070))
        assertEquals(2, SF6Utils.calculateWinnableMr(2096, 1736))
        assertEquals(2, SF6Utils.calculateWinnableMr(2098, 1734))
        assertEquals(7, SF6Utils.calculateWinnableMr(2100, 2057))
        assertEquals(7, SF6Utils.calculateWinnableMr(2107, 2050))
        assertEquals(2, SF6Utils.calculateWinnableMr(2110, 1722))
        assertEquals(9, SF6Utils.calculateWinnableMr(1483, 1520))
        assertEquals(7, SF6Utils.calculateWinnableMr(1520, 1483))
        assertEquals(3, SF6Utils.calculateWinnableMr(2114, 1851))
        assertEquals(3, SF6Utils.calculateWinnableMr(2101, 1864))
        assertEquals(3, SF6Utils.calculateWinnableMr(2104, 1861))
    }

    @Test
    fun `calculateLosableMr returns the correct MR the player can lose`() {
        assertEquals(11, SF6Utils.calculateLosableMr(2096, 1938))
        assertEquals(14, SF6Utils.calculateLosableMr(2110, 1722))
        assertEquals(9, SF6Utils.calculateLosableMr(1142, 1105))
        assertEquals(12, SF6Utils.calculateLosableMr(1330, 1133))
        assertEquals(4, SF6Utils.calculateLosableMr(1133, 1330))
        assertEquals(13, SF6Utils.calculateLosableMr(2114, 1851))
        assertEquals(13, SF6Utils.calculateLosableMr(2101, 1864))
        assertEquals(13, SF6Utils.calculateLosableMr(2104, 1861))
    }
}
