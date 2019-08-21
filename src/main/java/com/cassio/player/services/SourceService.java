package com.cassio.player.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cassio.player.constants.ServiceConstants;
import com.cassio.player.models.SourceResponse;

@Service
public class SourceService {

	public ResponseEntity<SourceResponse> listarLinkCodigoFonte() {
		SourceResponse sourceResponse = new SourceResponse();
		sourceResponse.setUrl(ServiceConstants.GIT_URL);
		return ResponseEntity.ok(sourceResponse);
	}
}
