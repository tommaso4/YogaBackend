package com.YogApp.app.controller;
import com.YogApp.app.Exception.NotFoundException;
import com.YogApp.app.model.entites.User;
import com.YogApp.app.model.request.LoginReq;
import com.YogApp.app.model.request.UserReq;
import com.YogApp.app.security.JwtTools;
import com.YogApp.app.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCtrl {

    @Autowired
    private UserSvc userSvc;
    @Autowired
    @Qualifier("BCript")
    private PasswordEncoder encoder;
    @Autowired
    private JwtTools jwtTools;


    @PostMapping("/auth/register")
    public ResponseEntity<CustomResponse> register(@RequestBody @Validated UserReq userReq, BindingResult result){
        if (result.hasErrors()) {
            return CustomResponse.failure(result.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }
        User user = userSvc.saveUser(userReq);
        return CustomResponse.success(HttpStatus.OK.toString(),user,HttpStatus.OK);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<CustomResponse> login (@RequestBody @Validated LoginReq loginReq, BindingResult result)
            throws NotFoundException {
        if (result.hasErrors()) {
            return CustomResponse.failure(result.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
        }
        User user = userSvc.findUserByUsername(loginReq.getUsername());
        System.out.println(user.toString());
        if (encoder.matches(loginReq.getPassword(), user.getPassword())){
            String token = jwtTools.createToken(user);
            return CustomResponse.success(HttpStatus.OK.toString(),token,HttpStatus.OK);
        }
        return CustomResponse.failure("Username/Password do not match",HttpStatus.NOT_FOUND);
    }
}
