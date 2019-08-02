package server;

import java.util.*;
import java.util.stream.Collectors;

class SessionStorage {
    private Collection<Session> sessionList =  new LinkedList<>();

    private SessionStorage() {
    }

    private Collection<Session> getSessions() {
        return sessionList;
    }

    Collection<Session> getWriterSessions() {
        return getSessions().stream()
                .filter(stream -> !stream.isReader())
                .collect(Collectors.toList());
    }

    void addSession(Session session) {
        sessionList.add(session);
    }

    //=========================

    private static SessionStorage theOne = new SessionStorage();

    static SessionStorage getTheOne() {
        return theOne;
    }
}
