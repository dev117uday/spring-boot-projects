package com.backend.urlink.service;

import com.backend.urlink.dto.CollectionDTO;
import com.backend.urlink.dto.CollectionLinkDTO;
import com.backend.urlink.dto.LinkDTO;
import com.backend.urlink.exceptions.RestException;
import com.backend.urlink.models.Links;
import com.backend.urlink.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LinkService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public LinkDTO getLinkService(Long linkId) throws RestException {
        Optional<Links> linkOp = linkRepository.findById(linkId);
        if (linkOp.isEmpty()) {
            throw new RestException("link not found", HttpStatus.NOT_FOUND);
        }
        return LinkDTO.linksTolinkDTO(linkOp.get());
    }

    public LinkDTO saveLinkService(LinkDTO linkDTO) throws RestException {
        var linkTOSave = LinkDTO.linkDtoToLink(linkDTO);

        try {
            var savedLink = linkRepository.save(linkTOSave);
            return LinkDTO.linksTolinkDTO(savedLink);
        } catch (IllegalArgumentException e) {
            throw new RestException("link object cannot be null", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RestException("internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    public LinkDTO updateLinkService(Long linkId, LinkDTO linkDTO) throws RestException {

        Optional<Links> linkOp = linkRepository.findById(linkId);

        if (linkOp.isEmpty()) {
            throw new RestException("link not found", HttpStatus.NOT_FOUND);
        }

        var linkToUpdate = linkOp.get();
        linkToUpdate.setUrlLink(
                linkDTO.getUrlLink() == null ? linkToUpdate.getUrlLink() : linkDTO.getUrlLink()
        );
        linkToUpdate.setLinkDescription(
                linkDTO.getLinkDescription() == null ? linkToUpdate.getLinkDescription() : linkDTO.getLinkDescription()
        );
        var savedLink = linkRepository.save(linkToUpdate);
        return LinkDTO.linksTolinkDTO(savedLink);
    }


    public void deleteLinkService(Long linkId) throws RestException {
        try {
            linkRepository.deleteById(linkId);
        } catch (EmptyResultDataAccessException e) {
            throw new RestException("link not found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public CollectionLinkDTO getLinkWCollection(Long linkId) throws RestException {
        Optional<Links> linkOp = linkRepository.findById(linkId);
        if (linkOp.isEmpty()) {
            throw new RestException("link not found", HttpStatus.NOT_FOUND);
        }
        var link = linkOp.get();
        CollectionLinkDTO dto = new CollectionLinkDTO();
        dto.setCollectionDTO(CollectionDTO.collectionToCollectionDTO(link.getCollection()));
        dto.setLinkDTO(LinkDTO.linksTolinkDTO(link));
        return dto;
    }
}
