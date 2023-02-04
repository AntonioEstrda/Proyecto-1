/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.server.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import server.server.Controller.Utilities.Utility;
import server.server.Model.Domain.Event;
import server.server.Model.Services.IEventService;
import server.server.utilities.Labels;

/**
 *
 * @author anmon
 */
@RestController
@RequestMapping("/Event")
public class EventController {

    @Autowired
    private IEventService evtService;

    /**
     * Find an event by code or id
     *
     * @param params
     * @return
     */
    @GetMapping(value = "/find")
    @ResponseBody
    public ResponseEntity<Event> get(@RequestParam Map<String, String> params) {
        ResponseEntity responseEntity = new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        Map<Labels, Object> returns = null;
        if (params.keySet().size() == 1) {
            if (params.containsKey("id")) {
                Long id = Long.parseLong(params.get("id"));
                returns = evtService.findbyId(id);
            }
            if (params.containsKey("code")) {
                String code = params.get("code");
                returns = evtService.findbyCode(code);
            }
            if (returns != null) {
                Event ev = (Event) returns.get(Labels.objectReturn);
                if (ev != null) {
                    responseEntity = new ResponseEntity<>(ev, null, HttpStatus.FOUND);
                }
            }
        }

        if (returns == null) {
            responseEntity = new ResponseEntity<>(null, null, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/add")
    public ResponseEntity<Event> add(@RequestBody @Valid Event event, Errors errors) {

        Map<Labels, Object> returns = evtService.create(event);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        Event event2 = (Event) returns.get(Labels.objectReturn);
        ResponseEntity responseEntity;

        if (event2 != null) {
            responseEntity = new ResponseEntity<>(event2, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors);
            headers.add(Labels.errors.name(), errors2.toString());
            responseEntity = new ResponseEntity<>(event, headers, HttpStatus.NOT_MODIFIED);
        }

        return responseEntity;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/update")
    public ResponseEntity<Event> update(@RequestBody Event event, Errors errors) {
        ResponseEntity<Event> responseEntity;
        Map<Labels, Object> returns = evtService.update(event);
        ArrayList<String> errors2 = (ArrayList<String>) returns.get(Labels.errors);
        Event event2 = (Event) returns.get(Labels.objectReturn);

        if (event2 != null) {
            responseEntity = new ResponseEntity<>(event2, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            ArrayList<String> setErrors = Utility.setErrors(errors);
            errors2.addAll(setErrors);
            headers.add(Labels.errors.name(), errors2.toString());
            responseEntity = new ResponseEntity<>(event, headers, HttpStatus.NOT_MODIFIED);
        }

        return responseEntity;
    }

    @DeleteMapping
    @RequestMapping("/delete")
    public ResponseEntity<Event> delete(@RequestParam("id") long id, @RequestParam("departmentId") long departmentId) {

        ResponseEntity<Event> responseEntity;
        Map<Labels, Object> returns = evtService.delete(id, departmentId);
        ArrayList<String> errors = (ArrayList<String>) returns.get(Labels.errors);
        Event event = (Event) returns.get(Labels.objectReturn);
        if (event != null) {
            responseEntity = new ResponseEntity<>(event, null, HttpStatus.ACCEPTED);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add(Labels.errors.name(), errors.toString());
            responseEntity = (new ResponseEntity<>(event, headers, HttpStatus.NOT_MODIFIED));
        }
        return responseEntity;
    }

    @ResponseBody
    @GetMapping(value = "/findByDepartmentOrProgram")
    public ResponseEntity<List<Event>> findByDepartmentOrProgram(@RequestParam("departmentId") List<Long> departmentId) {
        ResponseEntity<List<Event>> responseEntity = new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        Map<Labels, Object> returns = evtService.findByDeparmentId(departmentId);
        List<Event> events = null;
        if (returns != null) {
            events = (List<Event>) returns.get(Labels.objectReturn);
        }
        if (events != null && !events.isEmpty()) {
            responseEntity = new ResponseEntity<>(events, null, HttpStatus.FOUND);
        }
        return responseEntity;
    }

    @ResponseBody
    @GetMapping(value = "/findByDepartmentAndTypes")
    public ResponseEntity<List<Event>> findByDepartmentAndTypes(@RequestParam("departmentId") long departmentId,
            @RequestParam("type") Optional<List<String>> types) {
        ResponseEntity responseEntity = new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);

        List<String> types2 = null;
        if (types.isPresent() && types.isEmpty()) {
            types2 = types.get();
        }
        Map<Labels, Object> returns = evtService.findAllByDepartmentAndEvenType(departmentId, types2);
        List<Event> evts = (List<Event>) returns.get(Labels.objectReturn);
        if (evts != null && !evts.isEmpty()) {
            responseEntity = new ResponseEntity<>(evts, null, HttpStatus.OK);
        }
        return responseEntity;
    }

}
