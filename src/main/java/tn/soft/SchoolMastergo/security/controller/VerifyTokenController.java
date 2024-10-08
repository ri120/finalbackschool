package tn.soft.SchoolMastergo.security.controller;

import static tn.soft.SchoolMastergo.security.service.UserService.applicationUrl;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import tn.soft.SchoolMastergo.security.listener.RegistrationCompleteEventListener;
import tn.soft.SchoolMastergo.security.models.Response;
import tn.soft.SchoolMastergo.security.models.VerificationToken;
import tn.soft.SchoolMastergo.security.service.VerificationTokenService;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/verify")
@CrossOrigin(origins = "http://localhost:4200")
public class VerifyTokenController{

    private final VerificationTokenService tokenService;
    private final RegistrationCompleteEventListener eventListener;

    @GetMapping("/email")
    public ResponseEntity<Response> verifyEmail(String token) {
        return tokenService.verifyEmail(token);
    }

    @GetMapping("/resend-verification-token")
    public String resendVerificationToken(
            @RequestParam("token") String oldToken,
            final HttpServletRequest request
    ) throws MessagingException, UnsupportedEncodingException {
        VerificationToken verificationToken = tokenService.generateNewVerificationToken(oldToken);
        resendVerificationTokenEmail(applicationUrl(request), verificationToken);
        return "A new verification link has been sent to your email, check to activate your verification";
    }

    private void resendVerificationTokenEmail(
            String applicationUrl,
            VerificationToken verificationToken
    ) throws MessagingException, UnsupportedEncodingException {
        String url = applicationUrl+"/api/v1/verify/email?token="+verificationToken.getToken();
        eventListener.sendVerificationEmail(url);
    }
}
