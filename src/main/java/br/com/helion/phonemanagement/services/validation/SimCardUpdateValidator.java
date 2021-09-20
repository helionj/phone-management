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
import br.com.helion.phonemanagement.dtos.SimCardDTOUpdate;
import br.com.helion.phonemanagement.entities.SimCard;
import br.com.helion.phonemanagement.repositories.SimCardRepository;


public class SimCardUpdateValidator implements ConstraintValidator<SimCardUpdateValid, SimCardDTOUpdate> {
	
	@Autowired
	private SimCardRepository repository;
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public void initialize(SimCardUpdateValid ann) {
	}

	@Override
	public boolean isValid(SimCardDTOUpdate dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long simCardId = Long.parseLong(uriVars.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();
		
		SimCard simCard = repository.findByNumber(dto.getNumber());
		
		if(simCard != null && (simCardId != simCard.getId())) {
			list.add(new FieldMessage("number", "SimCard está em uso"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}