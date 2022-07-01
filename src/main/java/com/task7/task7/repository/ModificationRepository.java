package com.task7.task7.repository;

import com.task7.task7.domain.Modification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModificationRepository extends CrudRepository<Modification, Long> {

    @Query(nativeQuery = true, value = "select * from modification " +
            "where modification.name = :nameModification " +
            "and modification.period_begin >= :periodBegin " +
            "and modification.period_end <= :periodEnd ")
    List<Modification> findByNameModification(@Param("nameModification") String nameModification,
                                              @Param("periodBegin") int periodBegin, @Param("periodEnd") int periodEnd);

    @Query(nativeQuery = true, value = "select * from modification " +
            "where modification.period_begin >= :periodBegin " +
            "and modification.period_end <= :periodEnd ")
    List<Modification> findByDate(@Param("periodBegin") int periodBegin, @Param("periodEnd") int periodEnd);
}
