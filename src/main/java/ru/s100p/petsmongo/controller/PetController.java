package ru.s100p.petsmongo.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.s100p.petsmongo.client.SpringPetsFeignClient;
import ru.s100p.petsmongo.model.PetModel;
import ru.s100p.petsmongo.repository.PetModelRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/petsmongo/v1/pets")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PetController {

    PetModelRepository repository;
    SpringPetsFeignClient client;


    @GetMapping
    public List<PetModel> findAll() {
        log.debug("Got GET request for /pets");
        return repository.findAll();
    }

    @GetMapping("/add-pets-in-repo")
    public List<PetModel> addPetsInRepo() {
       return repository.saveAll(client.findAllPets()); //сохраняем в репозиторий данные, которые приходит от feignclient, который в свою очередь забирает их у стороннего сервиса - springpets
    }

    @PostMapping("/savePet")
    @ResponseStatus(HttpStatus.CREATED)
    public PetModel savePet(@RequestBody PetModel pet) {
        log.debug("Got POST request for /savePet with {}", pet);
        return repository.save(pet);
    }

    //id надо смотреть в БД !!!!
    @GetMapping("/findById/{id}")
    public Optional<PetModel> findById(@PathVariable String id /*не обязательно, можно оставить тип ObjectId и все работает*/) {
        log.debug("Got GET request for /pets with {}", id);
      return  repository.findById(new ObjectId(id)); /*и тут не создавать объект ObjectId из переданной стринги, а просто написать id и все работает*/
    }

    //тут поиск происходит по полю _id из передаваемого объекта, а не номера из БД
    @PutMapping("/upDatePet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PetModel upDatePet (@RequestBody PetModel pet) {
        log.debug("Got PUT request for /upDatePet with {}", pet);
        repository.findById(pet.get_id());
        return repository.save(pet);
    }

    //id надо смотреть в БД !!!!
    @DeleteMapping("/deletePetById")
    public String deletePetById(@RequestParam ObjectId id) {
        log.debug("Got DELETE request for /deletePetById with {}", id);
        repository.deleteById(id);
        return "Pet successfully deleted";
    }

    @DeleteMapping("/deleteAllPets")
    public String deleteAllPets() {
        log.debug("Got DELETE request for /deleteAllPets");
        repository.deleteAll();
        return "All pets successfully deleted";
    }




}
