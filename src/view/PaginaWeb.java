package view;

import model.Onibus;
import model.Poltrona;
import model.Reserva;

import java.util.ArrayList;

public class PaginaWeb {

    public String getHtml(Onibus onibus, ArrayList<Reserva> reservas, String retorno) {
        return "" +
            "<!DOCTYPE html>\n" +
            "<html lang=\"pt-BR\">" +
                head +
                body(onibus, reservas, retorno) +
            "</html>";
    }

    public final String head = "" +
        "<head>\n" +
            "<meta charset=\"utf-8\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
            "\n" +
            "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\n" +
                "integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\n" +
            "\n" +
            "<title>Reserva de passagens</title>\n" +
            "\n" +
            "<style>\n" +
                "#onibus {\n" +
                    "margin-top: 50px;\n" +
                    "margin-bottom: 60px;\n" +
                    "border: 2px solid #b8b8b8;\n" +
                    "border-bottom-left-radius: 72px;\n" +
                    "border-top-left-radius: 72px;\n" +
                    "width: 850px;\n" +
                    "position: relative;\n" +
                    "right: 1%;\n" +
                "}\n" +
                "#motorista {\n" +
                    "border: 4px solid #b8b8b8;\n" +
                    "border-bottom-left-radius: 70px;\n" +
                    "border-top-left-radius: 70px;\n" +
                    "/* padding-right: 100px; */\n" +
                    "width: 100px;\n" +
                    "height: 275px;\n" +
                    "background-color: #000000;\n" +
                "}\n" +
                "#fila-topo {\n" +
                    "width: 100%;\n" +
                    "height: 35%;\n" +
                    "position: absolute;\n" +
                    "top: 5%;\n" +
                    "left: 14%;\n" +
                "}\n" +
                "#fila-chao {\n" +
                    "width: 100%;\n" +
                    "height: 35%;\n" +
                    "position: absolute;\n" +
                    "bottom: 7%;\n" +
                    "left: 14%;\n" +
                "}\n" +
                "#linha-topo-fila-topo {\n" +
                    "height: 40px;\n" +
                    "position: absolute;\n" +
                    "width: 100%;\n" +
                    "height: 50%;\n" +
                "}\n" +
                "#linha-chao-fila-topo {\n" +
                    "height: 40px;\n" +
                    "position: absolute;\n" +
                    "bottom: 0;\n" +
                    "width: 100%;\n" +
                    "height: 50%;\n" +
                "}\n" +
                "#linha-topo-fila-chao {\n" +
                    "height: 40px;\n" +
                    "position: absolute;\n" +
                    "width: 100%;\n" +
                    "height: 50%;\n" +
                "}\n" +
                "#linha-chao-fila-chao {\n" +
                    "height: 40px;\n" +
                    "position: absolute;\n" +
                    "bottom: 0;\n" +
                    "width: 100%;\n" +
                    "height: 50%;\n" +
                "}\n" +
                ".poltrona {\n" +
                    "float: left;\n" +
                    "margin-left: 1%;\n" +
                    "margin-top: 0.75%;\n" +
                "}\n" +
                ".box-onibus {\n" +
                    "display: flex;\n" +
                    "justify-content: center;\n" +
                    "align-items: center;\n" +
                    "flex-direction: row;\n" +
                "}\n" +
            "</style>\n" +
        "</head>\n";

    public String body(Onibus onibus, ArrayList<Reserva> reservas, String retorno) {
        return "" +
            "<body>\n" +
                nav +
                container(onibus, reservas, retorno) +
                scrips() +
            "</body>\n";
    }

    public String nav = "" +
        "<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
            "<div class=\"container\">\n" +
                "<a class=\"navbar-brand\" href=\"\"><strong>RESERVA DE PASSAGENS</strong></a>\n" +
            "</div>\n" +
        "</nav>\n";

    public String container(Onibus onibus, ArrayList<Reserva> reservas, String retorno) {
        return "" +
            "<div class=\"container\">\n" +
                "<br><br>\n" +
                "<h2 class=\"text-center\">Selecione a poltrona</h2>\n" +
                formulario +
                onibus(onibus) +
                alert(retorno) +
                tabelaReservas(reservas) +
            "</div>\n";
    }

