
package com.co.brayanfsilvar.ctrlactividades.security;

import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
/**
 *
 * @author Brayan F Silva R
 */
@javax.ws.rs.NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {
    
}
