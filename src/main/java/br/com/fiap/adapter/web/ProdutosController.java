package br.com.fiap.adapter.web;

import br.com.fiap.core.domain.exception.PersistenceException;
import br.com.fiap.core.domain.exception.ProdutoNotFoundException;
import br.com.fiap.core.domain.model.ProdutoCategoria;
import br.com.fiap.core.domain.model.request.ProdutoCreateRequest;
import br.com.fiap.core.domain.model.request.ProdutoUpdateRequest;
import br.com.fiap.core.domain.model.response.AppResponse;
import br.com.fiap.core.domain.model.response.ProdutoResponse;
import br.com.fiap.core.domain.model.response.ResponseUtil;
import br.com.fiap.core.port.in.ProdutoCreateInputPort;
import br.com.fiap.core.port.in.ProdutoDeleteInputPort;
import br.com.fiap.core.port.in.ProdutoLoadInputPort;
import br.com.fiap.core.port.in.ProdutoUpdateInputPort;
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
@RequestMapping(value = "/v1/produtos")
@Tag(name = "API Produtos v1.0", description = "Endpoints para cadastro, alteração, remoção e busca de produtos")
public class ProdutosController {

    private final ProdutoCreateInputPort produtoCreateInputPort;
    private final ProdutoUpdateInputPort produtoUpdateInputPort;
    private final ProdutoDeleteInputPort produtoDeleteInputPort;
    private final ProdutoLoadInputPort produtoLoadInputPort;

    public ProdutosController(ProdutoCreateInputPort produtoCreateInputPort,
                              ProdutoUpdateInputPort produtoUpdateInputPort,
                              ProdutoDeleteInputPort produtoDeleteInputPort,
                              ProdutoLoadInputPort produtoLoadInputPort) {
        this.produtoCreateInputPort = produtoCreateInputPort;
        this.produtoUpdateInputPort = produtoUpdateInputPort;
        this.produtoDeleteInputPort = produtoDeleteInputPort;
        this.produtoLoadInputPort = produtoLoadInputPort;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Objeto contendo as informações do produto cadastrado"),
            @ApiResponse(responseCode = "404", description = "Ocorreu um erro ao processar o cadastro do produto"),
    })
    public ResponseEntity<AppResponse<ProdutoResponse>> createProduct(HttpServletRequest request,
                                                                      @RequestBody ProdutoCreateRequest produtoCreateRequest) {
        try {
            var produtoResponse = produtoCreateInputPort.create(produtoCreateRequest);
            produtoResponse.setPath(String.format("%s/%s", request.getRequestURL().toString(),
                    produtoResponse.getId()));

            AppResponse<ProdutoResponse> response = ResponseUtil.success(produtoResponse,
                    "Produto cadastrado com sucesso",
                    produtoResponse.getPath());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PersistenceException ex) {
            AppResponse<ProdutoResponse> response = ResponseUtil.error(ex.getCause().getLocalizedMessage(),
                    ex.getLocalizedMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto contendo as informações do produto alterado"),
            @ApiResponse(responseCode = "400", description = "Ocorreu um erro ao processar a alteração do produto"),
    })
    public ResponseEntity<AppResponse<ProdutoResponse>> updateProduct(HttpServletRequest request,
                                                                      @RequestBody ProdutoUpdateRequest produtoUpdateRequest) {
        try {
            var produtoResponse = produtoUpdateInputPort.update(produtoUpdateRequest);
            produtoResponse.setPath(String.format("%s/%s", request.getRequestURL().toString(),
                    produtoResponse.getId()));

            AppResponse<ProdutoResponse> response = ResponseUtil.success(produtoResponse,
                    "Produto alterado com sucesso",
                    produtoResponse.getPath());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (PersistenceException ex) {
            AppResponse<ProdutoResponse> response = ResponseUtil.error(ex.getCause().getLocalizedMessage(),
                    ex.getLocalizedMessage(),
                    HttpStatus.BAD_REQUEST.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado na base de dados"),
    })
    public ResponseEntity<AppResponse<ProdutoResponse>> deleteProduct(HttpServletRequest request,
                                                                      @PathVariable int id) {
        try {
            produtoDeleteInputPort.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ProdutoNotFoundException ex) {
            AppResponse<ProdutoResponse> response = ResponseUtil.error(ex.getCause().getLocalizedMessage(),
                    ex.getLocalizedMessage(),
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista contendo as informações dos produtos"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado na base de dados"),
    })
    public ResponseEntity<AppResponse<List<ProdutoResponse>>> getProductList(HttpServletRequest request) {
        var produtoResponse = produtoLoadInputPort.loadAll();

        if (produtoResponse.isEmpty()) {
            AppResponse<List<ProdutoResponse>> response = ResponseUtil.error("Nenhum produto encontrado na base de dados",
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        produtoResponse.forEach(produto -> produto.setPath(String.format("%s/%s",
                request.getRequestURL().toString().replace("/all", ""),
                produto.getId())));

        AppResponse<List<ProdutoResponse>> response = ResponseUtil.success(produtoResponse,
                "Lista de produtos encontrados na base de dados",
                request.getRequestURL().toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista contendo as informações dos produtos"),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado na base de dados"),
    })
    public ResponseEntity<AppResponse<List<ProdutoResponse>>> getProductListByCategory(HttpServletRequest request,
                                                                                       @RequestParam ProdutoCategoria categoria) {
        var produtoResponse = produtoLoadInputPort.loadByCategoria(categoria);

        if (produtoResponse.isEmpty()) {
            AppResponse<List<ProdutoResponse>> response = ResponseUtil.error("Nenhum produto encontrado na base de dados",
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        produtoResponse.forEach(produto -> produto.setPath(String.format("%s/%s", request.getRequestURL().toString(), produto.getId())));

        AppResponse<List<ProdutoResponse>> response = ResponseUtil.success(produtoResponse,
                "Lista de produtos encontrados na base de dados",
                request.getRequestURL().toString());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Objeto contendo as informações do produto"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    })
    public ResponseEntity<AppResponse<ProdutoResponse>> getProductById(HttpServletRequest request,
                                                                       @PathVariable int id) {
        try {
            var produtoResponse = produtoLoadInputPort.loadById(id);
            produtoResponse.setPath(request.getRequestURL().toString());

            AppResponse<ProdutoResponse> response = ResponseUtil.success(produtoResponse,
                    "Produto encontrado na base de dados",
                    produtoResponse.getPath());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ProdutoNotFoundException ex) {

            AppResponse<ProdutoResponse> response = ResponseUtil.error(ex.getLocalizedMessage(),
                    "Não foi possível processar a requisição",
                    HttpStatus.NOT_FOUND.value(),
                    request.getRequestURL().toString());

            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
