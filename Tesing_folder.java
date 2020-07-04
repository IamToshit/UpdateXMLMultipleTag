import java.io.File;

public class Tesing_folder {
    public static void main(String args[])
    {
     String file_path = "C:\\Folder_A\\D\\E\\input_xml.xml";
     String output_file = "C:\\Folder_A\\D\\F\\users_updated.xml";

     ModifyXMLFileInJava mv = new ModifyXMLFileInJava();
     mv.modify(file_path,output_file);
    }
}
