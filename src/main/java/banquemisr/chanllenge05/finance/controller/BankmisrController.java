package banquemisr.chanllenge05.finance.controller;

import banquemisr.chanllenge05.finance.config.JwtUtil;
import banquemisr.chanllenge05.finance.entities.FusersEntity;
import banquemisr.chanllenge05.finance.repositories.FusersRepo;
import banquemisr.chanllenge05.finance.services.AppUtils;
import banquemisr.chanllenge05.finance.services.FtasksService;
import banquemisr.chanllenge05.finance.vo.FusersVo;
import banquemisr.chanllenge05.finance.vo.JsonRequest;
import banquemisr.chanllenge05.finance.vo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/banquemisr/api")
@EnableAutoConfiguration
public class BankmisrController {
//    private final AuthenticationManager authenticationManager;

    @Autowired
    FtasksService ftasksService;
    @Autowired
    private AppUtils appUtils;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    public BankmisrController(FtasksService ftasksService,AppUtils appUtils){
        super();
        this.ftasksService = ftasksService;
        this.appUtils = appUtils;
//        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/doPost", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response doPost(@RequestBody JsonRequest request) {
        LOGGER.info("in do post");

        Response resp = new Response();
        boolean isValid;
        if (appUtils.isNotNullValidator(request.getMetadata().getCalcObject())) {

            resp = ftasksService.calculateObject(request);
            resp.setObjectdata(resp.getObjectdata());

            return resp;
        }

        if (request.getMetadata().getFuncMode().equals("I")) {
            isValid = ftasksService.validateItems(request);
            if (isValid) {
                isValid = ftasksService.doInsert(request);
                if (isValid) {
                    resp.setMessageCode(200);
                    resp.setMessageType(0);
                    resp.setMessageDescr("Saved Successfully");
                }
            }
        }else{
            resp.setMessageCode(400);
            resp.setMessageType(1);
            resp.setMessageDescr("Error");
        }
        return  resp;

    }

    @PutMapping(value = "/doPut", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response doPut(@RequestBody JsonRequest request) {
        LOGGER.info("in do put");

        Response resp = new Response();
        boolean isValid;
        if (appUtils.isNotNullValidator(request.getMetadata().getCalcObject())) {

            resp = ftasksService.calculateObject(request);
            resp.setObjectdata(resp.getObjectdata());

            return resp;
        }

        if (request.getMetadata().getFuncMode().equals("U")) {
            isValid = ftasksService.validateItems(request);
            if (isValid) {
                isValid = ftasksService.doUpdate(request);
                if (isValid) {
                    resp.setMessageCode(200);
                    resp.setMessageType(0);
                    resp.setMessageDescr("Saved Successfully");
                }
            }
        }else{
            resp.setMessageCode(400);
            resp.setMessageType(1);
            resp.setMessageDescr("Error");
        }
        return  resp;

    }
    @DeleteMapping(value = "/doDelete", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public Response doDelete(@RequestBody JsonRequest request) {
        LOGGER.info("in do delete");

        Response resp = new Response();
        boolean isValid;
        if (appUtils.isNotNullValidator(request.getMetadata().getCalcObject())) {

            resp = ftasksService.calculateObject(request);
            resp.setObjectdata(resp.getObjectdata());

            return resp;
        }

        if (request.getMetadata().getFuncMode().equals("D")) {
            isValid = ftasksService.validateItems(request);
            if (isValid) {
                isValid = ftasksService.doDelete(request);
                if (isValid) {
                    resp.setMessageCode(200);
                    resp.setMessageType(0);
                    resp.setMessageDescr("Saved Successfully");
                }
            }
        }else{
            resp.setMessageCode(400);
            resp.setMessageType(1);
            resp.setMessageDescr("Error");
        }
        return  resp;

    }



}
