package com.rr.jprog_interface.controller;

import com.rr.jprog_interface.model.Program;
import com.rr.jprog_interface.service.ProgramRunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/programs")
public class ProgramController {

    private final ProgramRunnerService programRunnerService;

    @Autowired
    public ProgramController(ProgramRunnerService programRunnerService) {
        this.programRunnerService = programRunnerService;
    }

    @GetMapping
    public List<Program> listPrograms() {
        return programRunnerService.listPrograms();
    }

    @PostMapping("/{id}/execute")
    public String executeProgram(@PathVariable int id, @RequestBody String inputPayload) {
        return programRunnerService.executeProgram(id, inputPayload);
    }

}
