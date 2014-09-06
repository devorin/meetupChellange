package pl.jug.torun.merces.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.jug.torun.merces.model.Award;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "award", path = "award")
public interface AwardRepository extends CrudRepository<Award, Integer> {

    List<Award> findByEventId(@Param("eventId") String eventId);

}
