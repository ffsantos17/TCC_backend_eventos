package br.com.projeto.api_projeto.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public void init();

    public boolean upload(MultipartFile file);

    public Resource download(String filename);

    public boolean delete(String filename);
}
