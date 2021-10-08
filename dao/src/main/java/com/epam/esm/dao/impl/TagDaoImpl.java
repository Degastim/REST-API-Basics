package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.constant.sql.TagSql;
import com.epam.esm.dao.mapper.TagMapper;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotAddedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * TagDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class TagDaoImpl implements TagDao {
    private final JdbcTemplate jdbcTemplate;
    private final TagMapper tagMapper;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate, TagMapper tagMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tagMapper = tagMapper;
    }

    @Override
    public long add(Tag tag) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(TagSql.ADD_TAG, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, tag.getName());
            return preparedStatement;
        }, keyHolder);
        if (keyHolder.getKey() == null) {
            throw new ResourceNotAddedException("Tag not add.No KeyHolder");
        }
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query(TagSql.FIND_ALL, tagMapper);
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> tagList = jdbcTemplate.query(TagSql.FIND_BY_NAME, tagMapper, name);
        return returnTag(tagList);
    }

    @Override
    public Optional<Tag> findById(long id) {
        List<Tag> tagList = jdbcTemplate.query(TagSql.FIND_BY_ID, tagMapper, id);
        return returnTag(tagList);
    }

    @Override
    public void update(Tag tag) {
        jdbcTemplate.update(TagSql.UPDATE_BY_ID, tag.getName(), tag.getId());
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update(TagSql.DELETE_TAG, id);
    }

    private Optional<Tag> returnTag(List<Tag> tagList) {
        if (tagList.size() != 0) {
            return Optional.of(tagList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
