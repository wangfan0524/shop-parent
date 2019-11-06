public class Singlton {
    private static final Singlton singlton=new Singlton();

    public static Singlton getSinglton(){
        return  singlton;
    }

    public Singlton() {
        getSinglton();
    }
}
