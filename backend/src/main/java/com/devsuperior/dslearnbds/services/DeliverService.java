package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslearnbds.dto.DeliverRevisionDTO;
import com.devsuperior.dslearnbds.entities.Deliver;
import com.devsuperior.dslearnbds.repositories.DeliverRepository;

@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;

	@PreAuthorize("hasAnyRole('ADMIN','INSTRUCTOR')")
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto) {
		Deliver entity = repository.getOne(id);
		copyDtoToEntity(entity, dto);
		repository.save(entity);

	}

	private void copyDtoToEntity(Deliver entity, DeliverRevisionDTO dto) {
		entity.setStatus(dto.getStatus());
		entity.setFeedback(dto.getFeedback());
		entity.setCorrectCount(dto.getCorrectCount());
	}

}
