package com.agatTech.MediInstru.MediInstru.myorder;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


public class CustomAuditorAware implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}