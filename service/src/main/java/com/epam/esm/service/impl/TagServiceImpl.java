package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.tag.TagCreationDTOMapper;
import com.epam.esm.mapper.tag.TagResponseDTOMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagCreationDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * TagService implementation.
 *
 * @author Yauheni Tsitov
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final GiftCertificatesTagDao giftCertificatesTagDao;
    private final TagResponseDTOMapper tagResponseDTOMapper;
    private final TagCreationDTOMapper tagCreationDTOMapper;
    private final TagCreationDTOValidator tagCreationDTOValidator;

    @Autowired
    public TagServiceImpl(TagDao tagDao, GiftCertificatesTagDao giftCertificatesTagDao,
                          TagResponseDTOMapper tagResponseDTOMapper,
                          TagCreationDTOMapper tagCreationDTOMapper,
                          TagCreationDTOValidator tagCreationDTOValidator) {
        this.tagDao = tagDao;
        this.giftCertificatesTagDao = giftCertificatesTagDao;
        this.tagResponseDTOMapper = tagResponseDTOMapper;
        this.tagCreationDTOMapper = tagCreationDTOMapper;
        this.tagCreationDTOValidator = tagCreationDTOValidator;
    }

    @Override
    public List<TagResponseDTO> findAll() {
        return tagDao.findAll().stream().map(tagResponseDTOMapper::toTagResponseDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagResponseDTO add(TagCreationDTO tagCreationDTO) {
        tagCreationDTOValidator.isTagCreationDTOValid(tagCreationDTO);
        Tag tag = tagCreationDTOMapper.toTag(tagCreationDTO);
        String tagName = tag.getName();
        long tagId = tag.getId();
        if (tagId != 0) {
            if (tagName != null) {
                tagDao.addWithId(tag);
            }
        } else {
            Tag newTag = tagDao.addWithoutId(tag);
            tag.setId(newTag.getId());
        }
        return tagResponseDTOMapper.toTagResponseDTO(tag);
    }

    @Override
    public TagResponseDTO findById(long id) {
        Tag tag = tagDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.TAG));
        return tagResponseDTOMapper.toTagResponseDTO(tag);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Optional<Tag> tagOptional = tagDao.findById(id);
        if (tagOptional.isPresent()) {
            giftCertificatesTagDao.deleteByTagId(id);
            tagDao.delete(id);
        } else {
            throw new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.TAG);
        }
    }
}