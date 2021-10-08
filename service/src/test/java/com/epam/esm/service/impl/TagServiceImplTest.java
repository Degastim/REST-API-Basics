package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificatesTagDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
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
    TagDao tagDao;
    @Mock
    GiftCertificatesTagDao giftCertificatesTagDao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new TagServiceImpl(tagDao, giftCertificatesTagDao);
    }

    @Test
    void findAll() {
        List<Tag> expected = new ArrayList<>();
        expected.add(tag);
        Mockito.when(tagDao.findAll()).thenReturn(expected);
        List<Tag> actual = service.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void add() {
        Mockito.when(tagDao.findByName(tag.getName())).thenReturn(Optional.empty());
        assertDoesNotThrow(() -> service.add(tag));
    }

    @Test
    void findById() {
        long tagId = tag.getId();
        Mockito.when(tagDao.findById(tagId)).thenReturn(Optional.of(tag));
        Tag expected = service.findById(tagId);
        assertEquals(expected, tag);
    }

    @Test
    void delete() {
        long tagId = tag.getId();
        Mockito.when(tagDao.findById(tagId)).thenReturn(Optional.of(tag));
        service.delete(tagId);
    }
}