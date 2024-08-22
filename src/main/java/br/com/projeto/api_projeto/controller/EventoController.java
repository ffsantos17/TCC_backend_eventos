package br.com.projeto.api_projeto.controller;

import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.models.ParticipanteEvento;
import br.com.projeto.api_projeto.repositories.DocumentoRepository;
import br.com.projeto.api_projeto.repositories.EventoRepository;
import br.com.projeto.api_projeto.repositories.UsuarioRepository;
import br.com.projeto.api_projeto.services.FileServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    DocumentoRepository documentoRepository;


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    FileServiceImpl fileServiceImpl;

    @GetMapping(value = "/listar", produces = "application/json; charset=utf-8" )
    public ResponseEntity<List<Evento>> listarEventos() {
        try {
            List<Evento> eventos = new ArrayList<Evento>();

//            if (title == null)
                eventoRepository.buscarTodos().forEach(eventos::add);
//            else
//                eventoRepository.findByTitleContaining(title).forEach(eventos::add);
            eventos.forEach(e ->{
                e.setDocumentos(eventoRepository.buscarDocumentoEvento(e.getId()));
            });
            if (eventos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(eventos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<String> cadastrarEvento(@RequestParam MultipartFile file, @RequestHeader("json") String json, @RequestHeader("documentos") String documentos){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Evento evento = objectMapper.readValue(json, Evento.class);
            String[] splits =  documentos.replace("[","").replace("]","").split(",");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(splits));
            eventoRepository.salvar(evento, arrayList);
            fileServiceImpl.upload(file, "uploads/imagens");
            return new ResponseEntity<>("Evento criado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/atualizar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> atualizarEvento(@PathVariable("id") int id, @RequestBody Evento evento){

        Evento _evento = eventoRepository.buscarPorId(id);
        if (_evento != null) {
            _evento.setId(id);
            _evento.setNome(evento.getNome());
            _evento.setLink(evento.getLink());
            _evento.setVagas(evento.getVagas());

            eventoRepository.atualizar(_evento);
            return new ResponseEntity<>("Evento atualizado.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Não foi possivel editar o evento id=" + id, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deletar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletarEvento(@PathVariable int id){
        try {
            int result = eventoRepository.deletarPorId(id);
            if (result == 0) {
                return new ResponseEntity<>("Não foi possivel localizar o evento id " + id, HttpStatus.OK);
            }
            return new ResponseEntity<>("Evento deletado com sucesso.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Não foi possivel deletar o evento.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/obter-vagas")
    public ResponseEntity<Integer> obterVagas(@RequestHeader("id") int id){
        int result = eventoRepository.buscarVagasEvento(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Evento> buscarPorId(@RequestHeader("id") int id){
        Evento evento = eventoRepository.buscarPorId(id);
//        evento.setDocumentos(eventoRepository.buscarDocumentoEvento(evento.getId()));
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @GetMapping("/inserir-visita")
    public ResponseEntity<Integer> inserirVisita(@RequestHeader("id") int id){
        int result = eventoRepository.inserirVisita(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/alterar_status")
    public ResponseEntity<Integer> alterarStatus(@RequestHeader("id") int id, @RequestHeader("status_id") int status_id){
        int result = eventoRepository.alterarStatusInscricao(id,status_id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/buscar-participantes")
    public ResponseEntity<List<ParticipanteEvento>> buscarParticipantes(@RequestHeader("id") int id){
        List<ParticipanteEvento> participantes = eventoRepository.buscarParticipanteEvento(id);
        participantes.forEach(p ->{
            p.setUsuario(usuarioRepository.buscarPorId(p.getIdUsuario()));
        });
        return new ResponseEntity<>(participantes, HttpStatus.OK);
    }

}
