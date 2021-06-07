package hotel.app;

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
}
