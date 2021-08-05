package mx.com.lickodev.udemy.control.commons.advice.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import mx.com.lickodev.udemy.control.commons.exceptions.CourseNotFoundException;
import mx.com.lickodev.udemy.control.commons.exceptions.UserNotFoundException;

/**
 * 
 * @author saul_
 * 
 *         refs:
 * 
 *         -https://medium.com/@jovannypcg/understanding-springs-controlleradvice-cd96a364033f
 * 
 *         -https://github.com/jovannypcg/exception_handler
 * 
 *         -https://zetcode.com/springboot/controlleradvice/
 * 
 *         PENDIENTE:
 *         https://stackoverflow.com/questions/21150688/how-can-i-handle-exceptions-with-spring-data-rest-and-the-pagingandsortingreposi/44581156
 */
@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Este permite capturar las excepciones que se generan al utilizar la
	 * anotación @Valid de Java para validación de objetos con ayuda del controller
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();

		body.put("timestamp", LocalDate.now());
		body.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		body.put("errors", errors);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
	}

	/**
	 * Este captura las excepciones que se marcan en el valor del @ExceptionHandler
	 * y te permite manipularlas para darles formato o algo antes de enviarlas a
	 * quien solicitó la petición, de esta forma puedes dar una mejor presentación a
	 * los mensajes de error.
	 * 
	 * @param ex      excepcion generica qeu captura todas las excepciones que
	 *                extiendan de ella
	 * @param request el request de la solicitud
	 * @return un objeto que contiene los errores conformato e información adicional
	 *         para el solicitante
	 */
	@ExceptionHandler({ UserNotFoundException.class, CourseNotFoundException.class })
	public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);

	}

	/**
	 * https://www.baeldung.com/spring-data-rest-validators;
	 * https://stackoverflow.com/questions/24318405/spring-data-rest-validator
	 * 
	 * Captura las excepciones o errores causados por la clase personalizada
	 * CourseValidator
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ RepositoryConstraintViolationException.class })
	public ResponseEntity<Object> handleCustomConstraintViolationException(Exception ex, WebRequest request) {
		RepositoryConstraintViolationException nevEx = (RepositoryConstraintViolationException) ex;

		String errors = nevEx.getErrors().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.joining("\n"));

		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * 
	 * Usando la anotación @Validated a nivel de controlador (aun siendo un rest
	 * repository) es posible validar los parametros que se envian por la url para
	 * evitar que lleguen al repositorio, este metodo "cacha" las excepciones que se
	 * produzcan por esas validaciones y permite manipularlas antes de enviarlas al
	 * usuario final.
	 * 
	 * https://www.javaquery.com/2018/02/passing-and-validating-requestparam-in.html?m=1
	 * 
	 * Caputra las excepciones causadas por el API de validacion de Java, ya se un
	 * unico parametro o un objeto completo.
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex,
			HttpServletRequest request) {
		try {
			List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.toList());
			return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(Arrays.asList(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
	}

}
