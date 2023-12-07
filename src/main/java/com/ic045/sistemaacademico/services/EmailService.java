package com.ic045.sistemaacademico.services;

import com.ic045.sistemaacademico.exception.custom.EmailException;
import com.ic045.sistemaacademico.utils.constants.ErrorMessages;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

	
	@Value("${spring.mail.host}")
	private String sendGridApiKey;
	
	@Value("${spring.mail.username}")
	private String sendGridFromEmail;
	 

    public boolean sendEmail(String to, String subject, String body){
        Email from = new Email(sendGridFromEmail);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(from, subject, toEmail, content);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            
            if(response.getStatusCode() == 202)
            	return true;
            
        } catch (IOException ex) {
           throw new EmailException(ErrorMessages.EMAIL_NOT_SENT.getMessage());
        }
		return false;
    }
}