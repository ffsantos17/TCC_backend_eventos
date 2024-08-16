package br.com.projeto.api_projeto.controller;

import br.com.projeto.api_projeto.models.Documento;
import br.com.projeto.api_projeto.repositories.DocumentoRepository;
import br.com.projeto.api_projeto.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    FileServiceImpl fileServiceImpl;

    @GetMapping("/buscar")
    public ResponseEntity<Documento> buscarPorId(@RequestHeader("id") int id){
        Documento result = documentoRepository.buscarPorId(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarDocumento(@RequestParam(required = false) MultipartFile file, @RequestHeader("nomeDoc") String nomeDocumento, @RequestHeader("pModelo") boolean pModelo){
        Documento documento = new Documento();
        documento.setNome(nomeDocumento);
        documento.setPossuiModelo(pModelo);
        if (file != null && !file.isEmpty()) {
            documento.setModelo(file.getOriginalFilename());
        } else {
            documento.setModelo(null); // Ou defina como achar melhor
        }
//        documento.setModelo(file.getOriginalFilename());
        int result = documentoRepository.criar(documento);
        if(result == 1){
            if (file != null && !file.isEmpty()) {
                boolean up = fileServiceImpl.upload(file, "uploads/modelos");
//                if (up) {
//                    return new ResponseEntity<>("Documento criado com sucesso", HttpStatus.OK);
//                }
            }
            return new ResponseEntity<>("Documento criado com sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>(String.valueOf(result), HttpStatus.EXPECTATION_FAILED);
    }

    @PostMapping("/upload_documento")
    public ResponseEntity<String> uploadDocumento(@RequestParam MultipartFile file, @RequestHeader("id") int idDocumentoUsuario) {
        String message = "";
        try {
            boolean up = fileServiceImpl.upload(file, "uploads/documentos_usuario");

            if (up) {
                documentoRepository.entregarDocumento(file.getOriginalFilename(), idDocumentoUsuario);
            }
            message = "Upload com sucesso: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Não foi possivel realizar o upload do arquivo: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/upload_modelo")
    public ResponseEntity<String> uploadModelo(@RequestParam MultipartFile file, @RequestHeader("id") int idDocumentoUsuario) {
        String message = "";
        try {
            boolean up = fileServiceImpl.upload(file,"uploads/modelos");

            if (up) {
                documentoRepository.entregarDocumento(file.getOriginalFilename(), idDocumentoUsuario);
            }
            message = "Upload com sucesso: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Não foi possivel realizar o upload do arquivo: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @PostMapping("/upload_imagem")
    public ResponseEntity<String> uploadImagem(@RequestParam MultipartFile file, @RequestHeader("id") int idDocumentoUsuario) {
        String message = "";
        try {
            boolean up = fileServiceImpl.upload(file,"uploads/imagens");

            if (up) {
                documentoRepository.entregarDocumento(file.getOriginalFilename(), idDocumentoUsuario);
            }
            message = "Upload com sucesso: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Não foi possivel realizar o upload do arquivo: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/download/{folder}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename, @PathVariable String folder) {
        Resource file = fileServiceImpl.download(filename, folder);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/delete")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@RequestHeader("idDocumentoUsuario") int idDocumentoUsuario, @RequestHeader("nomeAnexo") String nomeAnexo) {
        boolean retorno = fileServiceImpl.delete(nomeAnexo);
        if (retorno) {
            documentoRepository.removerDocumento(idDocumentoUsuario);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso: " + nomeAnexo);
    }

    @GetMapping(value = "/listar", produces = "application/json; charset=utf-8" )
    public List<Documento> listarDocumentos() {
        return documentoRepository.buscarTodos();
    }

    @GetMapping(value = "/img/{dir}/{img:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String dir, @PathVariable String img) throws IOException {


        byte[] bytes = Files.readAllBytes(Paths.get("uploads/" + dir + "/" + img));

        return bytes;


    }
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
