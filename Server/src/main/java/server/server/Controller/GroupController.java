/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.Group;
import server.server.Model.Services.IGroupService;
import server.server.utilities.Labels;

/**
 *
 * @author Fernando
 */
@RestController
@RequestMapping("/groupt")
public class GroupController {
    @Autowired
    public IGroupService groupService;

    @GetMapping(value = "/all")
    public ArrayList<Group> all() {
        return groupService.getAll();
    }

    @GetMapping(value = "/{groupId}")
    @ResponseBody
    public Group get(@PathVariable Long groupId) {
        Group group = new Group();
        group.setGroupId(groupId);
        return groupService.find(group);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> add(@RequestBody @Valid Group group, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
        }else {
            Map<Labels, Object> returns = groupService.save(group);
            ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
            Group grp = (Group) returns.get(Labels.objectReturn);
            if (!errors2.isEmpty() || grp == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
            } else {
                return new ResponseEntity<>(group, null, HttpStatus.ACCEPTED);
            }
        }
    }

    @PutMapping
    @RequestMapping("/update")
    public ResponseEntity<Group> update(@RequestBody @Valid Group group, Errors errors) {
        HttpHeaders headers = new HttpHeaders();
        if (errors.hasErrors()) {
            ArrayList<String> setErrors = Utility.setErrors(errors);
            headers.add(Labels.errors.name(), setErrors.toString());
            return new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
        } else {
            Map<Labels, Object> update = groupService.update(group);
            ArrayList<String> errors2 = (ArrayList<String>) update.get(Labels.errors);
            Group grp = (Group) update.get(Labels.objectReturn);
            if (!errors2.isEmpty() || grp == null) {
                headers.add(Labels.errors.name(), errors2.toString());
                return new ResponseEntity<>(group, headers, HttpStatus.NOT_MODIFIED);
            }
            return new ResponseEntity<>(group, null, HttpStatus.ACCEPTED);
        }
        
    }

    @DeleteMapping
    @RequestMapping("/delete/{groupId}")
    public ResponseEntity<Group> delete(@PathVariable Long groupId) {

        Map<Labels, Object> delete = groupService.delete(groupId);
        ArrayList<String> errors = (ArrayList<String>) delete.get(Labels.errors);
        Group grp = (Group) delete.get(Labels.objectReturn);
        if (grp != null) {
            return new ResponseEntity<>(grp, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            return (new ResponseEntity<>(grp, headers, HttpStatus.NOT_MODIFIED));
        }
    }
}
