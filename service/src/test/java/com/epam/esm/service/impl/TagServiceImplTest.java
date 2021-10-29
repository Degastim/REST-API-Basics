package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.tag.TagCreationDTO;
import com.epam.esm.dto.tag.TagResponseDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.mapper.tag.TagCreationDTOMapper;
import com.epam.esm.mapper.tag.TagResponseDTOMapper;
import com.epam.esm.service.TagService;
import com.epam.esm.validator.TagCreationDTOValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {
    private final Tag tag = new Tag(1, "Tag");
    private TagService service;
    @Mock
    private TagDao tagDao;
    @Mock
    private GiftCertificatesTagDao giftCertificatesTagDao;
    @Mock
    private TagResponseDTOMapper tagResponseDTOMapper;
    @Mock
    private TagCreationDTOMapper tagCreationDTOMapper;
    @Mock
    private TagCreationDTOValidator tagCreationDTOValidator;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new TagServiceImpl(tagDao, giftCertificatesTagDao, tagResponseDTOMapper, tagCreationDTOMapper,
                tagCreationDTOValidator);
    }

    @Test
    void findAll() {
        List<Tag> list = new ArrayList<>();
        list.add(tag);
        TagResponseDTO tagResponseDTO = new TagResponseDTO(1, "Tag");
        Mockito.when(tagDao.findAll()).thenReturn(list);
        Mockito.when(tagResponseDTOMapper.toTagResponseDTO(Mockito.any())).thenReturn(tagResponseDTO);
        List<TagResponseDTO> actual = service.findAll();
        List<TagResponseDTO> expected = new ArrayList<>();
        expected.add(tagResponseDTO);
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        TagCreationDTO tagCreationDTO = new TagCreationDTO(1, "Tag");
        TagResponseDTO expected = new TagResponseDTO(1, "Tag");
        Mockito.when(tagDao.findByName(tag.getName())).thenReturn(Optional.empty());
        Mockito.when(tagCreationDTOMapper.toTag(Mockito.any())).thenReturn(tag);
        Mockito.when(tagDao.addWithoutId(Mockito.any())).thenReturn(tag);
        Mockito.when(tagResponseDTOMapper.toTagResponseDTO(Mockito.any())).thenReturn(expected);
        TagResponseDTO actual = service.add(tagCreationDTO);
        assertEquals(actual, expected);
    }

    @Test
    void findById() {
        long tagId = tag.getId();
        TagResponseDTO expected = new TagResponseDTO(1, "Tag");
        Mockito.when(tagDao.findById(tagId)).thenReturn(Optional.of(tag));
        Mockito.when(tagResponseDTOMapper.toTagResponseDTO(Mockito.any())).thenReturn(expected);
        TagResponseDTO actual = service.findById(tagId);
        assertEquals(expected, actual);
    }

    @Test
    void delete() {
        long tagId = tag.getId();
        Mockito.when(tagDao.findById(tagId)).thenReturn(Optional.of(tag));
        assertDoesNotThrow(() -> service.delete(tagId));
    }
}