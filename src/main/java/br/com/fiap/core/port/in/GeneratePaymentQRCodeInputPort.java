package br.com.fiap.core.port.in;

import br.com.fiap.core.domain.model.request.PaymentQRCodeRequest;

public interface GeneratePaymentQRCodeInputPort {

    String generatePaymentQrCode(PaymentQRCodeRequest paymentQRCodeRequest);
}
