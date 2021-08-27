package view;

public class NotFound {

    public String getHtml() {
        String html = "" +
            "<div class=\"row\">\n" +
                "<div class=\"col-md-12\">\n" +
                    "<div class=\"alert alert-danger text-center\">\n" +
                        "<br>\n" +
                        "<svg class=\"bi flex-shrink-0 me-2\" width=\"100\" height=\"100\" role=\"img\" aria-label=\"Danger:\">\n" +
                            "<use xlink:href=\"#exclamation-triangle-fill\" />\n" +
                        "</svg>\n" +
                        "<br><br>\n" +
                        "<h1><strong>Página não encontrada!</strong></h1>\n" +
                        "\n" +
                        "<br>\n" +
                        "<form action=\"/index\">\n" +
                            "<button class=\"alert alert-outline-danger\" type=\"submit\">Voltar à reserva de passagens</button>\n" +
                        "</form>\n" +
                    "</div>\n" +
                "</div>\n" +
            "</div>\n";
        return html + new MensagemResposta().svg;
    }
}
