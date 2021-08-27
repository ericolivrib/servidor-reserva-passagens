package view;

import model.Reserva;

public class MensagemResposta {

    public String getHtml(Reserva reserva, String nome, int numeroPoltrona) {
        String html;

        if (reserva.getPassageiro().getNome().equals(nome) && reserva.getPoltrona().getNumero() == numeroPoltrona) {
            html = "" +
                "<div class=\"row\">\n" +
                    "<div class=\"col-md-12\">\n" +
                        "<div class=\"alert alert-success text-center\">\n" +
                            "<br>\n" +
                            "<svg class=\"bi flex-shrink-0 me-2\" width=\"100\" height=\"100\" role=\"img\" aria-label=\"Success:\">\n" +
                                "<use xlink:href=\"#check-circle-fill\" />\n" +
                            "</svg>\n" +
                            "<br><br>\n" +
                            "<h1><strong>Reserva efetuada com sucesso!</strong></h1>\n" +
                            "\n" +
                            "<p><strong>Passageiro: </strong> " + nome + "</p>\n" +
                            "<p><strong>Poltrona: </strong> " + numeroPoltrona + "</p>\n" +
                            "<p><strong>IP: </strong> " + reserva.getPassageiro().getIp() + "</p>\n" +
                            "<p><strong>Data: </strong> " + reserva.getData() + "</p>\n" +
                            "<p><strong>Hora: </strong> " + reserva.getHora() + "</p>\n" +
                            "\n" +
                            "<form action=\"/index\">\n" +
                                "<button class=\"alert alert-outline-success\" type=\"submit\">Voltar à reserva de passagens</button>\n" +
                            "</form>\n" +
                        "</div>\n" +
                    "</div>\n" +
                "</div>\n";
        } else {
            html = "" +
                "<div class=\"row\">\n" +
                    "<div class=\"col-md-12\">\n" +
                        "<div class=\"alert alert-warning text-center\">\n" +
                            "<br>\n" +
                            "<svg class=\"bi flex-shrink-0 me-2\" width=\"100\" height=\"100\" role=\"img\" aria-label=\"Warning:\">\n" +
                                "<use xlink:href=\"#exclamation-triangle-fill\" />\n" +
                            "</svg>\n" +
                            "<br><br>\n" +
                            "<h1><strong>Não foi possível realizar a reserva!</strong></h1>\n" +
                            "\n" +
                            "<br>\n" +
                            "<form action=\"/index\">\n" +
                                "<button class=\"alert alert-outline-warning\" type=\"submit\">Voltar à reserva de passagens</button>\n" +
                            "</form>\n" +
                        "</div>\n" +
                    "</div>\n" +
                "</div>\n";
        }
        return html + svg;
    }

    public String svg = "" +
        "<svg xmlns=\"http://www.w3.org/2000/svg\" style=\"display: none;\">\n" +
            "<symbol id=\"check-circle-fill\" fill=\"currentColor\" viewBox=\"0 0 16 16\">\n" +
                "<path d=\"M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0zm-3.97-3.03a.75.75 0 0 0-1.08.022L7.477 9.417 \n" +
                "5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-.01-1.05z\" />\n" +
            "</symbol>\n" +
            "<symbol id=\"exclamation-triangle-fill\" fill=\"currentColor\" viewBox=\"0 0 16 16\">\n" +
                "<path d=\"M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 \n" +
                "0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 \n" +
                "5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z\" />\n" +
            "</symbol>\n" +
        "</svg>";
}
