package com.comsysto.neo4j.showcase.model.relationships;

import com.comsysto.neo4j.showcase.model.RelationshipTypes;
import com.comsysto.neo4j.showcase.model.nodes.Page;
import org.springframework.data.neo4j.annotation.*;

/**
 * Created by okyrych on 12/27/13.
 */
@RelationshipEntity(type = RelationshipTypes.LINK_OUT)
public class Link {

    @GraphId
    private Long graphId;

    @StartNode
    @Fetch
    private Page outPage;

    @EndNode
    @Fetch
    private Page inPage;

    public Link() {

    }

    public Link(Page outPage, Page inPage) {
        this.outPage = outPage;
        this.inPage = inPage;
    }

    public Long getGraphId() {
        return graphId;
    }

    public void setGraphId(Long graphId) {
        this.graphId = graphId;
    }

    public Page getInPage() {
        return inPage;
    }

    public void setInPage(Page inPage) {
        this.inPage = inPage;
    }

    public Page getOutPage() {
        return outPage;
    }

    public void setOutPage(Page outPage) {
        this.outPage = outPage;
    }

}
