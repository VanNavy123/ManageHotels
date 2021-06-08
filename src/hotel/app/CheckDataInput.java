package hotel.app;
import java.sql.Date;

public class CheckDataInput {
	// Ham chuan hoa xau ho ten nhap vao:
    public static String chuannHoa_str(String str) 
    {
    	String st = "";
        st=str.trim().toLowerCase();
        st = st.replaceAll("\\s+", " ");
        String[] temp= st.split(" ");
        st="";
        for(int i=0;i<temp.length;i++) 
        {
            st+=String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if(i<temp.length-1)
                st+=" ";
        }
        return st;
    }
    
    //Ham chuan hoa ngay thang nam viet nam:
    public static Date ConvertToDateVN(String str)
    {
    	try
    	{
        	String[] s = str.split("/");
        	Date d = Date.valueOf(s[2] + "-" + s[1] + "-" + s[0]);
        	return d;
    	}
    	catch (Exception e) {
    		return null;
		}

    }
}
