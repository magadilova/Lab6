package manager;

import commands.AbstractCommand;

import java.io.Console;
import interaction.Request;
import interaction.Response;
import manager.exception.WrongFieldCommandException;
import model.Product;
import model.ProductDto;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class CommandManager {
    LinkedHashMap<String, AbstractCommand> commands = new LinkedHashMap<>();
    ArrayList<String> history = new ArrayList();

    public void addCommands(AbstractCommand[] commands) {
        for (AbstractCommand command : commands) {
            this.commands.put(command.getName(), command);
        }
    }

    public LinkedHashMap<String, AbstractCommand> getCommands() {
        return commands;
    }

    public void addToHistory(String command) {
        if (history.size() == 12) {
            history.remove(0);
            history.add(command);
        } else {
            history.add(command);
        }
    }

    public ArrayList<String> getHistory() {
//        int i = 1;
//        for (String element : history) {
//            System.out.println(i + ". " + element);
//            i++;
//        }
        return history;
    }

    public Response executeCommand(Request req) {
        try {
            if (!commands.containsKey(req.getCommand())) {
                throw new WrongFieldCommandException("No such command " + req.getCommand());
            }
            return commands.get(req.getCommand()).execute(req);
        } catch (WrongFieldCommandException e) {
            return new Response<>(false, e.getMessage());
        }
    }

    public Product createProductForRemoving(ProductDto dto){
        Product productToCompare = new Product(
                dto.getName(),
                dto.getCoordinates(),
                dto.getPrice(),
                dto.getPartNumber(),
                dto.getManufactureCost(),
                dto.getUnitOfMeasure(),
                dto.getOwner()
        );
        return productToCompare;
    }
}