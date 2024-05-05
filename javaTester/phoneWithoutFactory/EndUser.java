package phoneWithoutFactory;

public class EndUser {
    public static void main(String[] args) {
        // quan 3 - iphone
        Iphone iphone = new Iphone();
        System.out.println(iphone.getIphone14());
        System.out.println(iphone.getIphone14Pro());
        System.out.println(iphone.getIphone14ProMax());

        // Quan 8 - Samsung
        Samsung samsung = new Samsung();
        System.out.println(samsung.getSamsungs23());
        System.out.println(samsung.getSamsungs23Plus());
        System.out.println(samsung.getSamsungs23Ultra());

        // Quan 5 - Reno
        Reno reno = new Reno();
        System.out.println(reno.getReno6());
        System.out.println(reno.getReno7());
        System.out.println(reno.getReno8());
    }
}
