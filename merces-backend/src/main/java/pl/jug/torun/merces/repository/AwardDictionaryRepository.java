package pl.jug.torun.merces.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.jug.torun.merces.model.AwardDictionary;

@RepositoryRestResource(collectionResourceRel = "awardDictionary", path = "awardDictionary")
public interface AwardDictionaryRepository extends CrudRepository<AwardDictionary, ObjectId> {

}
