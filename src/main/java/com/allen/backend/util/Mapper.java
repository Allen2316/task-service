package com.allen.backend.util;

import org.modelmapper.ModelMapper;

public class Mapper {
    private Mapper() {
        throw new IllegalThreadStateException("No se puede instanciar un utilitario");
    }

    public static ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
