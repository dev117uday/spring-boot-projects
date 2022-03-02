package com.backend.urlink.tests;

import com.backend.urlink.models.Links;
import com.backend.urlink.models.UrlCollections;
import com.backend.urlink.repository.LinkRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LinkTest {


    private final LinkRepository linkRepository;

    @Autowired
    public LinkTest(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

   @Test
   public void saveLink() {
       var link1 = Links.builder().urlLink("google.com").linkDescription("address to google.com")
               .collection(UrlCollections.builder().collectionId(3L).build()).build();

       var link2 = Links.builder().urlLink("microsoft.com").linkDescription("address to microsoft.com")
               .collection(UrlCollections.builder().collectionId(3L).build()).build();

       var link3 = Links.builder().urlLink("microsoft.com").linkDescription("address to microsoft.com")
               .collection(UrlCollections.builder().collectionId(4L).build()).build();

       linkRepository.save(link1);
       linkRepository.save(link2);
       linkRepository.save(link3);
   }

}
