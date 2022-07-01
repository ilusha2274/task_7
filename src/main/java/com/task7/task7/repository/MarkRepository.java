package com.task7.task7.repository;

import com.task7.task7.domain.Mark;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {

    @Query(nativeQuery = true, value = "select * from mark " +
            "join model_auto " +
            "on mark.id = model_auto.mark_id " +
            "join modification " +
            "on model_auto.id = modification.model_id " +
            "where mark.name = :nameMark " +
            "and modification.period_begin >= :periodBegin " +
            "and modification.period_end <= :periodEnd ")
    List<Mark> findByNameMark(@Param("nameMark") String nameMark, @Param("periodBegin") int periodBegin, @Param("periodEnd") int periodEnd);
}
