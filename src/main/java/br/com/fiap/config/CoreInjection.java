package br.com.fiap.config;

import br.com.fiap.adapter.integration.MercadoPagoServiceMock;
import br.com.fiap.adapter.repository.ClienteRepository;
import br.com.fiap.adapter.repository.PedidoRepository;
import br.com.fiap.adapter.repository.ProdutoRepository;
import br.com.fiap.adapter.repository.jpa.ClienteJpaRepository;
import br.com.fiap.adapter.repository.jpa.PedidoJpaRepository;
import br.com.fiap.adapter.repository.jpa.ProdutoJpaRepository;
import br.com.fiap.adapter.repository.mapper.ClienteMapper;
import br.com.fiap.adapter.repository.mapper.PedidoMapper;
import br.com.fiap.adapter.repository.mapper.ProdutoMapper;
import br.com.fiap.core.port.in.*;
import br.com.fiap.core.port.out.*;
import br.com.fiap.core.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjection {

    @Bean
    public FetchPaymentQRCodeOutputPort fetchCurrentAgeOfMajorityOutputPort() {
        return new MercadoPagoServiceMock();
    }

    @Bean
    public GeneratePaymentQRCodeInputPort fetchPaymentQRCodeOutputPort(FetchPaymentQRCodeOutputPort fetchPaymentQRCodeOutputPort) {
        return new GeneratePaymentQRCodeUseCase(fetchPaymentQRCodeOutputPort);
    }

    /*****************************************************
     * CLIENTE
     *****************************************************/

    @Bean
    public ClienteSaveOutputPort clienteSaveOutputPort(ClienteJpaRepository clienteJpaRepository,
                                                       ClienteMapper clienteMapper) {
        return new ClienteRepository(clienteJpaRepository,
                clienteMapper);
    }

    @Bean
    public ClienteLoadOutputPort clienteLoadOutputPort(ClienteJpaRepository clienteJpaRepository,
                                                       ClienteMapper clienteMapper) {
        return new ClienteRepository(clienteJpaRepository,
                clienteMapper);
    }

    @Bean
    public ClienteRegisterInputPort clienteRegisterInputPort(ClienteSaveOutputPort clienteSaveOutputPort) {
        return new ClienteRegisterUseCase(clienteSaveOutputPort);
    }

    @Bean
    public ClienteLoadInputPort clienteLoadInputPort(ClienteLoadOutputPort clienteLoadOutputPort) {
        return new ClienteLoadUseCase(clienteLoadOutputPort);
    }

    /*****************************************************
     * PRODUTO
     *****************************************************/

    @Bean
    public ProdutoSaveOutputPort produtoSaveOutputPort(ProdutoJpaRepository produtoJpaRepository,
                                                       ProdutoMapper produtoMapper) {
        return new ProdutoRepository(produtoJpaRepository,
                produtoMapper);
    }

    @Bean
    public ProdutoUpdateOutputPort produtoUpdateOutputPort(ProdutoJpaRepository produtoJpaRepository,
                                                           ProdutoMapper produtoMapper) {
        return new ProdutoRepository(produtoJpaRepository,
                produtoMapper);
    }

    @Bean
    public ProdutoDeleteOutputPort produtoDeleteOutputPort(ProdutoJpaRepository produtoJpaRepository,
                                                           ProdutoMapper produtoMapper) {
        return new ProdutoRepository(produtoJpaRepository,
                produtoMapper);
    }

    @Bean
    public ProdutoLoadOutputPort produtoLoadOutputPort(ProdutoJpaRepository produtoJpaRepository,
                                                       ProdutoMapper produtoMapper) {
        return new ProdutoRepository(produtoJpaRepository,
                produtoMapper);
    }

    @Bean
    public ProdutoCreateInputPort produtoCreateInputPort(ProdutoSaveOutputPort produtoSaveOutputPort) {
        return new ProdutoCreateUseCase(produtoSaveOutputPort);
    }

    @Bean
    public ProdutoUpdateInputPort produtoUpdateInputPort(ProdutoUpdateOutputPort produtoUpdateOutputPort) {
        return new ProdutoUpdateUseCase(produtoUpdateOutputPort);
    }

    @Bean
    public ProdutoDeleteInputPort produtoDeleteInputPort(ProdutoDeleteOutputPort produtoDeleteOutputPort) {
        return new ProdutoDeleteUseCase(produtoDeleteOutputPort);
    }

    @Bean
    public ProdutoLoadInputPort produtoLoadInputPort(ProdutoLoadOutputPort produtoLoadOutputPort) {
        return new ProdutoLoadUseCase(produtoLoadOutputPort);
    }

    /*****************************************************
     * PEDIDO
     *****************************************************/

    @Bean
    public PedidoSaveOutputPort pedidoSaveOutputPort(PedidoJpaRepository pedidoJpaRepository,
                                                     ClienteJpaRepository clienteJpaRepository,
                                                     ProdutoJpaRepository produtoJpaRepository,
                                                     PedidoMapper pedidoMapper) {
        return new PedidoRepository(pedidoJpaRepository,
                clienteJpaRepository,
                produtoJpaRepository,
                pedidoMapper);
    }

    @Bean
    public PedidoLoadOutputPort pedidoLoadOutputPort(PedidoJpaRepository pedidoJpaRepository,
                                                     ClienteJpaRepository clienteJpaRepository,
                                                     ProdutoJpaRepository produtoJpaRepository,
                                                     PedidoMapper pedidoMapper) {
        return new PedidoRepository(pedidoJpaRepository,
                clienteJpaRepository,
                produtoJpaRepository,
                pedidoMapper);
    }

    @Bean
    public PedidoCreateInputPort pedidoCreateInputPort(PedidoSaveOutputPort pedidoSaveOutputPort) {
        return new PedidoCreateUseCase(pedidoSaveOutputPort);
    }

    @Bean
    public PedidoLoadInputPort pedidoLoadInputPort(PedidoLoadOutputPort pedidoLoadOutputPort) {
        return new PedidoLoadUseCase(pedidoLoadOutputPort);
    }

}
