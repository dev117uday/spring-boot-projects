package com.backend.urlink.dto;

import com.backend.urlink.models.Links;
import com.backend.urlink.models.UrlCollections;
import com.backend.urlink.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDTO {

    private Long collectionId;
    private String collectionName;
    private String collectionDescription;
    private Long userId;

    public static CollectionDTO collectionToCollectionDTO(UrlCollections collection) {
        return new CollectionDTO(
                collection.getCollectionId(),
                collection.getCollectionName(),
                collection.getCollectionDescription(),
                collection.getUser().getUserId()
        );
    }

    public static UrlCollections collectionDTOToUrlCollection(CollectionDTO collection) {
        return new UrlCollections(
                collection.getCollectionId(),
                collection.getCollectionName(),
                collection.getCollectionDescription(),
                User.builder().userId(collection.userId).build(),
                List.of(Links.builder().linkId(collection.collectionId).build())
        );
    }
}
