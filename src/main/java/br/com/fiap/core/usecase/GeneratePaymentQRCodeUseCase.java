package br.com.fiap.core.usecase;

import br.com.fiap.core.domain.model.request.PaymentQRCodeRequest;
import br.com.fiap.core.port.in.GeneratePaymentQRCodeInputPort;
import br.com.fiap.core.port.out.FetchPaymentQRCodeOutputPort;

public class GeneratePaymentQRCodeUseCase implements GeneratePaymentQRCodeInputPort {

    private final FetchPaymentQRCodeOutputPort fetchPaymentQRCodeOutputPort;

    public GeneratePaymentQRCodeUseCase(FetchPaymentQRCodeOutputPort fetchPaymentQRCodeOutputPort) {
        this.fetchPaymentQRCodeOutputPort = fetchPaymentQRCodeOutputPort;
    }

    @Override
    public String generatePaymentQrCode(PaymentQRCodeRequest paymentQRCodeRequest) {
        return fetchPaymentQRCodeOutputPort.getQRCode();
    }
}
