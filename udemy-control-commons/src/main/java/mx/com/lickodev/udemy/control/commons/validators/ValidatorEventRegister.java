package mx.com.lickodev.udemy.control.commons.validators;

/**
 * import java.util.Arrays; import java.util.List; import java.util.Map;
 * 
 * import org.springframework.beans.factory.InitializingBean; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
 * import org.springframework.validation.Validator;
 */

/**
 * 
 * @author saul_
 *
 *         https://www.baeldung.com/spring-data-rest-validators;
 *         https://stackoverflow.com/questions/24318405/spring-data-rest-validator
 * 
 *         Este codigo se comenta y se sustituye por el existente en la clase
 *         mx.com.lickodev.udemy.control.commons.config.repository
 * 
 */
/**
 * @Configuration public class ValidatorEventRegister implements
 *                InitializingBean {
 * 
 * @Autowired ValidatingRepositoryEventListener
 *            validatingRepositoryEventListener;
 * 
 * @Autowired private Map<String, Validator> validators;
 * 
 * @Override public void afterPropertiesSet() throws Exception { List<String>
 *           events = Arrays.asList("beforeCreate"); for (Map.Entry<String,
 *           Validator> entry : validators.entrySet()) {
 *           events.stream().filter(p ->
 *           entry.getKey().startsWith(p)).findFirst() .ifPresent(p ->
 *           validatingRepositoryEventListener.addValidator(p,
 *           entry.getValue())); } }
 * 
 *           }
 */
