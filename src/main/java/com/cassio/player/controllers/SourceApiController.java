package com.cassio.player.controllers;

import javax.inject.Inject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cassio.player.models.SourceResponse;
import com.cassio.player.services.SourceService;

@RestController
public class SourceApiController implements SourceApi {

	@Inject
	private SourceService sourceService;

	public ResponseEntity<SourceResponse> listarLinkCodigoFonte() {
		return sourceService.listarLinkCodigoFonte();
	}
}
