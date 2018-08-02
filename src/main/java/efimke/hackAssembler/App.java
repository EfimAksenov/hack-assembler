package efimke.hackAssembler;

import java.io.*;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();
        Parser parser = new Parser(symbolTable);
        Code code = new Code();

        try (BufferedReader asm =
                     new BufferedReader(new FileReader(new File(args[0])));
             FileWriter hack =
                     new FileWriter(new File(args[0].replace(".asm", ".hack")))) {
            ArrayList<String> program = new ArrayList<>();
            int lineNumber = 0;
            String line;
            while ((line = asm.readLine()) != null) {
                String validatedLine;
                if ((validatedLine = parser.validateLine(line, lineNumber)) != null) {
                    lineNumber++;
                    program.add(validatedLine);
                }
            }
            for (String programLine: program) {
                parser.parse(programLine);
                if (parser.getInstructionType() == Type.A) {
                    hack.write(code.createAInstruction(parser.getNum()));
                } else if (parser.getInstructionType() == Type.C) {
                    hack.write(code.createCInstruction(parser.getDest(), parser.getComp(), parser.getJmp()));
                }
                hack.write('\n');
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
    }
}
