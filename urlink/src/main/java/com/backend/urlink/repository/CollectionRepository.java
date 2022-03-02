package com.backend.urlink.repository;

import com.backend.urlink.models.UrlCollections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository<UrlCollections, Long> {

    @Query(
            value = "select * from tbl_url_collections where user_id = ?1",
            nativeQuery = true
    )
    List<UrlCollections> findByUser(Long userId);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_url_collections set collection_name = ?1 , collection_description = ?2 where collection_id = ?3",
            nativeQuery = true
    )
    Integer updateCollection(String collectionName, String collectionDescription, Long collectionId);

}
