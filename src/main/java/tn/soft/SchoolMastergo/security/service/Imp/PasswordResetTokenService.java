package tn.soft.SchoolMastergo.security.service.Imp;


import org.springframework.http.ResponseEntity;

import tn.soft.SchoolMastergo.security.models.ActionRsetpW;
import tn.soft.SchoolMastergo.security.models.ChangePasswordResetRequest;
import tn.soft.SchoolMastergo.security.models.Resetpwemail;












public interface PasswordResetTokenService {
	String verifyEmail(Resetpwemail rep);


ResponseEntity<String> verifyOtp(ActionRsetpW actionrsetPW);


   ResponseEntity<String> changePasswordHandler(
           ChangePasswordResetRequest changePasswordResetRequest  );


}

