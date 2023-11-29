package by.bsuir.phoneshop.web.command;

import by.bsuir.phoneshop.web.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    private Map<CommandName, Command> commands;
    private static class SingletonHandler {
        private static final CommandHandler INSTANCE = new CommandHandler();
    }
    public static CommandHandler getInstance() {
        return SingletonHandler.INSTANCE;
    }
    private CommandHandler() {
        commands = new HashMap<>();
        commands.put(CommandName.LOGIN, new LoginCommand());
        commands.put(CommandName.PRODUCT_LIST, new ProductListCommand());
        commands.put(CommandName.PRODUCT_DETAILS, new ProductDetailsCommand());
        commands.put(CommandName.ADD_TO_CART, new AddToCartCommand());
        commands.put(CommandName.CART, new CartCommand());
        commands.put(CommandName.DELETE_CART_ITEM, new DeleteCartItemCommand());
        commands.put(CommandName.UPDATE_CART, new UpdateCartCommand());
        commands.put(CommandName.CHECKOUT, new CheckoutCommand());
        commands.put(CommandName.PLACE_ORDER, new PlaceOrderCommand());
        commands.put(CommandName.ORDER_OVERVIEW, new OrderOverviewCommand());
        commands.put(CommandName.MINICART, new MinicartCommand());
        commands.put(CommandName.AUTHORISE, new AuthoriseCommand());
        commands.put(CommandName.REGISTER, new RegisterCommand());
        commands.put(CommandName.SIGNUP, new SignUpCommand());
        commands.put(CommandName.LOGOUT, new LogoutCommand());
        commands.put(CommandName.USERS, new UsersCommand());
        commands.put(CommandName.UPDATE_USERS, new UpdateUsersCommand());
    }
    public Command getCommandByName(CommandName name) {
        return commands.get(name);
    }
}
