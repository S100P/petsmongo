package ru.s100p.petsmongo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.s100p.petsmongo.model.PetModel;

import java.util.List;


/**Интерфейс служит для обращения на сторонний сервис через эту апи (OpenFeign). Программа будет обращаться к сервису, забирать сгенерированные данные и класть в нашу коллекцию внутри БД**/
@Service
// мы объявляем клиента Feign с помощью аннотации @FeignClient
@FeignClient(name="springpets",url = "http://localhost:8080") // Аргумент name (он же value), передаваемый в аннотации @FeignClient, является обязательным произвольным именем клиента, а с аргументом url мы указываем базовый URL-адрес API.
public interface SpringPetsFeignClient {


   //Необходимо указать точные сигнатуры методов контроллеров сервиса, к которому мы обращаемся, в том числе аннотации и эндпоинты.

    @GetMapping("/api/v1/pets")
    List<PetModel> findAllPets();

    @GetMapping("/api/v1/pets/{id}")
    PetModel findById(@PathVariable("id") Long id);

}
