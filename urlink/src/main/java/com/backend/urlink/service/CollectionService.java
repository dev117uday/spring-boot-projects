package com.backend.urlink.service;

import com.backend.urlink.dto.CollectionDTO;
import com.backend.urlink.dto.UserCollectionDTO;
import com.backend.urlink.dto.UserDTO;
import com.backend.urlink.exceptions.RestException;
import com.backend.urlink.models.UrlCollections;
import com.backend.urlink.models.User;
import com.backend.urlink.repository.CollectionRepository;
import com.backend.urlink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository, UserRepository userRepository) {
        this.collectionRepository = collectionRepository;
        this.userRepository = userRepository;
    }

    public List<CollectionDTO> getAllCollectionByUserService(Long userId) {
        List<UrlCollections> lstCollection = collectionRepository.findByUser(userId);
        List<CollectionDTO> collectionDTOList = new ArrayList<>();
        for (var element : lstCollection) {
            collectionDTOList.add(CollectionDTO.collectionToCollectionDTO(element));
        }

        return collectionDTOList;
    }

    public CollectionDTO getCollectionByIdService(Long collectionId) throws RestException {
        Optional<UrlCollections> collectionOp = collectionRepository.findById(collectionId);

        if (collectionOp.isEmpty()) {
            throw new RestException("collection not found", HttpStatus.NOT_FOUND);
        }
        return CollectionDTO.collectionToCollectionDTO(collectionOp.get());
    }

    public CollectionDTO saveCollectionService(CollectionDTO collection) throws RestException {

        Optional<User> userOp = userRepository.findById(collection.getUserId());
        if (userOp.isEmpty()) {
            throw new RestException("user not found", HttpStatus.BAD_REQUEST);
        }

        UrlCollections collectionToSave = CollectionDTO.collectionDTOToUrlCollection(collection);
        var savedCollection = collectionRepository.save(collectionToSave);
        return CollectionDTO.collectionToCollectionDTO(savedCollection);
    }

    @Transactional
    public CollectionDTO updateCollectionService(CollectionDTO collection, Long collectionId) throws RestException {

        Optional<UrlCollections> collectionOp = collectionRepository.findById(collectionId);

        if (collectionOp.isEmpty()) {
            throw new RestException("collection not found", HttpStatus.NOT_FOUND);
        }

        var collectionToUpdate = collectionOp.get();

        collectionToUpdate.setCollectionName(collection.getCollectionName() == null ? collectionToUpdate.getCollectionName() : collection.getCollectionName());

        collectionToUpdate.setCollectionDescription(collection.getCollectionDescription() == null ? collectionToUpdate.getCollectionDescription() : collection.getCollectionDescription());

        var savedCollection = collectionRepository.save(collectionToUpdate);
        return CollectionDTO.collectionToCollectionDTO(savedCollection);
    }

    public void deleteCollectionService(Long collectionId) throws RestException {
        try {
            collectionRepository.deleteById(collectionId);
        } catch (EmptyResultDataAccessException e) {
            throw new RestException("collection not found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public UserCollectionDTO getCollectionWUserService(Long collectionId) throws RestException {
        Optional<UrlCollections> collectionOp = collectionRepository.findById(collectionId);
        if (collectionOp.isEmpty()) {
            throw new RestException("collection not found", HttpStatus.NOT_FOUND);
        }
        UserCollectionDTO dto = new UserCollectionDTO();
        var collection = collectionOp.get();
        dto.setCollectionDTO(CollectionDTO.collectionToCollectionDTO(collection));
        dto.setUserDTO(UserDTO.userToUserDTO(collection.getUser()));
        return dto;
    }

}
