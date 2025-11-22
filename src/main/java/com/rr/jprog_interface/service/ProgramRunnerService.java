package com.rr.jprog_interface.service;

import org.springframework.stereotype.Service;
import com.rr.jprog_interface.model.Program;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Service
public class ProgramRunnerService {

    private final List<Program> programs = List.of(
            new Program(1, "Adder", "Adds two numbers"),
            new Program(2, "Greeter", "Greets a person"),
            new Program(3, "Factorial", "Computes factorial")
    );

    public List<Program> listPrograms() {
        return programs;
    }

    public String executeProgram(int id, String inputPayload) {
        switch (id) {
            case 1: return runAdder(inputPayload);
            case 2: return runGreeter(inputPayload);
            case 3: return runFactorial(inputPayload);
            default: throw new IllegalArgumentException("Program not found");
        }
    }

    private String runAdder(String inputJson) {
        try {
            JsonNode obj = new ObjectMapper().readTree(inputJson);
            int a = obj.get("a").asInt();
            int b = obj.get("b").asInt();
            return "Sum=" + (a + b);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON input", e);
        }
    }

    private String runGreeter(String inputJson) {
        try {
            JsonNode obj = new ObjectMapper().readTree(inputJson);
            String name = obj.get("name").asText();
            return "Hello, " + name + "!";
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON input", e);
        }
    }

    private String runFactorial(String inputJson) {
        try {
            JsonNode obj = new ObjectMapper().readTree(inputJson);
            int n = obj.get("n").asInt();
            int result = 1;
            for (int i = 1; i <= n; i++) result *= i;
            return "Factorial=" + result;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON input", e);
        }
    }
}
