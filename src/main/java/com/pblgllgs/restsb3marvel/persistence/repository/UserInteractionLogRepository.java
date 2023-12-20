package com.pblgllgs.restsb3marvel.persistence.repository;
/*
 *
 * @author pblgl
 * Created on 19-12-2023
 *
 */

import com.pblgllgs.restsb3marvel.persistence.entity.UserInteractionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionLogRepository extends JpaRepository<UserInteractionLog, Long> {
    Page<UserInteractionLog> findByUsername(Pageable pageable, String username);
}
