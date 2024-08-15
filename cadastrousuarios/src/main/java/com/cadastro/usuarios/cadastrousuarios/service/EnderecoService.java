package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.cadastrousuarios.entities.Endereco;
import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Bairro;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Cidade;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.EnderecoPersist;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Estado;
import com.cadastro.usuarios.cadastrousuarios.entities.repository.BairroRepository;
import com.cadastro.usuarios.cadastrousuarios.entities.repository.CidadeRepository;
import com.cadastro.usuarios.cadastrousuarios.entities.repository.EnderecoPersistRepository;
import com.cadastro.usuarios.cadastrousuarios.entities.repository.EstadoRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.EnderecoRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
   private  EstadoRepository estadoRepository;

    @Autowired
     private CidadeRepository cidadeRepository;

    @Autowired
    private BairroRepository bairroRepository;

    @Autowired
    private EnderecoPersistRepository enderecoPersistRepository;

    @Transactional
    public EnderecoPersist adicionarEndereco(Endereco endereco) {

            Long usuarioId = endereco.getUsuarioId();
            if (usuarioId == null) {
                throw new RuntimeException("ID do usuário não pode ser nulo");
            }


            Optional<Usuarios> usuarioOptional = usuarioRepository.findById(usuarioId);
            if (!usuarioOptional.isPresent()) {
                throw new RuntimeException("Usuário não encontrado com o ID: " + usuarioId);
            }
            Usuarios usuario = usuarioOptional.get();


            Estado estado = estadoRepository.findByNome(endereco.getEstado())
                    .orElseGet(() -> {
                        Estado novoEstado = new Estado();
                        novoEstado.setNome(endereco.getEstado());
                        return estadoRepository.save(novoEstado);
                    });
        if (estado == null) {
            throw new RuntimeException("Erro ao salvar o estado. estado retornou como null.");
        }

            Cidade cidade = cidadeRepository.findByNomeAndEstado(endereco.getCidade(), estado)
                    .orElseGet(() -> {
                    Cidade novaCidade = new Cidade();
                    novaCidade.setNome(endereco.getCidade());
                    novaCidade.setEstado(estado);
                    return cidadeRepository.save(novaCidade);
                });
        if (cidade == null) {
            throw new RuntimeException("Erro ao salvar o cidade. cidade retornou como null.");
        }


        Bairro bairro = bairroRepository.findByNomeAndCidade(endereco.getBairro(), cidade)
                .orElseGet(() -> {
                    Bairro novoBairro = new Bairro();
                    novoBairro.setNome(endereco.getBairro());
                    novoBairro.setCidade(cidade);
                    return bairroRepository.save(novoBairro);
                });

        if (bairro == null) {
            throw new RuntimeException("Erro ao salvar o bairro. Bairro retornou como null.");
        }


        EnderecoPersist enderecoPersist = new EnderecoPersist();
        enderecoPersist.setUsuarioId(usuarioId);
        enderecoPersist.setBairro(bairro);
        enderecoPersist.setNumero(endereco.getNumero());
        enderecoPersist.setComplemento(endereco.getComplemento());

        EnderecoPersist enderecoSalvo = enderecoPersistRepository.save(enderecoPersist);


        usuario.setEndereco(enderecoSalvo);
        usuarioRepository.save(usuario);

        return enderecoSalvo;
    }
}
