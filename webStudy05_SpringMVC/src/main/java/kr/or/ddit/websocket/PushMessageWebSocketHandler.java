package kr.or.ddit.websocket;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class PushMessageWebSocketHandler extends TextWebSocketHandler{

	@Resource(name = "wsSessionList")
	List<WebSocketSession> wsSessionList;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		wsSessionList.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		wsSessionList.remove(session);
	}
	
}
