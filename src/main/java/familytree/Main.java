package familytree;

import familytree.view.Application;

public final class Main {

  private Main(){

  }

  public static void main(String[] args) { //NOPMD - suppressed MethodArgumentCouldBeFinal
    final Application application = new Application();
    application.run();
  }

}
