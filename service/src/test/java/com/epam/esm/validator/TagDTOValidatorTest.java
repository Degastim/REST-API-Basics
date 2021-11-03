package com.epam.esm.validator;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dto.TagDTO;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.InvalidFieldValueException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagDTOValidatorTest {
    @Mock
    private TagDao tagDao;
    private TagDTOValidator tagDTOValidator;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tagDTOValidator = new TagDTOValidator(tagDao);
    }

    @Test
    void isTagCreationDTOValid() {
        Mockito.when(tagDao.findByName(Mockito.any())).thenReturn(Optional.of(new Tag()));
        assertThrows(InvalidFieldValueException.class, () -> tagDTOValidator.isTagCreationDTOValid(new TagDTO()));
    }
}