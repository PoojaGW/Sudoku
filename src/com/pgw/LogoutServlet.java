package com.pgw;

/**
 * Created by pooja on 6/17/16.
 */

        import java.io.IOException;

        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "logoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        //response.setHeader("Expires", new java.util.Date().toString());//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
        response.setHeader("Expires", "0");//http://www.coderanch.com/t/541412/Servlets/java/Logout-servlet-button
        response.setHeader("Connection", "close");//http://javaarm.com/faces/display.xhtml?tid=2416&page=1#post_18198

        // Invalidate current HTTP session.
        // Will call JAAS LoginModule logout() method
        request.getSession().invalidate();
        //request.logout();   // It's a must
        System.out.println("Logging out");
        // Redirect the user to the secure web page.
        // Since the user is now logged out the
        // authentication form will be shown
        response.sendRedirect(request.getContextPath()
                + "/index.html");

    }

}