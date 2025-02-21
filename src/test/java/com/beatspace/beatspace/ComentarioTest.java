package com.beatspace.beatspace;
import com.beatspace.beatspace.models.Comentario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComentarioTest {
    private Comentario comentario1;

    @BeforeEach
    public void config() {
        comentario1 = new Comentario("teste teste teste" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");
    }

    @Test
    public void deveriaLancarExcecaoSeTextoMuitoLongo(){
        StringBuilder builder = new StringBuilder();

        // Adicionando 251 caracteres de "A" ao StringBuilder
        for (int i = 0; i < 251; i++) {
            builder.append("A");
        }
        Assertions.assertThrows(RuntimeException.class,() -> {comentario1.setTexto(builder.toString());});
    }

    @Test
    public void deveriaLancarExcecaoSeTextoMuitoLongoAoCriarComentario(){
        StringBuilder builder = new StringBuilder();

        // Adicionando 251 caracteres de "A" ao StringBuilder
        for (int i = 0; i < 251; i++) {
            builder.append("A");
        }
        Assertions.assertThrows(RuntimeException.class,() -> {new Comentario(builder.toString() , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});
    }


    @Test
    public void deveriaLancarExcecaoSeNotaNegativa(){

        Assertions.assertThrows(RuntimeException.class,() -> {comentario1.setNota(-1);});
    }

    @Test
    public void deveriaLancarExcecaoSeNotaMaiorQue10(){

        Assertions.assertThrows(RuntimeException.class,() -> {comentario1.setNota(11);});
    }

    @Test
    public void deveriaLancarExcecaoSeNotaNegativaAoConstruir(){

        Assertions.assertThrows(RuntimeException.class,() -> {new Comentario("teste4" , "123456", -1, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});
    }

    @Test
    public void deveriaLancarExcecaoSeNotaMaiorQue10AoConstruir(){
        Assertions.assertThrows(RuntimeException.class,() -> {new Comentario("teste4" , "123456", 11, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});

    }

    @Test
    public void deveriaLancarExcecaoParaDataNaoConvertivel(){
        Assertions.assertThrows(RuntimeException.class,() -> {comentario1.setData("teste teste teste teste");});
    }


    @Test
    public void deveriaLancarExcecaoParaDataNaoConvertivelAoConstruir(){
        Assertions.assertThrows(RuntimeException.class,() -> {new Comentario("teste4" , "123456", 10, "Exemplo" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});

    }




}
