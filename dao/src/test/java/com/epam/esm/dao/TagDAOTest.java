package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Query;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {TestDBConfig.class})
public class TagDAOTest {

    @Autowired
    private TagDAO tagDAO;

//    @Autowired @Qualifier("testTemplate")
//    NamedParameterJdbcTemplate namedParamJdbcTemplate;
//
//    @BeforeAll
//    public void setup() {
//        tagDAO.setNamedParamJdbcTemplate(namedParamJdbcTemplate);
//    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindOne(){
        Tag tag = new Tag();
        tag.setName("Tag");
        tag = tagDAO.insert(tag);
        Optional<Tag> tagInDb = tagDAO.findOne(tag.getId());
        Assertions.assertEquals(tag.getId(), tagInDb.get().getId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindOneNotPresent(){
        Optional<Tag> tagInDb = tagDAO.findOne(1L);
        Assertions.assertFalse(tagInDb.isPresent());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindAll(){
        Tag tag = new Tag();
        tag.setName("Tag");
        tagDAO.insert(tag);
        List<Tag> certificateInDb = tagDAO.findAll();
        Assertions.assertEquals(1, certificateInDb.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteExisting(){
        Tag tag = new Tag();
        tag.setName("Tag");
        tag = tagDAO.insert(tag);
        boolean actual = tagDAO.delete(tag.getId());
        Assertions.assertTrue(actual);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteNotExisting(){
        boolean actual = tagDAO.delete(1L);
        Assertions.assertFalse(actual);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testAddCertificate(){
        Tag tag = new Tag();
        tag.setName("Tag");
        tag = tagDAO.insert(tag);
        List<Tag> tagInDb = tagDAO.findAll();
        Assertions.assertEquals(tag.getName(), tagInDb.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByName() {
        Tag tag = new Tag();
        tag.setName("Tag");
        tag = tagDAO.insert(tag);
        Optional<Tag> tagByName = tagDAO.findByName(tag.getName());
        Assertions.assertEquals(tag.getName(), tagByName.get().getName());
    }
}
