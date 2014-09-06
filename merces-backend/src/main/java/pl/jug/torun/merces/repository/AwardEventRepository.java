package pl.jug.torun.merces.repository;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.jug.torun.merces.model.AwardEvent;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "awardEvent", path = "awardEvent")
public interface AwardEventRepository extends CrudRepository<AwardEvent, ObjectId> {

    List<AwardEvent> findByEventId(@Param("eventId") String eventId);

}
