package efimke.hackAssembler;

import java.text.ParseException;

public class Parser {

    private SymbolTable symbolTable;

    Parser(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    private Type instructionType;
    private Integer num;
    private String dest;
    private String comp;
    private String jmp;

    public void parse(String instruction) {
        // parse instruction
        if (instruction.startsWith("@")) {
            this.instructionType = Type.A;
            String value = instruction.substring(1);
            try {
                this.num = Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                if (this.symbolTable.contains(value)) {
                    this.num = this.symbolTable.getAddress(value);
                } else {
                    this.num = this.symbolTable.addEntry(value);
                }
            }

            this.dest = null;
            this.comp = null;
            this.jmp = null;
        } else {
            this.instructionType = Type.C;
            this.num = null;

            String[] tmp = instruction.split("=");
            if (tmp.length > 1) {
                this.dest = tmp[0];
                tmp = tmp[1].split(";");
            } else {
                this.dest = "null";
                tmp = tmp[0].split(";");
            }
            this.comp = tmp[0];
            if (tmp.length > 1) {
                this.jmp = tmp[1];
            } else {
                this.jmp = "null";
            }
        }
    }

    public String validateLine(String line, int address) {
        // trim whitespaces
        String trimmedInstruction = line.split("//")[0].trim();
        if (trimmedInstruction.isEmpty()) {
            return null;
        }
        if (trimmedInstruction.startsWith("(") && trimmedInstruction.endsWith(")")) {
            this.symbolTable.addEntry(trimmedInstruction.substring(1, trimmedInstruction.length() - 1), address);
            return null;
        }
        return trimmedInstruction;
    }

    public Type getInstructionType() {
        return instructionType;
    }

    public Integer getNum() {
        return num;
    }

    public String getDest() {
        return dest;
    }

    public String getComp() {
        return comp;
    }

    public String getJmp() {
        return jmp;
    }
}
