package com.epam.esm.dao.impl;

import com.epam.esm.config.DatabaseConfiguration;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagDaoImplTest {
    private static final TagDao tagDao = new TagDaoImpl(new JdbcTemplate(new DatabaseConfiguration().embeddedDataSource()), new TagMapper());

    @Test
    @Order(1)
    void findAll() {
        long expected = 5;
        List<Tag> actual = tagDao.findAll();
        assertEquals(expected, actual.size());
    }

    @Test
    @Order(2)
    void findByName() {
        String tagName = "a";
        Tag tag = new Tag(1, tagName);
        Optional<Tag> tagOptional = tagDao.findByName(tagName);
        assertEquals(tag, tagOptional.get());
    }

    @Test
    @Order(3)
    void findById() {
        long tagId = 1;
        Tag tag = new Tag(1, "a");
        Optional<Tag> tagOptional = tagDao.findById(tagId);
        assertEquals(tag, tagOptional.get());
    }

    @Test
    @Order(4)
    void update() {
        Tag tag = new Tag(2, "Ff");
        assertDoesNotThrow(() -> tagDao.update(tag));
    }

    @Test
    void add() {
        long expected = 6;
        Tag tag = new Tag(1, "Tag");
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> tagDao.delete(5));
    }
}