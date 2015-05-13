package mahout.tut.recommender;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.TanimotoCoefficientSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;



public class ItemRecommender {
	
	public static Connection conn=null;
	
	public static void mysqlcon(){
	try{
		 String myDriver = "org.gjt.mm.mysql.Driver";
	      String myUrl = "jdbc:mysql://harinisjsu.chymyuerorpt.us-west-2.rds.amazonaws.com/test";
	      try{
	      Class.forName(myDriver);
	      conn = DriverManager.getConnection(myUrl, "root", "harinisjsu");
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	      
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	
	public static void mysql(String[] args){
	     
	      // create a sql date object so we can use it in our INSERT statement
	     
	      // the mysql insert statement
	      String query = " insert into similarity values (?, ?, ?, ?, ?, ?, ?)";
	 try{
	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn.prepareStatement(query);
	      preparedStmt.setString (1, args[0]);
	      preparedStmt.setString (2, args[1]);
	      preparedStmt.setString (3, args[2]);
	      preparedStmt.setString (4, args[3]);
	      preparedStmt.setString (5, args[4]);
	      preparedStmt.setString (6, args[5]);
	      preparedStmt.setString (7, args[6]);
	 
	      // execute the preparedstatement
	      
			preparedStmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	      //conn.close();
	      
	}

	public static void main(String[] args) {
		mysqlcon();
		// TODO Auto-generated method stub
		try {
			DataModel model = new FileDataModel(new File("output1.csv"));
			LogLikelihoodSimilarity item = new LogLikelihoodSimilarity(model);
			
			
			GenericItemBasedRecommender gm = new GenericItemBasedRecommender(model, item);

			int x=1;
			for(LongPrimitiveIterator prim = model.getItemIDs(); prim.hasNext();){
				long userid = prim.nextLong();
				List<RecommendedItem> listrecomm = gm.mostSimilarItems(userid, 3);
				System.out.println("Recommendations for the Userid: "+userid);
				String[] arg=new String[7];
				arg[0]=Long.toString(userid);
				int i=1;
				for(RecommendedItem recommendation : listrecomm){
					arg[i++]=Long.toString(recommendation.getItemID());
					arg[i++]=Float.toString(recommendation.getValue());
					//System.out.println("\t"+recommendation.getItemID()+","+recommendation.getValue());
				}
				//System.out.println(arg[0]+","+arg[1]+","+arg[2]+","+arg[3]+","+arg[4]+","+arg[5]+","+arg[6]);
				mysql(arg);
				x++;
				//if(x>10) System.exit(1);
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error.");
			e.printStackTrace();
		} catch (TasteException e) {
			// TODO Auto-generated catch block
			System.out.println("There was a taste error.");
			e.printStackTrace();
		}

	}

}
