package com.edn.base.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test")
public class Pessoa {

    @Id
    private String id;
    private String idade;
    private String nome;

}
