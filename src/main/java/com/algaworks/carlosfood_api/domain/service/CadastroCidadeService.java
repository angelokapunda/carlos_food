package com.algaworks.carlosfood_api.domain.service;

import com.algaworks.carlosfood_api.domain.exception.CidadeNaoEncotradoException;
import com.algaworks.carlosfood_api.domain.exception.EntidadeEmUsoException;
import com.algaworks.carlosfood_api.domain.exception.EstadoNaoEncotradoException;
import com.algaworks.carlosfood_api.domain.model.Cidade;
import com.algaworks.carlosfood_api.domain.model.Estado;
import com.algaworks.carlosfood_api.domain.repository.CidadeRepository;
import com.algaworks.carlosfood_api.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCidadeService {

    private static final String MSG_ENTIDADE_EM_USO = "A cidade de código %d está em uso";

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
                .orElseThrow(() -> new EstadoNaoEncotradoException(estadoId));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }

    public Cidade buscarOuFalhar(Long cidadeId) {
        return cidadeRepository.findById(cidadeId).orElseThrow(
                () -> new CidadeNaoEncotradoException(cidadeId));
    }

    public void excluir (Long cidadeId) {
        try {
            var cidade = buscarOuFalhar(cidadeId);
            cidadeRepository.delete(cidade);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format(MSG_ENTIDADE_EM_USO, cidadeId)
            );
        }
    }
}
