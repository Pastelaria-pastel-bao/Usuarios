package com.cadastro.usuarios.cadastrousuarios.feing;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "servicoemail", url="${servicoemail.api.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody Email email);
}
