package org.fabasoad.rest;

import org.fabasoad.db.pojo.NewsPojo;
import org.fabasoad.db.pojo.PojoProperties;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sasha.dev on 11.12.2016.
 */
@Path("news")
public class NewsResource implements BaseResource<NewsPojo> {

    @Override
    public Class<NewsPojo> getPojoClass() {
        return NewsPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.News::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.News.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews() {
        return getAll();
    }
}
