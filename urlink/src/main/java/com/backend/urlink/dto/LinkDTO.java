package com.backend.urlink.dto;

import com.backend.urlink.models.Links;
import com.backend.urlink.models.UrlCollections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {

    private Long linkId;
    private String urlLink;
    private String linkDescription;
    private Long collectionId;

    public static Links linkDtoToLink(LinkDTO linkDTO) {
        return new Links(
                linkDTO.getLinkId(),
                linkDTO.getUrlLink(),
                linkDTO.getLinkDescription(),
                UrlCollections.builder().collectionId(linkDTO.getCollectionId()).build()
        );
    }

    public static LinkDTO linksTolinkDTO(Links link) {
        return new LinkDTO(
                link.getLinkId(),
                link.getUrlLink(),
                link.getLinkDescription(),
                link.getCollection().getCollectionId()
        );
    }
}
