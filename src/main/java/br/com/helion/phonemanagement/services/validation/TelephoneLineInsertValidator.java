package br.com.helion.phonemanagement.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.helion.phonemanagement.controllers.exceptions.FieldMessage;
import br.com.helion.phonemanagement.dtos.TelephoneLineDTOInsert;
import br.com.helion.phonemanagement.entities.SimCard;
import br.com.helion.phonemanagement.entities.TelephoneLine;
import br.com.helion.phonemanagement.entities.User;
import br.com.helion.phonemanagement.repositories.SimCardRepository;
import br.com.helion.phonemanagement.repositories.TelephoneLineRepository;
import br.com.helion.phonemanagement.repositories.UserRepository;

public class TelephoneLineInsertValidator
		implements ConstraintValidator<TelephoneLineInsertValid, TelephoneLineDTOInsert> {

	@Autowired
	private TelephoneLineRepository repository;
	
	@Autowired
	private SimCardRepository simCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void initialize(TelephoneLineInsertValid ann) {
	}

	@Override
	public boolean isValid(TelephoneLineDTOInsert dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		TelephoneLine line;

		if (dto.getSimCard() == null) {
			list.add(new FieldMessage("simCard", "SimCard é obrigatório"));
		}else {
			line = repository.findBySimCardId(dto.getSimCard().getId());
			if (line != null) {
				list.add(new FieldMessage("simCard", "SimCard em uso"));
			}
		}
		

		line = repository.findByLineNumber(dto.getLineNumber());
		if (line != null) {
			list.add(new FieldMessage("lineNumber", "A linha em uso"));
		}
		
		if(dto.getSimCard()!= null) {
			Optional<SimCard> obj = simCardRepository.findById(dto.getSimCard().getId());
			
			if(obj.isEmpty()) {
				list.add(new FieldMessage("simCard", "SimCard não cadastrado"));
			}
		}
		
		if(dto.getUser()!=null) {
			Optional<User> usuObj = userRepository.findById(dto.getUser().getId());
			if(usuObj.isEmpty()) {
				list.add(new FieldMessage("user", "Usuário não cadastrado"));
			}
		}
		
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}