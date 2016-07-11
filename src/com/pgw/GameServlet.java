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

public class GameServlet extends HttpServlet {
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
            String [] games = new String [4];
            games [0]="{\"game\":[[\"1-1\",\"6\"],[\"1-3\",\"5\"],[\"1-6\",\"3\"]]}";
            games [1]="{\"game\":[[\"1-1\",\"5\"],[\"1-3\",\"5\"],[\"1-6\",\"3\"]]}";
            games [2]="{\"game\":[[\"1-1\",\"8\"],[\"1-3\",\"5\"],[\"1-6\",\"3\"]]}";
            games [3]="{\"game\":[[\"1-1\",\"2\"],[\"1-3\",\"5\"],[\"1-6\",\"3\"]]}";

           // int x = (int) (Math.random()*4);
            //out.println(games[x]);
            Sudoku sudoku  = Sudoku.create();
            out.println(sudoku.toJSON(sudoku.game));

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

            out.println("{\"result\":\"GameServlet.doPost\"}");

            System.out.println("GameServlet.doPost");
        } finally {
            out.close();  // Always close the output writer
        }
    }
}

