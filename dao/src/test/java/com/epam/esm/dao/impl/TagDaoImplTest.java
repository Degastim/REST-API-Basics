package com.epam.esm.dao.impl;

import com.epam.esm.Application;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Application.class)
class TagDaoImplTest {
    @Autowired
    private TagDao tagDao;

    @Test
    @Transactional
    void add() {
        Tag actual = new Tag("abd");
        tagDao.add(actual);
        Tag expected = new Tag(6, "abd");
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    void delete() {
        Tag tag = new Tag(6, "abd");
        assertDoesNotThrow(() -> tagDao.delete(tag));
    }

    @Test
    void findAll() {
        List<Tag> tagList = tagDao.findAll(new PaginationContainer(0, 0));
        assertEquals(tagList.size(), 5);
    }

    @Test
    void findByName() {
        String name = "a";
        Optional<Tag> tagOptional = tagDao.findByName(name);
        Tag actual = tagOptional.get();
        Tag expected = new Tag();
        expected.setId(1);
        expected.setName(name);
        actual.setGiftCertificateSet(null);
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        long id = 1;
        Optional<Tag> tagOptional = tagDao.findById(id);
        Tag actual = tagOptional.get();
        Tag expected = new Tag();
        expected.setId(id);
        expected.setName("a");
        actual.setGiftCertificateSet(null);
        assertEquals(expected, actual);
    }
}