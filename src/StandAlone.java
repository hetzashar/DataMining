import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StandAlone {

	public static void main(String[] args) throws ParseException {
	
		String date="2015-01-31T12:59";
		
		int year=Integer.parseInt(date.substring(0, 4));
		int month=Integer.parseInt(date.substring(5, 7));
		int day=Integer.parseInt(date.substring(8, 10));
		int hourOfDay=Integer.parseInt(date.substring(11, 13));
		int minute=Integer.parseInt(date.substring(14));
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hourOfDay, minute);
		Date dte=cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println("DAte::" +sdf.format(dte));
		//Date dte=sdf.parse(date);
		//System.out.println("DAte:: "+dte);
	}
	
}
