package services;

import java.util.List;
import models.Request;

public class RequestService {
    private static final ExchangeService exchange = new ExchangeService();

    public static void sendRequest(int senderId, int bookRequestedId, int bookOfferedId) throws Exception {
        exchange.sendExchangeRequest(senderId, bookRequestedId, bookOfferedId);
    }

    public static List<Request> getRequestsForUser(int userId) throws Exception {
        // Combine incoming and outgoing or pick one; MainGUI expects for current user, we'll return incoming.
        return exchange.getIncomingRequests(userId);
    }

    // ✅ NEW METHODS
    public static void acceptRequest(int requestId) throws Exception {
        exchange.acceptRequest(requestId);
    }

    public static void rejectRequest(int requestId) throws Exception {
        exchange.rejectRequest(requestId);
    }
}
