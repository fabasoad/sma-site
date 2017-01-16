package org.fabasoad.rest;

import org.fabasoad.db.pojo.ContactsPojo;
import org.fabasoad.db.pojo.PojoProperties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("contacts")
public class ContactsResource extends BaseResource<ContactsPojo> {
    @Override
    public Class<ContactsPojo> getPojoClass() {
        return ContactsPojo.class;
    }

    @Override
    public Function<String, Optional<String>> fromDto() {
        return PojoProperties.Contacts::fromDto;
    }

    @Override
    public Map<String, String> getPojoProperties() {
        return Stream.of(PojoProperties.Contacts.values()).collect(Collectors.toMap(v -> v.DB, v -> v.DTO));
    }

    @Override
    String getDisplayName() {
        return "Contacts";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContacts() {
        return get("SMA_CONTACTS_BODY");
    }

}
