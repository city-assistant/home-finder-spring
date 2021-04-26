package hf.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserException.class)
	public String handleCustomException(UserException exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/boardError";
	}

	@ExceptionHandler(Exception.class)
	public String handleException(Exception exception, Model model) {
		model.addAttribute("exception", exception);
		return "/errors/globalError";
	}
}
