package efimke.hackAssembler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CodeTest
{
    @Test
    public void getAInstruction()
    {
        Code code = new Code();
        assertEquals("0000000000000011", code.createAInstruction(3));
    }
    @Test
    public void getCInstruction()
    {
        Code code = new Code();
        assertEquals("1110110000001111", code.createCInstruction("M", "A", "JMP"));
        assertEquals("1111110000001111", code.createCInstruction("M", "M", "JMP"));
        assertEquals("1110110000000111", code.createCInstruction("null", "A", "JMP"));
        assertEquals("1110110000000000", code.createCInstruction("null", "A", "null"));
    }
}
