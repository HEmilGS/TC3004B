package com.example.app.factory;

import java.util.List;

import com.example.app.model.FormFactory;
import com.example.app.model.GenericField;

public class GuestFormFactory implements FormFactory {
    @Override
    public List<FormField> createFormFields() {
        // Se crea y retorna una lista inmutable utilizando List.of()
        // La lista contiene dos objetos de tipo GenericField que representan los campos de un formulario
        return List.of(
            new GenericField("text", "Nickname", false),
            new GenericField("text", "Favorite Color", false),
            new GenericField("email", "Contact Email", true)
        );
    }
}
