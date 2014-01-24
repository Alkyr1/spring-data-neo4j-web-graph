package com.comsysto.neo4j.showcase.model.nodes;

import com.comsysto.neo4j.showcase.model.IdentifiableEntity;
import com.comsysto.neo4j.showcase.model.RelationshipTypes;
import com.comsysto.neo4j.showcase.model.relationships.Link;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by okyrych on 12/27/13.
 */
@NodeEntity
public class Page extends IdentifiableEntity {

    @Indexed(unique = true)
    private String url;

    @RelatedToVia(type = RelationshipTypes.LINK_OUT)
    private Set<Link> outLinks = new HashSet<Link>();


    public Page() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Link> getOutLinks() {
        return outLinks;
    }

    public void setOutLinks(Set<Link> outLinks) {
        this.outLinks = outLinks;
    }

    @Override
    public String toString() {
        return "Page{" +
                "graphId=" + this.getGraphId() +
                ", url=" + this.getUrl() +
                '}';
    }

}
