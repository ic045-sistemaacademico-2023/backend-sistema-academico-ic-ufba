package com.ic045.sistemaacademico.services;
import com.ic045.sistemaacademico.domain.models.OportunidadeMatricula;
import com.ic045.sistemaacademico.repositories.OportunidadeMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class EmailAutomaticoService {

    @Autowired
    private OportunidadeMatriculaRepository oportunidadeMatriculaRepository;

    @Autowired
    private final EmailService emailService;

    public EmailAutomaticoService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 0 * * *") // Executa diariamente à meia-noite
    public void enviarEmailAutomatico() {
        // Lógica para verificar a data e enviar o e-mail
        List<OportunidadeMatricula> allOportunidade = oportunidadeMatriculaRepository.findAll();
        for(OportunidadeMatricula oportunidade : allOportunidade){
            Timestamp dataAtual = new Timestamp(System.currentTimeMillis());

            if (dataAtual.after(oportunidade.getDataFinal())) {
                // Envie o e-mail
                String destinatario = oportunidade.getCoordenador().getEmail();
                String assunto = "Fechamento de oportunidade";
                String corpo = "A oportunidade de matrícula " + oportunidade.getNome() + " foi fechada";

                emailService.sendEmail(destinatario, assunto, corpo);
            }
        }

    }
}
