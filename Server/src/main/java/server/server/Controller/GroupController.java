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
import server.server.Model.Domain.Group;
import server.server.Model.Services.IGroupService;
import server.server.Model.Services.ISubjectService;
import server.server.auth.IAuthenticationFacade;
import server.server.utilities.Labels;
import server.server.utilities.errors.UserErrors;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/groupt")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    private ISubjectService subjectService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/all")
    public ArrayList<Group> all() {
        return groupService.getAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULEMANAGER')")
    @GetMapping(value = "/allBySubjectId")
    public ResponseEntity<List<Group>> allBySubjectId(@RequestParam("subjectId") long subjectId) {
        ResponseEntity<List<Group>> response;
        HttpHeaders headers = new HttpHeaders();
        ArrayList<String> errors2 = new ArrayList();
        Map<Labels, Object> groups = groupService.getAllBySubjectId(subjectId);
        List<Group> grs = (List<Group>) groups.get(Labels.objectReturn);
        response = new ResponseEntity<>(grs, null, HttpStatus.FOUND);
        
        return response;
    }

    @GetMapping(value = "/{groupId}")
    @ResponseBody
    public Group get(@PathVariable Long groupId) {
        Group group = new Group();
        group.setGroupId(groupId);
        return groupService.find(group);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULEMANAGER')")
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> add(@RequestBody @Valid Group group, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Group> response;
        ArrayList<String> errors2 = new ArrayList();

        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            response = new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> returns = groupService.save(group);
            errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Group grp = (Group) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || grp == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                response = new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
            } else {
                response = new ResponseEntity<>(group, null, HttpStatus.ACCEPTED);
            }
        }
        return response;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULEMANAGER')")
    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Group> update(@RequestBody @Valid Group group, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Group> response;
        ArrayList<String> errors2 = new ArrayList();

        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            response = new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = groupService.update(group);
            errors2 = (ArrayList<String>) update.get(Labels.errors);
            Group grp = (Group) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || grp == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                response = new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
            } else {
                response = new ResponseEntity<>(group, null, HttpStatus.ACCEPTED);
            }
        }
        return response;
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULEMANAGER')")
    @DeleteMapping
    @RequestMapping("/delete/{groupId}")
    public ResponseEntity<Group> delete(@PathVariable Long groupId) {

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Group> response;
        ArrayList<String> errors2 = new ArrayList();

        Map<Labels, Object> delete = groupService.delete(groupId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        Group grp = (Group) delete.get(Labels.objectReturn);
        if (grp != null) {
            response = new ResponseEntity<>(grp, null, HttpStatus.ACCEPTED);
        } else {
            headers.add(Labels.errors.name(), errors.toString());
            response = (new ResponseEntity<>(grp, headers, HttpStatus.NOT_MODIFIED));
        }
        return response;
    }
}
