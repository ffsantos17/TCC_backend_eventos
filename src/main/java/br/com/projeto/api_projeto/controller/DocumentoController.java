package br.com.projeto.api_projeto.controller;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.models.Evento;
import br.com.projeto.api_projeto.repositories.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.awt.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    DocumentoRepository documentoRepository;

    @GetMapping("/buscar")
    public ResponseEntity<Documento> buscarPorId(@RequestHeader("id") int id){
        Documento result = documentoRepository.buscarPorId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
//
//    @GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<Documento> listarDocumentos() {
//        return documentoRepository.findAll();
//    }
//
//    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Documento cadastrarDocumento(@RequestBody Documento documento){
//        Documento addDocumento = new Documento();
//
//        addDocumento.setNome(documento.getNome());
//        addDocumento.setPossuiModelo(documento.isPossuiModelo());
//        addDocumento.setModelo(documento.getModelo());
//        addDocumento.setDataCriacao(new Date());
//        addDocumento.setExcluido(false);
//
//        return documentoRepository.save(addDocumento);
//    }
//
//    @PutMapping(value = "/att", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Documento atualizarDocumento(@RequestBody Documento documento){
//            Documento getDocumento = documentoRepository.findById((long) documento.getId()).orElseThrow();
//
//            Documento updateDoc = new Documento();
//
//            updateDoc.setId(documento.getId());
//            updateDoc.setNome(documento.getNome());
//            updateDoc.setPossuiModelo(documento.isPossuiModelo());
//            updateDoc.setModelo(documento.getModelo());
//            updateDoc.setDataCriacao(documento.getDataCriacao());
//            updateDoc.setExcluido(documento.isExcluido());
//
//            return documentoRepository.save(updateDoc);
//    }
//
//    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Documento deletarDocumento(@PathVariable Long id){
//        Documento getDocumento = documentoRepository.findById(id).orElseThrow();
//        documentoRepository.delete(getDocumento);
//        return  getDocumento;
//    }
}
