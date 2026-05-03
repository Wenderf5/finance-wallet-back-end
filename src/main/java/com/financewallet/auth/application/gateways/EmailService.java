package com.financewallet.auth.application.gateways;

import com.financewallet.auth.application.dto.Email;

public interface EmailService {
    void send(Email email);
}
