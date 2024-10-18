package com.venture.rbac.util;

import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;
import com.venture.rbac.entity.User;

@Component
public class SessionManager {

    public void setUserSession(HttpSession session, User user) {
        session.setAttribute("user", user);
    }

    public User getUserFromSession(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    public void clearSession(HttpSession session) {
        session.invalidate();
    }
}