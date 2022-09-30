package app.controller.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import app.exeception.AraujoExeception;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class AraujoAdvice {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("mensagem", "O campo ".concat(ex.getName().concat(" é inválido")));
		errors.put("status", String.valueOf(HttpStatus.BAD_REQUEST.value()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(errors);
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("mensagem", ex.getMessage());
		errors.put("status", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(errors);
	}



	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(AraujoExeception.class)
	public Map<String, String> handlerDataIntegrityViolationException(AraujoExeception ex) {
		return ex.getErrors();
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Map<String, String>> RuntimeException(RuntimeException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("mensagem", ex.getMessage());
		errors.put("status", String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()));
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(errors);
	}
}
