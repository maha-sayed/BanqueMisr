package banquemisr.chanllenge05.finance.controller;

import banquemisr.chanllenge05.finance.config.JwtUtil;
import banquemisr.chanllenge05.finance.entities.FusersEntity;
import banquemisr.chanllenge05.finance.repositories.FusersRepo;
import banquemisr.chanllenge05.finance.vo.FusersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banquemisr/Login")
public class LoginController {
    @Autowired
    private FusersRepo fusersRepo;
    private JwtUtil jwtUtil ;

    public LoginController(FusersRepo fusersRepo,JwtUtil jwtUtil) {
        this.fusersRepo = fusersRepo;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody FusersVo fusersVo){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(fusersVo.getUsername(), fusersVo.getPassword()));
        FusersEntity user = fusersRepo.findByUsername(fusersVo.getUsername());
        if(user != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("some error has occurred");

    }
}
