package com.task7.task7.repository;

import com.task7.task7.domain.Modification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModificationRepository extends CrudRepository<Modification, Long> {
}
