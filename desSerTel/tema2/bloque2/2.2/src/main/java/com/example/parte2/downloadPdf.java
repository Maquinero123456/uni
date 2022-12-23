package com.example.parte2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.json.simple.parser.ParseException;

import java.io.*;

//Creamos un Servlet para descargar pdf
@WebServlet(name = "Descargar", value = "/descargarPdf")
public class downloadPdf extends HttpServlet {

    private static final int BYTES_DOWNLOAD = 1024;

    //Creamos un metodo get
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        //Cargamos el archivo para descargar
        File file = new File(request.getParameter("enlace"));
        FileInputStream fileIn = new FileInputStream(file);
        ServletOutputStream out = response.getOutputStream();
        //Mandamos el archivo
        byte[] outputByte = new byte[4096];
        while(fileIn.read(outputByte, 0, 4096) != -1)
        {
            out.write(outputByte, 0, 4096);
        }
        //Actualizamos el numero de descargas de archivo
        try {
            controlLibros.updateDescargas(request.getParameter("titulo"), request.getParameter("autor"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        fileIn.close();
        out.flush();
        out.close();
    }
}