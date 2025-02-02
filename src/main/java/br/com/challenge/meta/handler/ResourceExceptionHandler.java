package br.com.challenge.meta.handler;

import br.com.challenge.meta.dto.response.Response;
import br.com.challenge.meta.exception.ApprovalProcessing.ApprovalProcessingInvalidUpdateException;
import br.com.challenge.meta.exception.ApprovalProcessing.ApprovalProcessingNotFoundException;
import br.com.challenge.meta.exception.NotParsableContentException;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;

/**
 * Class that implements a handler of exceptions and errors in the API, using {@ControllerAdvice} 
 * and sending the proper response to the client.
 *
 * @author Felipe Santiago
 * @since 2021-03-12
 *
 * @param <T>
 */
@ControllerAdvice
public class ResourceExceptionHandler<T> {
	
	/**
	 * Method that handles with a TravelInvalidUpdateException and returns an error with 
	 * status code = 403.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception ApprovalProcessingInvalidUpdateException
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { ApprovalProcessingInvalidUpdateException.class })
    protected ResponseEntity<Response<T>> handleApprovalProcessingInvalidUpdateException(ApprovalProcessingInvalidUpdateException exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
	
	/**
	 * Method that handles with a TravelNotFoundException and returns an error with 
	 * status code = 404.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception ApprovalProcessingNotFoundException
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { ApprovalProcessingNotFoundException.class })
    protected ResponseEntity<Response<T>> handleApprovalProcessingNotFoundException(ApprovalProcessingNotFoundException exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
	
	/**
	 * Method that handles with a HttpClientErrorException and returns a Conflict
	 * error with status code = 409.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception HttpClientErrorException
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { HttpClientErrorException.Conflict.class })
    protected ResponseEntity<Response<T>> handleConflictException(HttpClientErrorException exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
	
	/**
	 * Method that handles with a HttpMessageNotReadableException or JsonParseException and 
	 * returns an Unprocessable Entity error with status code = 422.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception Exception
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, JsonParseException.class, NotParsableContentException.class })
    protected ResponseEntity<Response<T>> handleMessageNotReadableException(Exception exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }
	
	/**
	 * Method that handles with a HttpClientErrorException and returns a TooManyRequests error 
	 * with status code = 429.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception HttpClientErrorException
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { HttpClientErrorException.TooManyRequests.class })
    protected ResponseEntity<Response<T>> handleTooManyRequestException(HttpClientErrorException exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }
	
	/**
	 * Method that handles with a TravelsJavaAPIException and returns an Internal Server Error 
	 * with status code = 500.
	 *
	 * @author Felipe Santiago
	 * @since 2021-03-12
	 * 
	 * @param exception ServerErrorException
	 * @return ResponseEntity<Response<T>>
	 */
	@ExceptionHandler(value = { ServerErrorException.class })
    protected ResponseEntity<Response<T>> handleAPIException(ServerErrorException exception) {
		
		Response<T> response = new Response<>();
		response.addErrorMsgToResponse(exception.getLocalizedMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
