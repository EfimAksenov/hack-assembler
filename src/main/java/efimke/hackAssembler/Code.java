package efimke.hackAssembler;

import java.util.HashMap;

public class Code {

    private HashMap<String, String> destination = new HashMap<>();
    private HashMap<String, String> computation = new HashMap<>();
    private HashMap<String, String> jump = new HashMap<>();

    public Code() {
        this.destination.put("null", "000");
        this.destination.put("M", "001");
        this.destination.put("D", "010");
        this.destination.put("MD", "011");
        this.destination.put("A", "100");
        this.destination.put("AM", "101");
        this.destination.put("AD", "110");
        this.destination.put("AMD", "111");

        this.jump.put("null", "000");
        this.jump.put("JGT", "001");
        this.jump.put("JEQ", "010");
        this.jump.put("JGE", "011");
        this.jump.put("JLT", "100");
        this.jump.put("JNE", "101");
        this.jump.put("JLE", "110");
        this.jump.put("JMP", "111");

        this.computation.put("0", "101010");
        this.computation.put("1", "111111");
        this.computation.put("-1", "111010");
        this.computation.put("D", "001100");
        this.computation.put("A", "110000");
        this.computation.put("!D", "001101");
        this.computation.put("!A", "110001");
        this.computation.put("-D", "001111");
        this.computation.put("-A", "110011");
        this.computation.put("D+1", "011111");
        this.computation.put("A+1", "110111");
        this.computation.put("D-1", "001110");
        this.computation.put("A-1", "110010");
        this.computation.put("D+A", "000010");
        this.computation.put("D-A", "010011");
        this.computation.put("A-D", "000111");
        this.computation.put("D&A", "000000");
        this.computation.put("D|A", "010101");
    }

    public String createAInstruction(Integer num) {
        return "0" + String.format("%15s", Integer.toBinaryString(num)).replace(' ', '0');
    }

    public String createCInstruction(String dest, String comp, String jmp) {
        String binaryInstruction = "111";
        if (comp.contains("M")) {
            binaryInstruction+= "1";
            comp = comp.replace('M', 'A');
        } else {
            binaryInstruction+= "0";
        }
        return binaryInstruction + this.computation.get(comp) + this.destination.get(dest) + this.jump.get(jmp);
    }
}
