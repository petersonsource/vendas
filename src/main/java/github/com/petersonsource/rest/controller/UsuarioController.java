package github.com.petersonsource.rest.controller;

import github.com.petersonsource.domain.entity.Usuario;
import github.com.petersonsource.exception.RegraNegocioException;
import github.com.petersonsource.exception.UsuarioSenhaInvalidaException;
import github.com.petersonsource.rest.dto.CredenciaisDTO;
import github.com.petersonsource.rest.dto.TokenDTO;
import github.com.petersonsource.security.jwt.JwtService;
import github.com.petersonsource.service.impl.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return usuarioService.salvar(usuario);

    }

    @PostMapping("/auth")
    public TokenDTO autentica(@RequestBody CredenciaisDTO credenciais) {
        try{

            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);

            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | UsuarioSenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e .getMessage());
        }
    }

}
