package ru.s100p.petsmongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.s100p.petsmongo.model.PetModel;

public interface PetModelRepository extends MongoRepository<PetModel, ObjectId> {

}
