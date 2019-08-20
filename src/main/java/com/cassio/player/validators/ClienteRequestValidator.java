package com.cassio.player.validators;

import com.cassio.player.constants.MessageConstants;
import com.cassio.player.constants.ServiceConstants;
import com.cassio.player.models.ClienteRequest;
import com.cassio.player.utils.Messages;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Objects;

@Component
public class ClienteRequestValidator implements Validator {

    // General Email Regex (RFC 5322 Official Standard)
    private static final String REGEX_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
            "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])" +
            "*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]" +
            "*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final String CODIGO_BAD_REQUEST = "400";

    @Inject
    private Messages messages;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object input, Errors errors) {
        if (errors.getErrorCount() == 0) {
            ClienteRequest clienteRequest = (ClienteRequest) input;
            try {
                LocalDate data = LocalDate.parse(clienteRequest.getDataNascimento(), ServiceConstants.formatter);
                if (Objects.isNull(data) || data.isAfter(LocalDate.now())) {
                    errors.reject(CODIGO_BAD_REQUEST, messages.get(MessageConstants.ERRO_DATA_INVALIDA));
                }
            } catch (Exception e) {
                errors.reject(CODIGO_BAD_REQUEST, messages.get(MessageConstants.ERRO_DATA_INVALIDA));
            }

            if (!Objects.isNull(clienteRequest.getEmail()) && !clienteRequest.getEmail().matches(REGEX_EMAIL)) {
                errors.reject(CODIGO_BAD_REQUEST, messages.get(MessageConstants.ERRO_EMAIL_INVALIDO));
            }
        }
    }
}
