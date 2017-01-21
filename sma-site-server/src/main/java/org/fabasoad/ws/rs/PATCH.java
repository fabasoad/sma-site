package org.fabasoad.ws.rs;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yevhen Fabizhevskyi
 * @date 21.01.2017.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod("PATCH")
public @interface PATCH {
}