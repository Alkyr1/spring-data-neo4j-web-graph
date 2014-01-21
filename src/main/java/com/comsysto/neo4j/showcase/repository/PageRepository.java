package com.comsysto.neo4j.showcase.repository;

import com.comsysto.neo4j.showcase.model.nodes.Page;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by okyrych on 12/27/13.
 */
public interface PageRepository extends GraphRepository<Page> {

    @Query("START page=node:Page(url={url}) " +
            "MATCH page-[link_out:LINK_OUT]->otherPage " +
            "RETURN otherPage " +
            "LIMIT 5 ")
    List<Page> findOtherPages(@Param("url") String url);
}
