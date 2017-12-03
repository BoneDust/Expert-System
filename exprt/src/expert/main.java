package expert;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {

    public  static  void main (String args[]) {


        BufferedReader br = null;
        FileReader fr = null;

        try
        {
            fr = new FileReader(args[1]);
            br = new BufferedReader(fr);

            String Line;

            while ((Line = br.readLine()) != null)
            {
                System.out.println(Line);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
