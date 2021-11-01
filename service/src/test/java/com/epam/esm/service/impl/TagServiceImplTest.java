package com.epam.esm.service.impl;

import com.epam.esm.Paginator;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.TagDTOMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.PaginationContainerValidator;
import com.epam.esm.validator.TagDTOValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {
    @Mock
    private TagDao tagDao;
    @Mock
    private TagDTOMapper tagDTOMapper;
    @Mock
    private TagDTOValidator tagDTOValidator;
    @Mock
    private PaginationContainerValidator paginationContainerValidator;
    @Mock
    private Paginator<Tag> paginator;
    private TagService service;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TagServiceImpl(tagDao, tagDTOMapper, tagDTOValidator, paginationContainerValidator, paginator);
    }

    @Test
    void findAll() {
        List<Tag> tagList = new ArrayList<>();
        Mockito.when(tagDao.findAll()).thenReturn(tagList);
        Mockito.when(paginator.paginate(tagList, new PaginationContainer())).thenReturn(tagList);
        List<TagDTO> actual = service.findAll(new PaginationContainer());
        List<TagDTO> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Tag tag = new Tag();
        Mockito.when(tagDTOMapper.toEntity(Mockito.any())).thenReturn(tag);
        TagDTO tagDTO = new TagDTO();
        Mockito.when(tagDTOMapper.toDTO(tag)).thenReturn(tagDTO);
        TagDTO actual = service.add(tagDTO);
        TagDTO expected = new TagDTO();
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        long id = 3;
        Mockito.when(tagDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> service.findById(id));
    }

    @Test
    void delete() {
        long id = 3;
        Mockito.when(tagDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> service.delete(id));
    }

    @Test
    void findMostWidelyTagUsersHighestCostOrders() {
        long id = 3;
        Mockito.when(tagDao.findMostWidelyTagUsersHighestCostOrders()).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> service.findMostWidelyTagUsersHighestCostOrders());
    }
}