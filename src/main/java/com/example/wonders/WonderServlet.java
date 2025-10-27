package main.java.com.example.wonders;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/wonder")
public class WonderServlet extends HttpServlet {

    private static final class Wonder {
        String id, name, desc, img;
        Wonder(String id, String name, String desc, String img) {
            this.id = id; this.name = name; this.desc = desc; this.img = img;
        }
    }

    private Map<String, Wonder> wonders = new HashMap<>();

    @Override
    public void init() throws ServletException {
        wonders.put("1", new Wonder("1", "Taj Mahal",
                "Built by Mughal Emperor Shah Jahan in memory of Mumtaz Mahal. Located in Agra, India.",
                "images/taj_mahal.jpg"));
        wonders.put("2", new Wonder("2", "Great Wall of China",
                "A series of fortifications made of stone and earth, built across the historical northern borders of China.",
                "images/great_wall.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || !wonders.containsKey(id)) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }
        Wonder w = wonders.get(id);
        req.setAttribute("name", w.name);
        req.setAttribute("desc", w.desc);
        req.setAttribute("img", w.img);
        req.getRequestDispatcher("/details.jsp").forward(req, resp);
    }
}
