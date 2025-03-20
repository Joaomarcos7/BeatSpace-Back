package com.beatspace.beatspace;
import com.beatspace.beatspace.models.Comentarios.Resenha;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComentarioTest {
    private Resenha comentario1;

    @BeforeEach
    public void config() {
        comentario1 = new Resenha("teste teste teste","ophas" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");
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
        Assertions.assertThrows(RuntimeException.class,() -> {new Resenha(builder.toString() , "teste teste teste", "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});
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

        Assertions.assertThrows(RuntimeException.class,() -> {new Resenha("teste teste teste","ophas" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});
    }

    @Test
    public void deveriaLancarExcecaoSeNotaMaiorQue10AoConstruir(){
        Assertions.assertThrows(RuntimeException.class,() -> {new Resenha("teste teste teste","ophas" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});

    }

    @Test
    public void deveriaLancarExcecaoParaDataNaoConvertivel(){
        Assertions.assertThrows(RuntimeException.class,() -> {comentario1.setData("teste teste teste teste");});
    }


    @Test
    public void deveriaLancarExcecaoParaDataNaoConvertivelAoConstruir(){
        Assertions.assertThrows(RuntimeException.class,() -> {new Resenha("teste teste teste","ophas" , "123456", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");;});
    }


        @Test
        public void deveriaLancarExcecaoParaEmailNaoValido(){
            Assertions.assertThrows(RuntimeException.class,() -> {new Resenha("teste teste teste","ophas" ,"joaochaves", 5, "2025-02-21T15:58:09.524Z" , "3123fg24312g5" , "Fulano" , "http://img.com.br");});
        }


    }



