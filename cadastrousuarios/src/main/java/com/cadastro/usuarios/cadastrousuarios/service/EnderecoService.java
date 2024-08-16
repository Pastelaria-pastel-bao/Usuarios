package com.cadastro.usuarios.cadastrousuarios.service;

import com.cadastro.usuarios.dtos.EnderecoDto;
import com.cadastro.usuarios.cadastrousuarios.entities.Usuarios;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Bairro;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Cidade;
import com.cadastro.usuarios.cadastrousuarios.entities.EnderecoPersist;
import com.cadastro.usuarios.cadastrousuarios.entities.endereco.Estado;
import com.cadastro.usuarios.cadastrousuarios.repositories.BairroRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.CidadeRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.EnderecoPersistRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.EstadoRepository;
import com.cadastro.usuarios.cadastrousuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnderecoService {


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
    public EnderecoPersist adicionarEndereco(EnderecoDto enderecoDto) {

            Long usuarioId = enderecoDto.getUsuarioId();
            if (usuarioId == null) {
                throw new RuntimeException("ID do usuário não pode ser nulo");
            }


            Optional<Usuarios> usuarioOptional = usuarioRepository.findById(usuarioId);
            if (!usuarioOptional.isPresent()) {
                throw new RuntimeException("Usuário não encontrado com o ID: " + usuarioId);
            }
            Usuarios usuario = usuarioOptional.get();


            Estado estado = estadoRepository.findByNome(enderecoDto.getEstado())
                    .orElseGet(() -> {
                        Estado novoEstado = new Estado();
                        novoEstado.setNome(enderecoDto.getEstado());
                        return estadoRepository.save(novoEstado);
                    });
        if (estado == null || estado.getNome()=="") {
            throw new RuntimeException("Erro ao salvar o estado. estado retornou como null.");
        }

            Cidade cidade = cidadeRepository.findByNomeAndEstado(enderecoDto.getCidade(), estado)
                    .orElseGet(() -> {
                    Cidade novaCidade = new Cidade();
                    novaCidade.setNome(enderecoDto.getCidade());
                    novaCidade.setEstado(estado);
                    return cidadeRepository.save(novaCidade);
                });
        if (cidade == null || cidade.getNome()=="")  {
            throw new RuntimeException("Erro ao salvar o cidade. cidade retornou como null.");
        }


        Bairro bairro = bairroRepository.findByNomeAndCidade(enderecoDto.getBairro(), cidade)
                .orElseGet(() -> {
                    Bairro novoBairro = new Bairro();
                    novoBairro.setNome(enderecoDto.getBairro());
                    novoBairro.setCidade(cidade);
                    return bairroRepository.save(novoBairro);
                });

        if (bairro == null || bairro.getNome()=="") {
            throw new RuntimeException("Erro ao salvar o bairro. Bairro retornou como null.");
        }


        EnderecoPersist enderecoPersist = new EnderecoPersist();
        enderecoPersist.setUsuarioId(usuarioId);
        enderecoPersist.setBairro(bairro);
        enderecoPersist.setNumero(enderecoDto.getNumero());
        enderecoPersist.setComplemento(enderecoDto.getComplemento());

        EnderecoPersist enderecoSalvo = enderecoPersistRepository.save(enderecoPersist);


        usuario.setEndereco(enderecoSalvo);
        usuarioRepository.save(usuario);

        return enderecoSalvo;
    }
}
