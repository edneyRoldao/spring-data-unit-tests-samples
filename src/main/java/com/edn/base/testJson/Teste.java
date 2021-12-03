package com.edn.base.testJson;

import com.edn.base.models.EntidadeTaxa;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class Teste {

    public static void main(String[] args) {
        EntidadeTaxa taxa = EntidadeTaxa.builder()
                .id(10L)
                .idTipoTaxa(45L)
                .active(true)
                .nome("seja qual for")
                .build();

        String result = objectToJson(taxa);

        System.out.println(result);
    }

    @SneakyThrows
    private static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


}
