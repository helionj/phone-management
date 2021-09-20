package br.com.helion.phonemanagement.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.helion.phonemanagement.controllers.exceptions.FieldMessage;
import br.com.helion.phonemanagement.dtos.SimCardDTOInsert;
import br.com.helion.phonemanagement.entities.SimCard;
import br.com.helion.phonemanagement.repositories.SimCardRepository;


public class SimCardInsertValidator implements ConstraintValidator<SimCardInsertValid, SimCardDTOInsert> {
	
	@Autowired
	private SimCardRepository repository;
	
	@Override
	public void initialize(SimCardInsertValid ann) {
	}

	@Override
	public boolean isValid(SimCardDTOInsert dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		SimCard simCard = repository.findByNumber(dto.getNumber());
		
		if(simCard != null) {
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