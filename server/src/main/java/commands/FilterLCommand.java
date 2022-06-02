package commands;


import interaction.Request;
import interaction.Response;
import manager.CommandManager;
import manager.exception.EmptyFieldCommandException;
import model.Product;

import java.util.LinkedHashSet;

/**
 * выводит элементы, значение поля price которых меньше заданного
 */

public class FilterLCommand extends AbstractCommand {
    private LinkedHashSet<model.Product> set;
    CommandManager commandManager;


    public FilterLCommand(LinkedHashSet<model.Product> set, CommandManager commandManager) {
        super("filter_less_than_price", "price", "output the elements whose value of the price " +
                "field is less than the given value");
        this.set = set;
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Request req) {
        StringBuilder sb = new StringBuilder();

        try {
            if (req.getParams() != null) {
                double price = Double.parseDouble(req.getParams().trim());
                long count = set.stream().filter(product -> {
                    boolean b = product.getPrice() < price;
                    if (b) {
                        sb.append(product);
                    }
                    return b;
                }).count();
//                for (Product item : set) {
//                    if (price > item.getPrice()) {
//                        sb.append(item);
//                        count++;
//                    }
//                }
                commandManager.addToHistory(getName());
                return new Response<>(true, "Displayed elements, the value of the price field is less than the specified one.", sb+
                        "\nThe number of elements that satisfy the condition: " + count);
            } else
                throw new EmptyFieldCommandException("Exception: This command needs the value \" price \"");
        } catch (EmptyFieldCommandException e) {
            System.out.println(e.getMessage());
            return new Response(false, e.getMessage());
        }

    }
}