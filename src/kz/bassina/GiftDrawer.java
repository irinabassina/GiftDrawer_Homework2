package kz.bassina;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiftDrawer {
    private List<Toy> toys = new ArrayList<>();

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public double changeToyWeight(int toyID, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyID) {
                double oldWeight = toy.getWeight();
                toy.setWeight(newWeight);
                return oldWeight;
            }
        }
        throw new IndexOutOfBoundsException("Игрушка с ID=" + toyID + " не найдена");
    }

    public Toy drawToy() throws IOException {
        if (toys.isEmpty()) {
            throw new IndexOutOfBoundsException("Игрушек в автомате нет");
        }

        double totalWeight = 0;
        for (Toy toy : toys) {
            totalWeight += toy.getWeight() * toy.getQuantity();
        }

        Random rand = new Random();
        double randomNumber = rand.nextDouble() * totalWeight;

        int giftToyIdx = 0;
        for (int i = 0; i < toys.size(); i++) {
            Toy currentToy = toys.get(i);
            if (randomNumber < currentToy.getWeight() * currentToy.getQuantity()) {
                giftToyIdx = i;
                break;
            }
        }

        Toy giftToy = toys.get(giftToyIdx);
        writeGiftedToyToFile(giftToy);
        reduceToy(giftToyIdx);

        return giftToy;
    }

    private void reduceToy(int giftToyIdx) {
        Toy giftToy = toys.get(giftToyIdx);
        int giftToyQuantity = giftToy.getQuantity();
        if (giftToyQuantity > 1) {
            giftToy.setQuantity(giftToyQuantity - 1);
        } else {
            toys.remove(giftToyIdx);
        }
    }

    private void writeGiftedToyToFile(Toy giftToy) throws IOException {
        FileWriter writer = new FileWriter("gifted_toys.txt", true);
        writer.write("ID=" + giftToy.getId() + " name=" + giftToy.getName() + "\n");
        writer.close();
    }
}
