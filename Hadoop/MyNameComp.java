import java.util.Comparator;

public class MyNameComp implements Comparator<Subscriber>{
	 
    @Override
    public int compare(Subscriber e1, Subscriber e2) {
    	 if(e1.count < e2.count){
             return 1;
         } else {
             return -1;
         }
    }
}