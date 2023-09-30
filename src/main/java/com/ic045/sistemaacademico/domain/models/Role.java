package com.ic045.sistemaacademico.domain.models;

public class Role {

    public enum Type{
        ADMIN,
        COORDENADOR_DE_CURSO,
        PROFESSOR,
        ALUNO,
    }
    public enum Status{
        EMAIL_CHECK,
        WAITING_APPROVAL,
        APPROVED,
        DENIED
    }
    public enum Shift{
        MATUTINO,
        VESPERTINO,
        NOTURNO
    }
    public enum Area{
        CIENCIAS_SOCIAIS_HUMANAS("CSH"),
        CIENCIAS_EXATAS_TECNOLOGIA("CET"),
        CIENCIAS_DA_SAUDE("CDS"),
        ARTES_E_HUMANIDADES("AEH"),
        NEGOCIOS_E_ECONOMIA("NEE"),
        EDUCACAO("EDU"),
        CIENCIAS_DA_NATUREZA("CDN"),
        DIREITO("DIR"),
        CIENCIAS_DA_SAUDE_E_BEM_ESTAR("CSBE"),
        CIENCIAS_SOCIAIS_APLICADAS("CSA"),
        CIENCIAS_AMBIENTAIS("CAM"),
        CIENCIAS_AGRICOLAS_E_VETERINARIAS("CAV"),
        LETRAS("LET");

        private String code;
        Area(String code) {
            this.code = code;
        }
        public String getCode(){
            return code;
        }
    }

    public enum Date {
        Segunda("SEG"),
        Terca("TER"),
        Quarta("QUA"),
        Quinta("QUI"),
        Sexta("SEX"),
        Sabado("SAB"),
        Domingo("DOM");

        private String abreviacao;

        Date(String abreviacao) {
            this.abreviacao = abreviacao;
        }

        public String getCodeDate() {
            return abreviacao;
        }

    }
}
