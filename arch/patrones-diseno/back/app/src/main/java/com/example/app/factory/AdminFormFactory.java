package com.example.app.factory;

import java.util.List;

import com.example.app.model.FormFactory;
import com.example.app.model.GenericField;

public class AdminFormFactory implements FormFactory {
    @Override
    public List<FormField> createFormFields() {

        return List.of(
            new GenericField("text", "Username", true),

            new GenericField("password", "Admin Key", true),

            new GenericField("email", "Admin Email", true)
        );
    }
}
