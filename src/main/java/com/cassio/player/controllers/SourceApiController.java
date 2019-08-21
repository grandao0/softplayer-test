package com.cassio.player.controllers;

import com.cassio.player.models.SourceResponse;
import com.cassio.player.services.SourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class SourceApiController implements com.cassio.player.controllers.SourceApi {

    @Inject
    private SourceService sourceService;

    @Override
    public ResponseEntity<SourceResponse> listarLinkCodigoFonte() {
        return sourceService.listarLinkCodigoFonte();
    }
}