    public String formulario = "" +
        "<br>\n" +
        "<form class=\"row g-3 \" action=\"/reservar\">\n" +
            "<div class=\"form-group\">\n" +
                "<div class=\"col-md-6 offset-md-3\">\n" +
                    "<label for=\"passageiro\" class=\"visually-hidden\">Nome do passageiro</label>\n" +
                    "<input type=\"text\" class=\"form-control\" name=\"passageiro\" id=\"passageiro\" required placeholder=\"Nome do passageiro\">\n" +
                "</div>\n" +
            "</div>\n" +

            "<input type=\"hidden\" name=\"poltrona\" value=\"\">\n" +

            "<div class=\"form-group\">\n" +
                "<div class=\"col-md-6 offset-md-3\">\n" +
                    "<button type=\"submit\" class=\"btn btn-primary mb-3\">Reservar</button>\n" +
                "</div>\n" +
            "</div>\n" +
        "</form>";

    public String onibus(Onibus onibus) {

        String bus = "";
        String[] disabled = new String[onibus.getPoltronas().size()];
        String[] btn = new String[onibus.getPoltronas().size()];
        String[] dataBsTarget = new String[onibus.getPoltronas().size()];

        for (int i = 0; i < onibus.getPoltronas().size(); i++) {
            if (onibus.getPoltronas().get(i).isLivre()) {
                btn[i] = "btn btn-success";
                dataBsTarget[i] = "#exampleModal";
                disabled[i] = "";
            } else {
                btn[i] = "btn btn-secondary";
                dataBsTarget[i] = "#exampleModal2";
                disabled[i] = "disabled";
            }
        }

        bus = "" +
            "<div class=\"box-onibus\">\n" +
            "\n" +
            "<div id=\"onibus\">\n" +
            "\n" +
            "<div id=\"motorista\"></div>\n" +
            "\n" +
            "<div id=\"fila-topo\">\n" +
            "\n" +
            "<div id=\"linha-topo-fila-topo\">\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[0] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[0] + "\" data-bs-whatever=\"1\"\n " +
            "onclick=\"setPoltrona(1)\" " + disabled[0] + ">01</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[4] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[4] + "\" data-bs-whatever=\"5\"\n" +
            "onclick=\"setPoltrona(5)\" " + disabled[4] + ">05</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[8] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[8] + "\" data-bs-whatever=\"9\"\n" +
            "onclick=\"setPoltrona(9)\" " + disabled[8] + ">09</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[12] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[12] + "\" data-bs-whatever=\"13\"\n" +
            "onclick=\"setPoltrona(13)\" " + disabled[12] + ">13</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[16] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[16] + "\" data-bs-whatever=\"17\"\n" +
            "onclick=\"setPoltrona(17)\" " + disabled[16] + ">17</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[20] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[20] + "\" data-bs-whatever=\"21\"\n" +
            "onclick=\"setPoltrona(21)\" " + disabled[20] + ">21</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[24] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[24] + "\" data-bs-whatever=\"25\"\n" +
            "onclick=\"setPoltrona(25)\" " + disabled[24] + ">25</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[28] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[28] + "\" data-bs-whatever=\"29\"\n" +
            "onclick=\"setPoltrona(29)\" " + disabled[28] + ">29</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[32] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[32] + "\" data-bs-whatever=\"33\"\n" +
            "onclick=\"setPoltrona(33)\" " + disabled[32] + ">33</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[36] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[36] + "\" data-bs-whatever=\"37\"\n" +
            "onclick=\"setPoltrona(37)\" " + disabled[36] + ">37</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[40] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[40] + "\" data-bs-whatever=\"41\"\n" +
            "onclick=\"setPoltrona(41)\" " + disabled[40] + ">41</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[44] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[44] + "\" data-bs-whatever=\"45\"\n" +
            "onclick=\"setPoltrona(45)\" " + disabled[44] + ">45</button>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"linha-chao-fila-topo\">\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[1] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[1] + "\" data-bs-whatever=\"2\" " +
            "onclick=\"setPoltrona(2)\" " + disabled[1] + ">02</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[5] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[5] + "\" data-bs-whatever=\"6\" " +
            "onclick=\"setPoltrona(6)\" " + disabled[5] + ">06</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[9] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[9] + "\" data-bs-whatever=\"10\"\n" +
            "onclick=\"setPoltrona(10)\" " + disabled[9] + ">10</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[13] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[13] + "\" data-bs-whatever=\"14\"\n" +
            "onclick=\"setPoltrona(14)\" " + disabled[13] + ">14</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[17] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[17] + "\" data-bs-whatever=\"18\"\n" +
            "onclick=\"setPoltrona(18)\" " + disabled[17] + ">18</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[21] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[21] + "\" data-bs-whatever=\"22\"\n" +
            "onclick=\"setPoltrona(22)\" " + disabled[21] + ">22</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[25] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[25] + "\" data-bs-whatever=\"26\"\n" +
            "onclick=\"setPoltrona(26)\" " + disabled[25] + ">26</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[29] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[29] + "\" data-bs-whatever=\"30\"\n" +
            "onclick=\"setPoltrona(30)\" " + disabled[29] + ">30</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[33] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[33] + "\" data-bs-whatever=\"34\"\n" +
            "onclick=\"setPoltrona(34)\" " + disabled[33] + ">34</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[37] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[37] + "\" data-bs-whatever=\"38\"\n" +
            "onclick=\"setPoltrona(38)\" " + disabled[37] + ">38</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[41] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[41] + "\" data-bs-whatever=\"42\"\n" +
            "onclick=\"setPoltrona(42)\" " + disabled[41] + ">42</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[45] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[45] + "\" data-bs-whatever=\"46\"\n" +
            "onclick=\"setPoltrona(46)\" " + disabled[45] + ">46</button>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"fila-chao\">\n" +
            "\n" +
            "<div id=\"linha-topo-fila-chao\">\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[3] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[3] + "\" data-bs-whatever=\"4\" " +
            "onclick=\"setPoltrona(4)\" " + disabled[3] + ">04</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[7] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[7] + "\" data-bs-whatever=\"8\" " +
            "onclick=\"setPoltrona(8)\" " + disabled[7] + ">08</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[11] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[11] + "\" data-bs-whatever=\"12\"\n" +
            "onclick=\"setPoltrona(12)\" " + disabled[11] + ">12</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[15] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[15] + "\" data-bs-whatever=\"16\"\n" +
            "onclick=\"setPoltrona(16)\" " + disabled[15] + ">16</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[19] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[19] + "\" data-bs-whatever=\"20\"\n" +
            "onclick=\"setPoltrona(20)\" " + disabled[19] + ">20</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[23] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[23] + "\" data-bs-whatever=\"24\"\n" +
            "onclick=\"setPoltrona(24)\" " + disabled[23] + ">24</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[27] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[27] + "\" data-bs-whatever=\"28\"\n" +
            "onclick=\"setPoltrona(28)\" " + disabled[27] + ">28</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[31] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[31] + "\" data-bs-whatever=\"32\"\n" +
            "onclick=\"setPoltrona(32)\" " + disabled[31] + ">32</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[35] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[35] + "\" data-bs-whatever=\"36\"\n" +
            "onclick=\"setPoltrona(36)\" " + disabled[35] + ">36</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[39] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[39] + "\" data-bs-whatever=\"40\"\n" +
            "onclick=\"setPoltrona(40)\" " + disabled[39] + ">40</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[43] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[43] + "\" data-bs-whatever=\"44\"\n" +
            "onclick=\"setPoltrona(44)\" " + disabled[43] + ">44</button>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "<div id=\"linha-chao-fila-chao\">\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[2] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[2] + "\" data-bs-whatever=\"3\" " +
            "onclick=\"setPoltrona(3)\" " + disabled[2] + ">03</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[6] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[6] + "\" data-bs-whatever=\"7\" " +
            "onclick=\"setPoltrona(7)\" " + disabled[6] + ">07</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[10] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[10] + "\" data-bs-whatever=\"11\"\n" +
            "onclick=\"setPoltrona(11)\" " + disabled[10] + ">11</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[14] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[14] + "\" data-bs-whatever=\"15\"\n" +
            "onclick=\"setPoltrona(15)\" " + disabled[14] + ">15</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[18] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[18] + "\" data-bs-whatever=\"19\"\n" +
            "onclick=\"setPoltrona(19)\" " + disabled[18] + ">19</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[22] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[22] + "\" data-bs-whatever=\"23\"\n" +
            "onclick=\"setPoltrona(23)\" " + disabled[22] + ">23</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[26] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[26] + "\" data-bs-whatever=\"27\"\n" +
            "onclick=\"setPoltrona(27)\" " + disabled[26] + ">27</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[30] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[30] + "\" data-bs-whatever=\"31\"\n" +
            "onclick=\"setPoltrona(31)\" " + disabled[30] + ">31</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[34] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[34] + "\" data-bs-whatever=\"35\"\n" +
            "onclick=\"setPoltrona(35)\" " + disabled[34] + ">35</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[38] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[38] + "\" data-bs-whatever=\"39\"\n" +
            "onclick=\"setPoltrona(39)\" " + disabled[38] + ">39</button>\n" +
            "</div>\n" +
            "\n" +
            "<div class=\"poltrona\">\n" +
            "<button type=\"button\" class=\"" + btn[42] + "\" data-bs-toggle=\"dataBsTarget\"\n" +
            "data-bs-target=\"" + dataBsTarget[42] + "\" data-bs-whatever=\"43\"\n" +
            "onclick=\"setPoltrona(43)\" " + disabled[42] + ">43</button>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "</div>\n";

        return bus;
    }

