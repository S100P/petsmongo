package ru.s100p.petsmongo.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.s100p.petsmongo.client.SpringPetsFeignClient;
import ru.s100p.petsmongo.model.PetModel;
import ru.s100p.petsmongo.repository.PetModelRepository;

import java.util.List;


@RestController
@RequestMapping("/petsmongo/v1")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetController {

    PetModelRepository repository;
    SpringPetsFeignClient client;


    @GetMapping("/pets")
    public List<PetModel> findAll() {
        return repository.findAll();
    }

    @GetMapping("/add-pets-in-repo")
    public List<PetModel> init() {
       return repository.saveAll(client.findAllPets()); //сохраняем в репозиторий данные, которые приходит от feignclient, который в свою очередь забирает их у стороннего сервиса - springpets
    }



}
