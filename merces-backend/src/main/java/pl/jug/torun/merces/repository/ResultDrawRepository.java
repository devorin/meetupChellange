package pl.jug.torun.merces.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.jug.torun.merces.model.DrawId;
import pl.jug.torun.merces.model.ResultDraw;

@RepositoryRestResource(collectionResourceRel = "resultDrawDictionary", path = "resultDrawDictionary")
public interface ResultDrawRepository extends CrudRepository<ResultDraw, DrawId> {

    Long countByEventIdAndAwardEventId(String eventId, String awardEventId);

}
