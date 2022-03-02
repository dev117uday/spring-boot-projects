package com.backend.urlink.tests;

import com.backend.urlink.models.UrlCollections;
import com.backend.urlink.models.User;
import com.backend.urlink.repository.CollectionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
public class CollectionTest {

    private final CollectionRepository collectionRepository;

    @Autowired
    public CollectionTest(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    @Test
    public void saveCollection() {
        var collection1 = UrlCollections.builder().collectionName("first collection").collectionDescription("testing 1 2 3").user(User.builder().userId(1L).build()).build();
        var collection2 = UrlCollections.builder().collectionName("second collection").collectionDescription("testing ... ").user(User.builder().userId(2L).build()).build();

        collectionRepository.save(collection1);
        collectionRepository.save(collection2);
    }


    @Test
    @Transactional
    public void getCollectionById() {
        var collection = collectionRepository.findById(1L);
        System.out.println(collection);
        System.out.println(collection.get().getUser());
    }

    @Test
    public void getCollection() {
        var lstCollection = collectionRepository.findAll();
        System.out.println(lstCollection);
    }

    @Test
    public void updateCollection() {
        Optional<UrlCollections> collectionOp = collectionRepository.findById(1L);
        if (collectionOp.isPresent()) {
            var done = collectionRepository.updateCollection("updated collection 1", "update collection description 1", 1L);
            if (done == 1) {
                System.out.println("update completed");
            } else {
                System.out.println("collection not found");
            }
        }
    }

    @Test
    public void deleteCollectionTest() {
        var collection = UrlCollections.builder().collectionName("third collection").collectionDescription("testing ... ").user(User.builder().userId(2L).build()).build();

        var savedCollection = collectionRepository.save(collection);
        collectionRepository.deleteById(savedCollection.getCollectionId());
    }
}
