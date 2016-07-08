package announce.blockynights;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class main extends JavaPlugin implements Listener {
	
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://host";
	   static final String USER = "user";
	   static final String PASS = "password";

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	getAnnounceGlobal();
            }
        }, 20L, 36000L);
        BukkitScheduler schedul = Bukkit.getServer().getScheduler();
        schedul.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	getAnnounceMain();
            }
        }, 19000L, 36000L);
        BukkitScheduler schedu = Bukkit.getServer().getScheduler();
        schedu.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	getAnnounceSolar();
            }
        }, 20L, 600L);
	}
	@Override
	public void onDisable() {
	}
	public void getAnnounceGlobal() {
		String server = "check_global";
		Connection conn = null;
		Statement stmt = null;
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      stmt = conn.createStatement();
		    	  String sql_row_check = "SELECT COUNT(*) FROM an_global";
		    	  ResultSet row_check = stmt.executeQuery(sql_row_check);
		    	  row_check.next();
		    	  int rows = row_check.getInt(1);
		    	  String sql_checker = "SELECT check_global FROM an_check";
		    	  ResultSet checker = stmt.executeQuery(sql_checker);
		    	  checker.next();
		    	  int checking = checker.getInt(1);
			      String sql = "SELECT an_global.message,an_global.perm FROM an_global WHERE an_global.count="+checking+"";
			      ResultSet rs = stmt.executeQuery(sql);
			      if ((rs.next())) {
			    	  String msg = rs.getString("an_global.message");
			    	  String perm = rs.getString("an_global.perm");
			    	  for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			    		  if (player.hasPermission(perm)) {
			    		  player.sendMessage(msg.replace('&','§'));
			    		  }
			    	  }
			    	  if (checking >= rows) {
			    	  checking = 0;
			    	  }
			    	  checking++;
			    	  updateCheck(checking, rows, server);

			      }
			      rs.close();stmt.close();conn.close();
		   }catch(SQLException se){se.printStackTrace();}catch(Exception e){e.printStackTrace();}
	   		finally{try{if(stmt!=null)stmt.close();}catch(SQLException se2){}try{if(conn!=null)conn.close();}catch(SQLException se){se.printStackTrace();}}
	}
	public void getAnnounceMain() {
		String server = "check_main";
		String serv = "an_main";
		Connection conn = null;
		Statement stmt = null;
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      stmt = conn.createStatement();
		    	  String sql_row_check = "SELECT COUNT(*) FROM "+serv+"";
		    	  ResultSet row_check = stmt.executeQuery(sql_row_check);
		    	  row_check.next();
		    	  int rows = row_check.getInt(1);
		    	  String sql_checker = "SELECT "+server+" FROM an_check";
		    	  ResultSet checker = stmt.executeQuery(sql_checker);
		    	  checker.next();
		    	  int checking = checker.getInt(1);
			      String sql = "SELECT "+serv+".message,"+serv+".perm FROM "+serv+" WHERE "+serv+".count="+checking+"";
			      ResultSet rs = stmt.executeQuery(sql);
			      if ((rs.next())) {
			    	  String msg = rs.getString(""+serv+".message");
			    	  String perm = rs.getString(""+serv+".perm");
			    	  for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			    		  if (player.hasPermission(perm)) {
			    		  player.sendMessage(msg.replace('&','§'));
			    		  }
			    	  }
			    	  if (checking >= rows) {
			    	  checking = 0;
			    	  }
			    	  checking++;
			    	  updateCheck(checking,rows,server);
			      }
			      rs.close();stmt.close();conn.close();
		   }catch(SQLException se){se.printStackTrace();}catch(Exception e){e.printStackTrace();}
	   		finally{try{if(stmt!=null)stmt.close();}catch(SQLException se2){}try{if(conn!=null)conn.close();}catch(SQLException se){se.printStackTrace();}}
	}
	
	public void getAnnounceSolar() {
		String server = "check_solar";
		String serv = "an_solar";
		Connection conn = null;
		Statement stmt = null;
		   try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);
			      stmt = conn.createStatement();
		    	  String sql_row_check = "SELECT COUNT(*) FROM "+serv+"";
		    	  ResultSet row_check = stmt.executeQuery(sql_row_check);
		    	  row_check.next();
		    	  int rows = row_check.getInt(1);
		    	  String sql_checker = "SELECT "+server+" FROM an_check";
		    	  ResultSet checker = stmt.executeQuery(sql_checker);
		    	  checker.next();
		    	  int checking = checker.getInt(1);
			      String sql = "SELECT "+serv+".message,"+serv+".perm FROM "+serv+" WHERE "+serv+".count="+checking+" AND "+serv+".perm='an.solar'";
			      ResultSet rs = stmt.executeQuery(sql);
			      if ((rs.next())) {
			    	  String msg = rs.getString(""+serv+".message");
			    	  String perm = rs.getString(""+serv+".perm");
			    	  for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			    		  if (player.hasPermission(perm)) {
			    		  player.sendMessage(msg.replace('&','§'));
			    		  }
			    	  }
			    	  if (checking >= rows) {
			    	  checking = 0;
			    	  }
			    	  checking++;

			      }
			      rs.close();stmt.close();conn.close();
		   }catch(SQLException se){se.printStackTrace();}catch(Exception e){e.printStackTrace();}
	   		finally{try{if(stmt!=null)stmt.close();}catch(SQLException se2){}try{if(conn!=null)conn.close();}catch(SQLException se){se.printStackTrace();}}
	}
	public void updateCheck(int check, int rows, String server) {
		Connection conn = null;
		Statement stmt = null;
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      stmt = conn.createStatement();
		      String sql = "UPDATE an_check SET "+server+" = '"+check+"' WHERE ID='1'";
		      stmt.executeUpdate(sql);
		      
		stmt.close();conn.close();
	   }catch(SQLException se){se.printStackTrace();}catch(Exception e){e.printStackTrace();}
  		finally{try{if(stmt!=null)stmt.close();}catch(SQLException se2){}try{if(conn!=null)conn.close();}catch(SQLException se){se.printStackTrace();}}
	}
}
