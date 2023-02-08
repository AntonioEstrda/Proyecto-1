/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.Program;
import server.server.Model.Services.IDepartmentService;
import server.server.Model.Services.IProgramService;
import server.server.auth.IAuthenticationFacade;
import server.server.utilities.Labels;
import server.server.utilities.errors.UserErrors;

/**
 *
 * @author Fernando
 */
@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/Program")
public class ProgramController {

    @Autowired
    public IProgramService programService;

    @Autowired
    private IDepartmentService depService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @GetMapping(value = "/all")
    public ArrayList<Program> all() {
        return programService.getAll();
    }

    @GetMapping(value = "/allByDepartmentId")
    public ResponseEntity<List<Program>> all(@RequestParam("departmentId") long departmentId) {
        ResponseEntity<List<Program>> response;
        HttpHeaders headers = new HttpHeaders();
        ArrayList<String> errors2 = new ArrayList();

        Map<Labels, Object> returns = programService.getAll(departmentId);
        List<Program> get = (List<Program>) returns.get(Labels.objectReturn);
        response = new ResponseEntity<>(get, null, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Program get(@PathVariable Long id) {
        Program env = new Program();
        env.setProgramId(id);
        return programService.find(env);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Program> add(@RequestBody @Valid Program program, Errors errors) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Program> response;
        ArrayList<String> errors2 = new ArrayList();

        if (errors.hasErrors()) {
            headers.add(Labels.errors.name(), Utility.setErrors(errors).toString());
            return new ResponseEntity<>(program, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = programService.save(program);
            errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Program prg = (Program) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || prg == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(program, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(program, null, HttpStatus.ACCEPTED);

            }
        }
    }


    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Program> update(@RequestBody Program program, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Program> response;
        ArrayList<String> errors2 = new ArrayList();

        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            response = new ResponseEntity<>(program, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = programService.update(program);
            errors2 = (ArrayList<String>) update.get(Labels.errors);
            Program prg = (Program) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || prg == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(program, headers, HttpStatus.NOT_MODIFIED);
            }
            response = new ResponseEntity<>(program, null, HttpStatus.ACCEPTED);
        }
        return response;
    }

    @DeleteMapping
    @RequestMapping("/delete/{ProgramId}")
    public ResponseEntity<Program> delete(@PathVariable Long ProgramId
    ) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Program> response;
        ArrayList<String> errors2 = new ArrayList();

        Map<Labels, Object> delete = programService.delete(ProgramId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        Program prg = (Program) delete.get(Labels.objectReturn);
        if (prg != null) {
            response = new ResponseEntity<>(prg, null, HttpStatus.ACCEPTED);
        } else {
            headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            response = (new ResponseEntity<>(prg, headers, HttpStatus.NOT_MODIFIED));
        }
        return response;
    }
}
