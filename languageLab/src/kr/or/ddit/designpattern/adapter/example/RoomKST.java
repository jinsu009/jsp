package kr.or.ddit.designpattern.adapter.example;

public class RoomKST {
	public ConcentKST concent = new ConcentKST();
	
	public static void main(String[] args) {
		RoomKST room = new RoomKST();
		PluggableKST samsung = new SamsungProduct();
		PluggableKST lg = new LGProduct();
		PluggableCN xiaomi = new XiaomiProduct();
		
		room.concent.plugin(samsung);
		room.concent.plugin(lg);
//		room.concent.plugin(xiaomi);
		room.concent.plugin(new AdapterPlug(xiaomi));
	}
}









