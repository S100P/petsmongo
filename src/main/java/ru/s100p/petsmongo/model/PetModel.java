package ru.s100p.petsmongo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "pets") //Document аналог Entity, обозначает сущность для таблицы, collection отражает в какую коллекцию будут складываться эти документы (предварительно ее надо создать в монго)
public class PetModel {
    @Id
    ObjectId _id; //можно и Long и String, но это так - дефолт для монго, + не забыть нижнюю черту _id
    String name;
    String category;
    String status;
    Long placeInTop;
    List<String> photoUrls;
    String ownerName;
    Long ownerId;
}
