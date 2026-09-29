package io.github.joaogabriel0908.icompras.pedidos.service;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.Response;
import io.github.joaogabriel0908.icompras.pedidos.repository.PedidoRepository;
import io.github.joaogabriel0908.icompras.pedidos.client.ServicoBancarioClient;
import io.github.joaogabriel0908.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.joaogabriel0908.icompras.pedidos.model.DadosPagamento;
import io.github.joaogabriel0908.icompras.pedidos.model.Pedido;
import io.github.joaogabriel0908.icompras.pedidos.model.enums.StatusPedido;
import io.github.joaogabriel0908.icompras.pedidos.model.enums.TipoPagamento;
import io.github.joaogabriel0908.icompras.pedidos.model.exception.ItemNaoEncontradoException;
import io.github.joaogabriel0908.icompras.pedidos.model.exception.ValidationException;
import io.github.joaogabriel0908.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.joaogabriel0908.icompras.pedidos.validator.PedidoValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor 
@Slf4j 
public class PedidoService {
    
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoValidator pedidoValidator;
    private final ServicoBancarioClient servicoBancarioClient;

    @Transactional 
    public Pedido criarPedido(Pedido pedido) {
        pedidoValidator.validarPedido(pedido);
        realizarPersistencia(pedido);
        enviarSolicitacaoPagamento(pedido);
        return pedido;
    }

    private void enviarSolicitacaoPagamento(Pedido pedido) {
        String solicitarPagamento = servicoBancarioClient.solicitarPagamento(pedido);
        System.out.println(solicitarPagamento);
        pedido.setChavePagamento(solicitarPagamento);
    }

    private void realizarPersistencia(Pedido pedido) {
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(pedido.getItens());
    }

    public void atualizarStatusPagamento(
        Long codigoPedido, String chavePagamento, boolean success, String observacao) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findByCodigoAndChavePagamento(codigoPedido, chavePagamento);

        if (pedidoOptional.isEmpty()) {
            var message = "Pedido não encontrado para o código " + 
                codigoPedido + " e chave de pagamento " + chavePagamento;
            log.error(message);
            throw new ItemNaoEncontradoException(message);
            // return; // This line is no longer needed because the exception will interrupt the flow
        }

        Pedido pedido = pedidoOptional.get();

        if (success) {
            pedido.setStatus(StatusPedido.PAGO);
        } else {
            pedido.setStatus(StatusPedido.ERRO_PAGAMENTO);
            pedido.setObservacoes(observacao);
        }
        pedidoRepository.save(pedido);
    }

    @Transactional 
    public void adicionarNovoPagamento(
        Long codigoPedido, String dadosCartao, TipoPagamento tipoPagamento) {
            var pedidoEncontrado = pedidoRepository.findById(codigoPedido);

            if (pedidoEncontrado.isEmpty()) {
                throw new ItemNaoEncontradoException("Pedido não encontrado para o código " + codigoPedido);
            }

            Pedido pedido = pedidoEncontrado.get();
            DadosPagamento dadosPagamento = new DadosPagamento();
            dadosPagamento.setDados(dadosCartao);
            dadosPagamento.setTipoPagamento(tipoPagamento);

            pedido.setDadosPagamento(dadosPagamento);
            pedido.setStatus(StatusPedido.REALIZADO);
            pedido.setObservacoes("Novo pagamento realizado, aguardando o novo processamento");

            String novaChavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
            pedido.setChavePagamento(novaChavePagamento);
            pedidoRepository.save(pedido);
        }
}
