package com.interfazgrafica.version1.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interfazgrafica.version1.services.FirebaseService;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/save/{name}")
    public String saveData(
            @PathVariable String name, // Nombre que se envía en la URL
            @RequestBody Object data   // Datos a guardar (puede ser un Map, un POJO, etc.)
    ) throws Exception {
        String collectionName = "users"; // Colección configurada internamente
        firebaseService.saveData(collectionName, data); // El ID se genera automáticamente
        return "Data saved successfully for user: " + name;
    }


    // @GetMapping("/test-save/{name}")
    // public String testSaveData(
    //         @PathVariable String name, // Nombre que se envía en la URL
    //         @RequestParam int age // Edad como parámetro de la URL
    // ) throws Exception {
    //     String collectionName = "users"; // Colección configurada internamente

    //     // Crear un mapa para simular el cuerpo de la solicitud
    //     Map<String, Object> data = new HashMap<>();
    //     data.put("name", name); // Guardar el nombre en el mapa
    //     data.put("age", age); // Guardar la edad en el mapa

    //     firebaseService.saveData(collectionName, data); // El ID se genera automáticamente
    //     return "Data saved successfully for user: " + name;
    // }

    @GetMapping("/test-save/{name}")
    public String saveTiket(
            @PathVariable String name,
            @RequestParam String responsible,
            @RequestParam String deadline // formato esperado: yyyy-MM-dd
    ) throws Exception {
        String collectionName = "tiket";

        Map<String, Object> data = new HashMap<>();
        data.put("nombre", name);
        data.put("responsable", responsible);
        data.put("fecha de entrega", deadline); // puedes usar "fecha_entrega" si prefieres sin espacios

        firebaseService.saveData(collectionName, data);
        return "Tiket creado con éxito para: " + name;
    }

}


