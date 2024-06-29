package ru.s100p.petsmongo.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.s100p.petsmongo.client.SpringPetsFeignClient;
import ru.s100p.petsmongo.exception.pet_exceptions.PetNotFoundException;
import ru.s100p.petsmongo.model.PetModel;
import ru.s100p.petsmongo.repository.PetModelRepository;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor //для внедрения petRepository;
@Service
public class PetService {

    String PET_NOT_FOUND = "Pet not found";
    PetModelRepository repository;
    SpringPetsFeignClient client;


    //для примера работы OpenFeign, по изначальной задумке не требуется.
    public List<PetModel> addPetsInRepo() {
        return repository.saveAll(client.findAllPets()); //сохраняем в репозиторий данные, которые приходит от feignclient, который в свою очередь забирает их у стороннего сервиса - springpets
    }


    //TODO перепилить метод на прием списка вариантов параметров по каждой позиции
    //мы сами реализуем этот метод, поэтому прописывать его в репозитории не надо
    public List<PetModel> getFilteredPets(String category, String status, Long placeInTop) {
        if (repository.count() > 0) {
            return repository
                    .findAll()
                    .stream()
                    .filter(petModel ->
                            petModel.getCategory().equals(category) &&
                                    petModel.getStatus().equals(status)&&
                                        petModel.getPlaceInTop()<=placeInTop)
                    .toList();
        } else
            throw new PetNotFoundException("No pets found");

    }


    public PetModel savePet(@RequestBody PetModel pet) {
        return repository.save(pet);
    }

    public List<PetModel> findAll() {
        if (repository.count() > 0) {
            return repository.findAll();
        } else
            throw new PetNotFoundException("No pets found");
    }

    //id надо смотреть в БД !!!!
    public PetModel findById(@PathVariable String id /*не обязательно, можно оставить тип ObjectId и все работает*/) {
        return repository
                .findById(new ObjectId(id)) /*и тут не создавать объект ObjectId из переданной стринги, а просто написать id и все работает*/
                .orElseThrow(() -> new PetNotFoundException(PET_NOT_FOUND));
    }

    public List<PetModel> findPetsByCategory(String category) {
        return repository.findPetsByCategory(category);
    }

    //тут поиск происходит по полю _id из передаваемого объекта, а не номера из БД
    public PetModel upDatePet(@RequestBody PetModel pet) {
        if (!repository.existsById(pet.get_id())) {
            throw new PetNotFoundException(PET_NOT_FOUND);
        }
        return repository.save(pet);
    }

    //id надо смотреть в БД при выдаче объекта после создания !!!!
    public String deletePetById(@RequestParam ObjectId id) {
        repository
                .findById(id)
                .orElseThrow(() -> new PetNotFoundException(PET_NOT_FOUND));
        repository.deleteById(id);
        return "Pet successfully deleted";
    }

    public void deleteAllPets() {
        if (repository.count() > 0) {
            repository.deleteAll();
        } else
            throw new PetNotFoundException("No pets found");
    }


}
