package workers;

public class ConsoleWorker {

    public static void println(String string){
        System.out.println(string);
    }

    public static void print(String string){
        System.out.print(string);
    }

    public static void printSymbol(boolean command){
        System.out.print("-> ");
    }
}
