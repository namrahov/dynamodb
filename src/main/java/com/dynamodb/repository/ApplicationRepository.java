package com.dynamodb.repository;

import com.dynamodb.entity.ApplicationEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.EnableScanCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@EnableScan
@Repository
@EnableScanCount
public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, String> {

    List<ApplicationEntity> findByCreatedAtBetween(LocalDateTime begin, LocalDateTime end);
    long countByCourtName(String courtName);
    List<ApplicationEntity> findDistinctByCourtName();

    List<ApplicationEntity> findAllByOrderByRequestIdDesc();

    Page<ApplicationEntity> findByCourtName(String courtName, Pageable pageable);

}
