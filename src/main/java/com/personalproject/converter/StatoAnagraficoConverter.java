package com.personalproject.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.personalproject.model.StatoAnagrafico;
import com.personalproject.service.StatoAnagraficoService;

@Component
public class StatoAnagraficoConverter implements Converter<String, StatoAnagrafico> {

	private final StatoAnagraficoService statoAnagraficoService;

	public StatoAnagraficoConverter(StatoAnagraficoService statoAnagraficoService) {
		this.statoAnagraficoService = statoAnagraficoService;
	}

	@Override
	public StatoAnagrafico convert(@NonNull String codice) {
		return statoAnagraficoService.cerca_codice(codice);
	}
}
