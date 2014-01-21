package com.comsysto.neo4j.showcase.main;

import com.comsysto.neo4j.showcase.model.Product;
import com.comsysto.neo4j.showcase.model.User;
import com.comsysto.neo4j.showcase.model.nodes.Page;
import com.comsysto.neo4j.showcase.model.relationships.Link;
import com.comsysto.neo4j.showcase.repository.PageRepository;
import com.comsysto.neo4j.showcase.repository.ProductRepository;
import com.comsysto.neo4j.showcase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: rkowalewski
 */
@Component
@Transactional
public class Neo4jPersister {

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PageRepository pageRepository;


    private void userClickedProduct(User user, Product product) {

        user.addClickedProduct(product);

        userRepository.save(user);
        productRepository.save(product);
    }

    private Product createProduct(String id, String name) {
        return productRepository.save(new Product(id, name));
    }

    private User createUser(String id, String name) {
        return userRepository.save(new User(id, name));
    }

    private Page createPage(String url) {
        Page page = new Page();
        page.setUrl(url);
        return pageRepository.save(page);
    }

    public void createWebGraphData() {
        Page page1 = this.createPage("http://www.rambler.ru/");

        Page page2 = this.createPage("http://www.rambler1.ru/");
        Page page3 = this.createPage("http://www.rambler2.ru/");
        Page page4 = this.createPage("http://www.rambler3.ru/");
        Link link1 = new Link(page1, page2);
        Link link2 = new Link(page1, page3);

        Link link3 = new Link(page4, page2);
        Set<Link> set1 = new HashSet<Link>();
        set1.add(link3);
        page4.setOutLinks(set1);


        Set<Link> set = new HashSet<Link>();
        set.add(link1);
        set.add(link2);
        page1.setOutLinks(set);


        this.pageRepository.save(Arrays.asList(page1, page2, page3, page4));
    }
    
    public void createTestData() {
        User jordan = createUser("MJ", "Monika Jordan");
        User pippen = createUser("SP", "Sandra Pippen");
        User miller = createUser("JM", "John Miller");

        Product pizzaMargarita = createProduct("Pizza_1", "Pizza Margarita");
        Product pizzaFungi = createProduct("Pizza_2", "Pizza Fungi");
        Product pizzaSalami = createProduct("Pizza_3", "Pizza Salami");
        Product pizzaVegitarian = createProduct("Pizza_4", "Pizza Vegitarian");
        Product pizzaRustica = createProduct("Pizza_5", "Pizza Rustica");

        userClickedProduct(jordan, pizzaMargarita);
        userClickedProduct(jordan, pizzaFungi);
        userClickedProduct(jordan, pizzaSalami);

        userClickedProduct(pippen, pizzaMargarita);
        userClickedProduct(pippen, pizzaVegitarian);
        userClickedProduct(pippen, pizzaRustica);
        userClickedProduct(pippen, pizzaMargarita);
        userClickedProduct(pippen, pizzaVegitarian);

        userClickedProduct(miller, pizzaFungi);
    }

}
