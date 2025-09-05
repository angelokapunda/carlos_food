package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.carlosfood_api.domain.model.Cidade;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.CidadeRepository;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {

    private static final String MSG_ENTIDADE_NAO_ENCONTRADA = "Não existe cadastro de cidade de código %d ";
    private static final String MSG_ENTIDADE_EM_USO = "";

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CadastroEstadoService cadastroEstado;

    public List<Cidade> listar() {
        return cidadeRepository.findAll();
    }


    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format("Não existe cadastro de estado com código %d", estadoId)));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
                () -> new EntidadeNaoEncontradaException(
                        String.format(MSG_ENTIDADE_NAO_ENCONTRADA, cidadeId)
                ));
    }

    public void excluir (Long cidadeId) {
        var cidade = buscarOuFalhar(cidadeId);
        cidadeRepository.delete(cidade);
    }
}