    public String alert(String retorno) {

        String mensagem;

        String svg = "" +
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
            "</svg>\n";

        if (retorno.equals("OK")) {
            mensagem = "" +
                "<div class=\"alert alert-success alert-dismissible fade show\" role=\"alert\">\n" +
                    "<svg class=\"bi flex-shrink-0 me-2\" width=\"24\" height=\"24\" role=\"img\" aria-label=\"Success:\">\n" +
                        "<use xlink:href=\"#check-circle-fill\" />\n" +
                    "</svg>\n" +
                    "<strong>OK!</strong> Sua reserva foi efetuada com sucesso!\n" +
                    "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                "</div>\n";
        } else if (retorno.equals("Ops")) {
            mensagem = "" +
                "<div class=\"alert alert-warning alert-dismissible fade show\" role=\"alert\">\n" +
                    "<svg class=\"bi flex-shrink-0 me-2\" width=\"24\" height=\"24\" role=\"img\" aria-label=\"Warning:\">\n" +
                        "<use xlink:href=\"#exclamation-triangle-fill\" />\n" +
                    "</svg>\n" +
                    "<strong>Ops!</strong> Não foi possível realizar a reserva!\n" +
                    "<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button>\n" +
                "</div>\n";
        } else {
            mensagem = "";
        }

        return svg + mensagem;
    }

