import org.dom4j.DocumentException;

public class Test {

    public static  void main(String [] args){
       /* double f=13;
        String str="13";
        double d=Double.valueOf(str);
        String str1= String.valueOf(d);
        System.out.println(d);
        System.out.println(str1);
        System.out.println(f==d);*/


        //模拟IOC容器
        /*ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("test.xml");
        try {
            System.out.println(applicationContext.getBean("user2")); ;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }*/
        Singlton singlton=Singlton.getSinglton();
        Singlton singlton1=Singlton.getSinglton();

        Singlton singlton3=new Singlton();
        Singlton singlton4=new Singlton();



    }
}
