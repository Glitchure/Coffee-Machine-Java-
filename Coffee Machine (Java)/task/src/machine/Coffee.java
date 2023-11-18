package machine;

// I couldn't figure out inheritance so I just left this class here just in case I want to try again
public class Coffee {
    protected static int requiredMlOfWater;
    protected static int requiredMlOfMilk;
    protected static int requiredGOfCoffeeBeans;
    protected static int price;

    public static boolean isEnoughSupplies() {
        return CoffeeMachine.mlOfWater >= requiredMlOfWater && CoffeeMachine.mlOfMilk >= requiredMlOfMilk && CoffeeMachine.gOfCoffeeBeans >= requiredGOfCoffeeBeans && CoffeeMachine.numOfDisposableCups > 0;
    }

    public static void printInsufficientSupplies() {
        StringBuilder message = new StringBuilder();
        if (CoffeeMachine.mlOfWater < requiredMlOfWater) {
            message.append("water, ");
        } else if (CoffeeMachine.mlOfMilk < requiredMlOfMilk) {
            message.append("milk, ");
        } else if (CoffeeMachine.gOfCoffeeBeans < requiredGOfCoffeeBeans) {
            message.append("coffee beans, ");
        } else if (CoffeeMachine.numOfDisposableCups < 1) {
            message.append("disposable cups, ");
        }
        message.replace(message.length() - 2, message.length(), ".");
        System.out.println("Sorry, there are not enough resources to make that coffee. Here's what I'm missing: " + message);
    }
}
