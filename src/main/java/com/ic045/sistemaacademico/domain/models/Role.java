package com.ic045.sistemaacademico.domain.models;

public class Role {
	
	public enum Sala{
		PAFI_101,
		PAFI_102,
		PAFI_103,
		PAFI_104,
		PAFI_105,
		PAFI_106,
		PAFI_107,
		PAFI_108,
		PAFI_109,
		PAFI_110,
		PAFI_111,
		PAFI_112,
		PAFI_113,
		PAFI_114,
		PAFI_115,
		PAFI_116,
		PAFI_117,
		PAFI_118,
		PAFI_119,
		PAFI_120,
		PAFI_201,
		PAFI_202,
		PAFI_203,
		PAFI_204,
		PAFI_205,
		PAFI_206,
		PAFI_207,
		PAFI_208,
		PAFI_209,
		PAFI_210,
		PAFI_211,
		PAFI_212,
		PAFI_213,
		PAFI_214,
		PAFI_215,
		PAFI_216,
		PAFI_217,
		PAFI_218,
		PAFI_219,
		PAFI_220,
		PAFI_301,
		PAFI_302,
		PAFI_303,
		PAFI_304,
		PAFI_305,
		PAFI_306,
		PAFI_307,
		PAFI_308,
		PAFI_309,
		PAFI_310,
		PAFI_311,
		PAFI_312,
		PAFI_313,
		PAFI_314,
		PAFI_315,
		PAFI_316,
		PAFI_317,
		PAFI_318,
		PAFI_319,
		PAFI_320
	}

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
        SEGUNDA("SEG"),
        TERCA("TER"),
        QUARTA("QUA"),
        QUINTA("QUI"),
        SEXTA("SEX"),
        SABADO("SAB"),
        DOMINGO("DOM");

        private String code;

        Date(String abreviacao) {
            this.code = abreviacao;
        }

        public String getCodeDate() {
            return code;
        }

    }
}
