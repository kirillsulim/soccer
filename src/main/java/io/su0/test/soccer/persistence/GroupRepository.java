package io.su0.test.soccer.persistence;

import io.su0.test.soccer.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface GroupRepository extends MongoRepository<Group, String> {
}