    public String tabelaReservas(ArrayList<Reserva> reservas) {

        String linhas = "";
        String tabela;

        for (Reserva reserva : reservas) {
            linhas += "" +
                "<tr>\n" +
                    "<th scope=\"row\">" + reserva.getPoltrona().getNumero() + "</th>\n" +
                    "<td>" + reserva.getPassageiro().getNome() + "</td>\n" +
                    "<td>" + reserva.getPassageiro().getIp() + "</td>\n" +
                    "<td>" + reserva.getData() + "</td>\n" +
                    "<td>" + reserva.getHora() + "</td>\n" +
                "</tr>\n";
        }

        tabela = "" +
            "<table class=\"table table-bordered table-striped\">\n" +
                "<thead>\n" +
                    "<tr>\n" +
                        "<th scope=\"col\">Poltrona</th>\n" +
                        "<th scope=\"col\">Passageiro</th>\n" +
                        "<th scope=\"col\">IP</th>\n" +
                        "<th scope=\"col\">Data</th>\n" +
                        "<th scope=\"col\">Hora</th>\n" +
                    "</tr>\n" +
                "</thead>\n" +
                "<tbody>\n" +
                    linhas +
                "</tbody>\n" +
            "</table>";

        return tabela;
    }

    public String scrips() {
        return "" +
            "<script>\n" +
                "var exampleModal = document.getElementById('exampleModal')\n" +
                "exampleModal.addEventListener('show.bs.modal', function (event) {\n" +
                    "var button = event.relatedTarget\n" +
                    "var recipient = button.getAttribute('data-bs-whatever')\n" +
                    "var modalTitle = exampleModal.querySelector('.modal-title')\n" +
                    "var modalBodyInput = exampleModal.querySelector('.modal-body input')\n" +
                    "modalTitle.textContent = 'Poltrona ' + recipient\n" +
                    "var poltrona = exampleModal.querySelector();\n" +
                "})\n" +
                "\n" +
                "function setPoltrona(id) {\n" +
                    "document.querySelector(\"[name='poltrona']\").value = id;\n" +
                "}\n" +
            "</script>\n" +
            "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\"\n" +
                "integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\"\n" +
                "crossorigin=\"anonymous\"></script>\n";
    }
}
