package com.hotel.hotelreservationsystem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.hotelreservationsystem.model.Stock;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class PopupWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    private final Random r = new Random();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        float oldPrice = 0.0f;

        // Publishing new euro value every one second for 100 times
        for (int i = 0; i < 100; i ++) {
            //Calculating Random euro value between 4 to 5
            float euroPrice = 4 + r.nextFloat() * (5 - 4);
            float roundedPrice = (float) (Math.round(euroPrice * 100.0) / 100.0);

            //Creating a Stock Object
            Stock stock = new Stock("Euro",
                    "https://upload.wikimedia.org/wikipedia/commons/5/5c/Euro_symbol_black.svg",
                    roundedPrice);

            //Finding whether the stock price increased or decreased
            if (roundedPrice > oldPrice){
                stock.setIncreased(true);
            }
            oldPrice = roundedPrice;

            //Sending StockPrice
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(stock));
            session.sendMessage(message);
            Thread.sleep(2000);
        }
        sessions.add(session);
    }
}
