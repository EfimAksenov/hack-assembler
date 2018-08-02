package efimke.hackAssembler;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void emptyInstruction() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "  ";
        assertNull(parser.validateLine(instruction, 0));
    }

    @Test
    public void comment() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "// just some comment";
        assertNull(parser.validateLine(instruction, 0));
    }

    @Test
    public void AInstruction() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "@15";
        parser.parse(instruction);
        assertEquals(Type.A, parser.getInstructionType());
        assertEquals(15, parser.getNum().intValue());
    }

    @Test
    public void label() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "@R1";
        parser.parse(instruction);
        assertEquals(Type.A, parser.getInstructionType());
        assertEquals(1, parser.getNum().intValue());
    }

    @Test
    public void variable() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "@var";
        parser.parse(instruction);
        assertEquals(Type.A, parser.getInstructionType());
        assertEquals(16, parser.getNum().intValue());
    }
    @Test
    public void AInstructionWithComment() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "@15 // init value";
        assertEquals("@15", parser.validateLine(instruction, 0));
        parser.parse(parser.validateLine(instruction, 0));
        assertEquals(Type.A, parser.getInstructionType());
        assertEquals(15, parser.getNum().intValue());
    }

    @Test
    public void CInstruction() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "MD=M-1;JGT";
        parser.parse(instruction);
        assertEquals(Type.C, parser.getInstructionType());
        assertEquals("MD", parser.getDest());
        assertEquals("M-1", parser.getComp());
        assertEquals("JGT", parser.getJmp());
        instruction = "MD=M-1";
        parser.parse(instruction);
        assertEquals(Type.C, parser.getInstructionType());
        assertEquals("MD", parser.getDest());
        assertEquals("M-1", parser.getComp());
        assertEquals("null", parser.getJmp());
        instruction = "M-1";
        parser.parse(instruction);
        assertEquals(Type.C, parser.getInstructionType());
        assertEquals("null", parser.getDest());
        assertEquals("M-1", parser.getComp());
        assertEquals("null", parser.getJmp());
    }

    @Test
    public void CInstructionWithComment() {
        Parser parser = new Parser(new SymbolTable());
        String instruction = "MD=M-1;JGT // some comment";
        assertEquals("MD=M-1;JGT", parser.validateLine(instruction, 0));
        parser.parse(parser.validateLine(instruction, 0));
        assertEquals(Type.C, parser.getInstructionType());
        assertEquals("MD", parser.getDest());
        assertEquals("M-1", parser.getComp());
        assertEquals("JGT", parser.getJmp());
    }
}
