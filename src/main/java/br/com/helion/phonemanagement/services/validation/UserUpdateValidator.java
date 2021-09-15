package br.com.helion.phonemanagement.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.helion.phonemanagement.controllers.exceptions.FieldMessage;
import br.com.helion.phonemanagement.dtos.UserDTOUpdate;
import br.com.helion.phonemanagement.entities.User;
import br.com.helion.phonemanagement.repositories.UserRepository;


public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserDTOUpdate> {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserDTOUpdate dto, ConstraintValidatorContext context) {
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		User user = repository.findByEmail(dto.getEmail());
		
		if(user != null && userId != user.getId()) {
			list.add(new FieldMessage("email", "Email está em uso"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}