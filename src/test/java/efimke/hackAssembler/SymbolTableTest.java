package efimke.hackAssembler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SymbolTableTest
{
    @Test
    public void tests()
    {
        SymbolTable st = new SymbolTable();
        assertEquals(0, st.getAddress("R0").intValue());
        st.addEntry("myEntry", 1234);
        assertTrue(st.contains("myEntry"));
        assertEquals(1234, st.getAddress("myEntry").intValue());
    }
}
