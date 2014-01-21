package com.comsysto.neo4j.showcase.main;

import com.comsysto.neo4j.showcase.model.Product;
import com.comsysto.neo4j.showcase.model.nodes.Page;
import com.comsysto.neo4j.showcase.repository.PageRepository;
import com.comsysto.neo4j.showcase.repository.ProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.transaction.*;

/**
 * @author: rkowalewski
 */
public class Main {

    private static final String CLASSPATH_LOCATION = "classpath:com/comsysto/neo4j/showcase/main/related-to-via-test-context.xml";

    public static void main(String[] args) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(CLASSPATH_LOCATION);

        Neo4jPersister neo4jPersister = (Neo4jPersister) context.getBean("neo4jPersister");


        PageRepository pageRepository = (PageRepository) context.getBean("pageRepository");
        pageRepository.deleteAll();

        neo4jPersister.createWebGraphData();

        System.out.println(pageRepository.count());
        System.out.println("-------------------------------");
        for(Page page: pageRepository.findOtherPages("http://www.rambler.ru/")) {
            System.out.println(page.toString());
        }
        System.out.println("-------------------------------");
        for(Page page: pageRepository.findOtherPages("http://www.rambler3.ru/")) {
            System.out.println(page.toString());
        }

        //neo4jPersister.createTestData();
    }
}
