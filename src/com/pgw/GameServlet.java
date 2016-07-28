/**
 * Created by pooja on 6/11/16.
 */
// To save as "<CATALINA_HOME>\webapps\helloservlet\WEB-INF\src\mypkg\HelloWorldExample.java"
package com.pgw;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameServlet extends HttpServlet {
    Sudoku sudoku;

    @Override

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Allocate a output writer to write the response message into the network socket.
        PrintWriter out = response.getWriter();

        // Use a ResourceBundle for localized string in "LocalStrings_xx.properties" for i18n.
        // The request.getLocale() sets the locale based on the "Accept-Language" request header.
//        ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", request.getLocale());
        // To test other locales.
        //ResourceBundle rb = ResourceBundle.getBundle("LocalStrings", new Locale("fr"));

        // Write the response message, in an HTML document.
        try {
            response.setContentType("application/json");
            sudoku  = Sudoku.create();
            out.println(Sudoku.gameToJSON(sudoku));

        } finally {
            out.close();  // Always close the output writer
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // Allocate a output writer to write the response message into the network socket.
        PrintWriter out = response.getWriter();
        // Write the response message, in an HTML document.
        try {
            response.setContentType("application/json");

            String game = request.getParameterNames().nextElement();
            System.out.println("Solution: "+game);
            if(Sudoku.check(game, sudoku))
                out.println("{\"result\":\"true\"}");
            else {
                out.print(Sudoku.solutionToJSON(sudoku));
                System.out.println(sudoku.solutionToJSON(sudoku));
            }
            //System.out.println(request.getParameterNames());
//            System.out.println(Arrays.toString(request.getParameterValues("game")));
        } finally {
            out.close();  // Always close the output writer
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();
        try {
            response.setContentType("application/json");
            out.println(Sudoku.solutionToJSON(sudoku));
          //  System.out.println("Solution is:: "+Sudoku.solutionToJSON(sudoku));
        } finally {
            out.close();
        }
    }

}

