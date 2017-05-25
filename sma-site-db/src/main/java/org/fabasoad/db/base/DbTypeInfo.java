package org.fabasoad.db.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author efabizhevsky
 * @date 5/24/2017.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTypeInfo {

    String name();
}
