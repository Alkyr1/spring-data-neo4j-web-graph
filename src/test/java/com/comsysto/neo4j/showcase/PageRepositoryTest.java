package com.comsysto.neo4j.showcase;

import com.comsysto.neo4j.showcase.main.Neo4jPersister;
import com.comsysto.neo4j.showcase.model.nodes.Page;
import com.comsysto.neo4j.showcase.repository.PageRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by okyrych on 2/4/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/comsysto/neo4j/showcase/related-to-via-test-context.xml"})
@Transactional
public class PageRepositoryTest {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private Neo4jPersister neo4jPersister;

    @Before
    public void createData () {
        this.neo4jPersister.createWebGraphData();
    }

    @After
    public void cleanData() {
        this.pageRepository.deleteAll();
    }

    @Test
    public void testFindByPropertyValue() {
        Page page = this.pageRepository.findByPropertyValue("url", "http://www.rambler.ru/");
        assertEquals("Should be http://www.rambler.ru/", "http://www.rambler.ru/", page.getUrl());
    }

}
