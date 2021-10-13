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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TagDaoImplTest {
    private static final TagDao tagDao = new TagDaoImpl(new JdbcTemplate(new DatabaseConfiguration().embeddedDataSource()), new TagMapper());

    @Test
    @Order(1)
    void addWithoutId() {
        Tag tag = new Tag("oi");
        Tag actual = tagDao.addWithoutId(tag);
        tag.setId(6);
        assertEquals(tag, actual);
    }

    @Test
    @Order(2)
    void addWithId() {
        Tag tag = new Tag(7, "db");
        Tag actual = tagDao.addWithId(tag);
        assertEquals(tag, actual);
    }

    @Test
    @Order(3)
    void findAll() {
        int actual = tagDao.findAll().size();
        int expected = 7;
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void findByName() {
        Tag actual = tagDao.findByName("db").get();
        Tag expected = new Tag(7, "db");
        assertEquals(expected, actual);
    }

    @Test
    @Order(5)
    void findById() {
        Tag actual = tagDao.findById(7).get();
        Tag expected = new Tag(7, "db");
        assertEquals(expected, actual);
    }

    @Test
    @Order(6)
    void update() {
        Tag tag = new Tag(7, "abs");
        assertDoesNotThrow(() -> tagDao.update(tag));
    }

    @Test
    @Order(7)
    void delete() {
        assertDoesNotThrow(() -> tagDao.delete(7));
    }

    @Test
    @Order(8)
    void findByIdAndName() {
        long id = 6;
        String name = "oi";
        Tag expected = new Tag(id, name);
        Tag actual = tagDao.findByIdAndName(id, name).get();
        assertEquals(expected, actual);
    }
}