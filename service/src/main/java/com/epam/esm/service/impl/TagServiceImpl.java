package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.TagDTOMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.PaginationContainerValidator;
import com.epam.esm.validator.TagDTOValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * TagService implementation.
 *
 * @author Yauheni Tsitov
 */
@Service
public class TagServiceImpl implements TagService {
    private final TagDao tagDao;
    private final TagDTOMapper tagDTOMapper;
    private final TagDTOValidator tagDTOValidator;
    private final PaginationContainerValidator paginationContainerValidator;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagDTOMapper tagDTOMapper,
                          TagDTOValidator tagDTOValidator,
                          PaginationContainerValidator paginationContainerValidator) {
        this.tagDao = tagDao;
        this.tagDTOMapper = tagDTOMapper;
        this.tagDTOValidator = tagDTOValidator;
        this.paginationContainerValidator = paginationContainerValidator;
    }

    @Override
    public List<TagDTO> findAll(PaginationContainer paginationContainer) {
        paginationContainerValidator.paginationPaginationContainer(paginationContainer);
        List<Tag> tagList = tagDao.findAll(paginationContainer);
        return tagList.stream().map(tagDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TagDTO add(TagDTO tagDTO) {
        tagDTOValidator.isTagCreationDTOValid(tagDTO);
        Tag tag = tagDTOMapper.toEntity(tagDTO);
        tagDao.add(tag);
        return tagDTOMapper.toDTO(tag);
    }

    @Override
    public TagDTO findById(long id) {
        Tag tag = tagDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.TAG));
        return tagDTOMapper.toDTO(tag);
    }

    @Override
    @Transactional
    public void delete(long id) {
        Tag tag = tagDao.findById(id).orElseThrow(() ->
                new ResourceNotFoundedException("Requested resource not found (id)=" + id, ExceptionCauseCode.TAG));
        tagDao.delete(tag);
    }

    @Override
    public TagDTO findMostWidelyTagUsersHighestCostOrders() {
        Tag tag = tagDao.findMostWidelyTagUsersHighestCostOrders()
                .orElseThrow(() -> new ResourceNotFoundedException("Tag not founded", ExceptionCauseCode.TAG));
        return tagDTOMapper.toDTO(tag);
    }
}