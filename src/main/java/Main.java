import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] modality_a={"MRI","Xray"};
        String[] region_a={};
        String patient_name_a="A A";

        SearchInfo searchInfo=new SearchInfo(modality_a,region_a,patient_name_a);

        Client cl=new Client();

        Img[] img_a;
        try{
            img_a=cl.search(searchInfo);
        }catch (Exception e){
            e.printStackTrace();
            img_a= new Img[]{};
        }
        for (Img img : img_a) {
            System.out.println(img.getFile_name());

            //for test
            Image image = ImageIO.read(img.getThumbnail());
            JFrame frame = new JFrame();
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        }

        //click actionListener should set img_selected
        Img img_selected=new Img();
        img_selected.setFile_name("1.jpg");
        InputStream img_stream=cl.getImg(img_selected);

        //for test
        Image image = ImageIO.read(img_stream);
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
