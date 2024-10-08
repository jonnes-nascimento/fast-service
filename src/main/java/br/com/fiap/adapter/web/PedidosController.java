package br.com.fiap.adapter.web;

import br.com.fiap.core.domain.exception.PedidoNotFoundException;
import br.com.fiap.core.domain.exception.PersistenceException;
import br.com.fiap.core.domain.model.PedidoStatus;
import br.com.fiap.core.domain.model.request.PedidoCreateRequest;
import br.com.fiap.core.domain.model.response.AppResponse;
import br.com.fiap.core.domain.model.response.PedidoResponse;
import br.com.fiap.core.domain.model.response.ResponseUtil;
import br.com.fiap.core.port.in.PedidoCreateInputPort;
import br.com.fiap.core.port.in.PedidoLoadInputPort;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/pedidos")
@Tag(name = "API Pedidos v1.0", description = "Endpoints para cadastro, alteração e busca de pedidos")
public class PedidosController {

    private final PedidoCreateInputPort pedidoCreateInputPort;
    private final PedidoLoadInputPort pedidoLoadInputPort;

    public PedidosController(PedidoCreateInputPort pedidoCreateInputPort,
                             PedidoLoadInputPort pedidoLoadInputPort) {
        this.pedidoCreateInputPort = pedidoCreateInputPort;
        this.pedidoLoadInputPort = pedidoLoadInputPort;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Objeto contendo as informações do pedido cadastrado"),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro ao processar o cadastro do pedido"),
    })
    public ResponseEntity<AppResponse<PedidoResponse>> createPedido(HttpServletRequest request,
                                                                    @RequestBody PedidoCreateRequest pedidoCreateRequest) {
        try {
            var pedidoResponse = pedidoCreateInputPort.create(pedidoCreateRequest);
            pedidoResponse.setPath(String.format("%s/%s", request.getRequestURL().toString(),
                    pedidoResponse.getId()));

            AppResponse<PedidoResponse> response = ResponseUtil.success(pedidoResponse,
                    "Pedido cadastrado com sucesso",
                    pedidoResponse.getPath());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PersistenceException ex) {
            AppResponse<PedidoResponse> response = ResponseUtil.error(ex.getCause().getLocalizedMessage(),
                    ex.getLocalizedMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista contendo as informações dos pedidos"),
            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado na base de dados"),
    })
    public ResponseEntity<AppResponse<List<PedidoResponse>>> getPedidoList(HttpServletRequest request) {
        var pedidoResponse = pedidoLoadInputPort.loadAll();

        if (pedidoResponse.isEmpty()) {
            AppResponse<List<PedidoResponse>> response = ResponseUtil.error("Nenhum pedido encontrado na base de dados",
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        pedidoResponse.forEach(pedido -> pedido.setPath(String.format("%s/%s", request.getRequestURL().toString().replace("/all", ""),
                pedido.getNumero())));

        AppResponse<List<PedidoResponse>> response = ResponseUtil.success(pedidoResponse,
                "Lista de pedidos encontrados na base de dados",
                request.getRequestURL().toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{numero}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto contendo as informações do pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
    })
    public ResponseEntity<AppResponse<PedidoResponse>> getPedidoById(HttpServletRequest request,
                                                                     @PathVariable String numero) {
        try {
            var pedidoResponse = pedidoLoadInputPort.loadByNumero(numero);
            pedidoResponse.setPath(request.getRequestURL().toString());

            AppResponse<PedidoResponse> response = ResponseUtil.success(pedidoResponse,
                    "Pedido encontrado na base de dados",
                    pedidoResponse.getPath().replace(numero, "all"));

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PedidoNotFoundException ex) {

            AppResponse<PedidoResponse> response = ResponseUtil.error(ex.getLocalizedMessage(),
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto contendo as informações do pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
    })
    public ResponseEntity<AppResponse<List<PedidoResponse>>> getPedidoByStatus(HttpServletRequest request,
                                                                               @RequestParam PedidoStatus status) {
        var pedidoResponse = pedidoLoadInputPort.loadByStatus(status);
        if (pedidoResponse.isEmpty()) {
            AppResponse<List<PedidoResponse>> response = ResponseUtil.error("Nenhum pedido com status informado foi encontrado na base de dados",
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString() + "?status=" + status.name());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        pedidoResponse.forEach(pedido -> pedido.setPath(String.format("%s/%s", request.getRequestURL().toString(), pedido.getNumero())));

        AppResponse<List<PedidoResponse>> response = ResponseUtil.success(pedidoResponse,
                "Lista de pedidos encontrados na base de dados",
                request.getRequestURL().toString() + "?status=" + status.name());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
