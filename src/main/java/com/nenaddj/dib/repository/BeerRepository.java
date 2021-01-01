package com.nenaddj.dib.repository;

import com.nenaddj.dib.model.entity.BeerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeerRepository extends JpaRepository<BeerEntity, Long> {
    @Override
    Page<BeerEntity> findAll(Pageable pageable);

    @Override
    Optional<BeerEntity> findById(Long aLong);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
