package kz.bassina;

public class Main {
    public static void main(String[] args) {
        GiftDrawer giftDrawer = new GiftDrawer();

        System.out.println("Розыгрыш 1");
        giftDrawer.addToy(new Toy(1, "Constructor", 2, 20));
        giftDrawer.addToy(new Toy(2, "Robot", 2, 20));
        giftDrawer.addToy(new Toy(3, "Doll", 6, 60));
        play(giftDrawer, 10);

        System.out.println("Розыгрыш 2");
        giftDrawer.addToy(new Toy(4, "Ball", 2, 10));
        giftDrawer.addToy(new Toy(4, "Car", 2, 30));
        play(giftDrawer, 1);
        giftDrawer.changeToyWeight(4, 20);
        play(giftDrawer, 3);

    }

    private static void play(GiftDrawer giftDrawer, int times) {
        for (int i = 0; i < times; i++) {
            try {
                Toy giftedToy = giftDrawer.drawToy();
                System.out.println("Вы выиграли подарок: " + giftedToy.getName() + "\n");
            } catch (Exception e) {
                System.out.println("Возникла ошибка во время розыгрыша призов: " + e.getMessage());
            }
        }
    }
}
