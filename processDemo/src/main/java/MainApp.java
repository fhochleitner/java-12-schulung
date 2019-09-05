public class MainApp {

    public static void main(String[] args) {

        ProcessHandle.allProcesses().map(p -> p.info().command()).filter(command -> command.isPresent())
                .forEach(command -> System.out.println(command));

        System.out.println("\n-------------------------------------\n");
        ProcessBuilder.Redirect

    }
}
