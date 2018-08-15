package io.su0.test.soccer.persistence;

import io.su0.test.soccer.domain.Team;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TeamRepository extends MongoRepository<Team, String> {
}
