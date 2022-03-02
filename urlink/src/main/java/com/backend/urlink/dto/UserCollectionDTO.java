package com.backend.urlink.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionDTO {
    private UserDTO userDTO;
    private CollectionDTO collectionDTO;
}
