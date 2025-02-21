package com.beatspace.beatspace;

import com.beatspace.beatspace.models.Comentario;
import com.beatspace.beatspace.repository.ComentarioRepository;
import com.beatspace.beatspace.services.ComentarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @InjectMocks
    private ComentarioService comentarioService;

    @Mock
    private ComentarioRepository comentarioRepository;

    /*
    @Test
    public void deveriaInserirConta() {
        Comentario comentario1 = new Comentario("teste teste teste" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");
        Mockito.doReturn(null).when(comentarioRepository).findById(comentario1.getId());
        comentarioService.salvar(comentario1);

        Mockito.verify(comentarioRepository).save(comentario1);
    }

     */






}
