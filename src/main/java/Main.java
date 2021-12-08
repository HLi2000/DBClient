import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

        if (img_a.length!=0) {
            for (Img img : img_a) {
                img.setThumbnail(cl.thumbnail(img.getFile_name()));
            }
        }

        Img img_selected=new Img();
        img_selected.setFile_name("1.jpg");
        InputStream img_stream=cl.img(img_selected.getFile_name());
    }
}
