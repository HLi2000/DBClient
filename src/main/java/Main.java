public class Main {
    public static void main(String[] args){
        Client cl=new Client();
        try{
            cl.makePostRequest();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
