package com.backend.urlink.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionLinkDTO {
    private CollectionDTO collectionDTO;
    private LinkDTO linkDTO;
}