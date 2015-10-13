package com.example.artroo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {

	// Within which the entire activity is enclosed
	DrawerLayout mDrawerLayout;
	String curDate;
	// ListView represents Navigation Drawer
	ListView mDrawerList;
	ListView mainList;
	Operation operation;
	// ActionBarDrawerToggle indicates the presence of Navigation Drawer in the action bar
	ActionBarDrawerToggle mDrawerToggle;

	// Title of the action bar
	String mTitle="";

	private ArrayList<DataForMain> db_dates;
	private ArrayList<Operation> operationList;
	private ArrayList<User> manualList;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = (String) getTitle();
		// Getting reference to the DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		mainList =(ListView)findViewById(R.id.mainList);

		//getting current date
		Date dt = new Date();
		int date = dt.getDate();
		int month = dt.getMonth()+1;
		int year = dt.getYear();
		year += 1900;
		int day = dt.getDay();
		String[] days = { "Sunday", "Monday", "Tuesday","WednesDay", "Thursday", "Friday", "Saterday" };
		curDate = date + "/" + month + "/" + year + " " + days[day];
		// Getting reference to the ActionBarDrawerToggle
		mDrawerToggle = new ActionBarDrawerToggle( this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close){

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Select Bills & EMIs");
				invalidateOptionsMenu();
			}
		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		// Creating an ArrayAdapter to add items to the listview mDrawerList
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getBaseContext(),
				R.layout.drawer_list ,
				getResources().getStringArray(R.array.details)
				);

		// Setting the adapter on mDrawerList
		mDrawerList.setAdapter(adapter);

		// Enabling Home button
		getActionBar().setHomeButtonEnabled(true);

		// Enabling Up navigation
		getActionBar().setDisplayHomeAsUpEnabled(true);


		//get the operation object which contains the latest account details

		List<Sms> smsList = readAllSms();
		operationList=new ArrayList<Operation>();
		//ArrayList<Operation> operations = new ArrayList<Operation>();

		for (Sms sms : smsList) {

			if ((sms.from).contains("Axis")) {
				if(sms.body.contains("credit") || sms.body.contains("debit"))
				{
					operation = parseOperation(sms.body,sms.date,sms.from);
					//operationList.add(operation);
					break;

				}
			}
		}

		// Setting item click listener for the listview mDrawerList
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,
					int position,
					long id) {

				
				String[] details = getResources().getStringArray(R.array.details);

				Intent intent = null;
				//starting another activity corrsponding to each list item in drawerItem
				switch (position) {
				case 0:
					intent=new Intent(MainActivity.this,BankBalanceActivity.class);
					loadExpenses();
					intent.putExtra("key",db_dates);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(MainActivity.this,BankBalanceActivity.class);
					loadIncome();
					intent.putExtra("key",db_dates);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,CashMainActivity.class);
					intent.putExtra("key",details[position]);
					intent.putExtra("dateValue",curDate);
					break;
				case 3:
					intent=new Intent(MainActivity.this, SpendSumarryActivity.class);
					intent.putExtra("key",details[position]);
					intent.putExtra("dateValue",curDate);
					break;
				case 4:
					//intent=new Intent(MainActivity.this,LikeUsOnFbActivity.class);
					intent=new Intent(MainActivity.this,RefreshSmSActivity.class);

					intent.putExtra("key",details[position]);
					intent.putExtra("dateValue",curDate);
					break;
				case 5:

					intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(Constant.fbURL));
					//startActivity(browserIntent);

					break;
				case 6:
					intent = new Intent(Intent.ACTION_VIEW);
					intent.setData(Uri.parse(Constant.twitterURL));
					//startActivity(browserIntent1);

					break;
				case 7:
					intent=new Intent(MainActivity.this,AboutActivity.class);
					intent.putExtra("key",details[position]);
					intent.putExtra("dateValue",curDate);
					break;

				default:
					break;
				}


				startActivity(intent);


			}
		});


		ArrayList<DataForMain> list=getList(); 
		mainList.setAdapter(new StableArrayAdapter(this,list));
		mainList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
					View view,
					int position,
					long id) {


				String[] details = getResources().getStringArray(R.array.details);

				Intent intent = null;
				//starting another activity corrsponding to each list item in drawerItem
				switch (position) {
				case 0:
					intent=new Intent(MainActivity.this,BankBalanceActivity.class);
					loadExpenses();
					intent.putExtra("key",db_dates);
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(MainActivity.this,IncomeActivity.class);
					loadIncome();
					intent.putExtra("key",db_dates);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,SpendActivity.class);
					intent.putExtra("key",details[position]);
					startActivity(intent);
					break;
				case 3:
					intent=new Intent(MainActivity.this, TopSpendAreas.class);
					intent.putExtra("key","top Spend Areas");
					startActivity(intent);
					break;
				case 4:
					intent=new Intent(MainActivity.this,CashMainActivity.class);
					intent.putExtra("key","Cash");
					startActivity(intent);
					break;
				case 5:
					intent=new Intent(MainActivity.this,LatestTransactionActivity.class);
					intent.putExtra("key","Latest Transaction");
					startActivity(intent);
					break;
				case 6:
					intent=new Intent(MainActivity.this,RelaxBillActivity.class);
					intent.putExtra("key","Relax Bill");
					startActivity(intent);
					break;


				default:
					break;
				}







			}
		});

	}

	private ArrayList<DataForMain> getList() {
		// TODO Auto-generated method stub
		ArrayList<DataForMain> list=new ArrayList<DataForMain>();
		DataForMain main=new DataForMain();
		main.setName("Bank Balance");
		main.setAmount("Rs "+operation.getAmount()+"");
		list.add(main);

		main=new DataForMain();
		main.setName("Income/Deposits");
		main.setAmount("Rs "+operation.getAmount()+"");
		list.add(main);

		main=new DataForMain();
		main.setName("Spend");
		main.setAmount("Rs "+operation.getDebited()+"");
		list.add(main);

		main=new DataForMain();
		//max amount spend
		main.setName("Top Spend Areas");
		main.setAmount("Rs "+operation.getDebited()+"");
		list.add(main);

		main=new DataForMain();
		main.setName("Cash");
		String cashMoney=loadManualExpense();
		if(cashMoney==null)
		{
		main.setAmount("No cash activity yet");
		}
		else
		{
			main.setAmount("Rs "+cashMoney);	
		}
		list.add(main);

		main=new DataForMain();
		main.setName("Latest Transactions");
		main.setAmount("Rs "+"100");
		list.add(main);

		main=new DataForMain();
		main.setName("Relax,No Bill Due");
		main.setAmount("Rs "+100+"");
		list.add(main);

		return list;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the drawer is open, hide action items related to the content view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private List<Sms> readAllSms() {
		List<Sms> list = new ArrayList<Sms>();

		Uri request = Uri.parse("content://sms/inbox");

		Cursor cur = getContentResolver().query(request,
				new String[]{"address", "body", "date"}, null, null, null);

		if (cur == null) {
			return list;
		}

		while (cur.moveToNext()) {
			final Date date = new Date(Long.parseLong(cur.getString(2)));
			final String from = cur.getString(0);
			final String body = cur.getString(1);
			Log.d("====",date+"===");

			list.add(new Sms(date, from, body));
		}

		cur.close();

		return list;
	}



	@SuppressWarnings("deprecation")
	private Operation parseOperation(String text,Date date,String bankName) {
		Operation op=new Operation();
        Log.d("text",text);
		String[] id1=text.split("\\D+");
		String[] id2=text.split("\\d+");
		String[] id3=text.split(":");
		if(id2[5].contains("balance"))
		{
			op.setAmount(new BigDecimal(id1[6]));
		}
		/*Date date=new Date();
		date.setDate(Integer.parseInt(id1[5]));
		date.setMonth(Integer.parseInt(id1[4]));
		date.setYear(Integer.parseInt(id1[3]));*/
//        Log.d("date=",date.toGMTString()+"==="+date.toLocaleString()+"=="+date.toString());
		//long date1=getGMTTimeStampFromDate(date+"");
		op.setDate(date);
		op.setBankName(bankName);
		//op.setThrough(id3[1]);
		if(id2[1].contains("debit"))
		{
			op.setDebited(new BigDecimal(id1[2]));
			createRecord(op.getDate(),op.getAmount(),"debited",op.getBankName());

		}
		else
		{
			op.setCredited(new BigDecimal(id1[2]));	
			createRecord(op.getDate(),op.getAmount(),"credited",op.getBankName());
		}
		
		return op;
	}

	public static long getGMTTimeStampFromDate(String datetime) {
	    long timeStamp = 0;
	    Date localTime = new Date();

	    String format = "dd-MM-yyyy HH:mm:ss";
	    SimpleDateFormat sdfLocalFormat = new SimpleDateFormat(format);
	    sdfLocalFormat.setTimeZone(TimeZone.getDefault());

	    try {

	        localTime = (Date) sdfLocalFormat.parse(datetime); 

	        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"),
	                Locale.getDefault());
	        TimeZone tz = cal.getTimeZone();

	        cal.setTime(localTime);

	        timeStamp = (localTime.getTime()/1000);
	        Log.d("GMT TimeStamp: ", " Date TimegmtTime: " + datetime
	                + ", GMT TimeStamp : " + localTime.getTime());

	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    return timeStamp;

	}
	

	//databse storage for all the expenses made in one year/one month/one day
	private void createRecord(Date date, BigDecimal amount, String type,String bankName) {
		Log.d("date",date+"");
		ExpenseDatabase db = new ExpenseDatabase(getApplicationContext());
		db.open();
		if(type.equals("debited"))
		{
			db.createAlert(ExpenseDatabase.EXPENSE_TABLE,
					ExpenseDatabase.EXPENSE_INT, new String[] { "" + amount,
					"" + date,"" + bankName });
		}
		else
		{

			db.createAlertIncome(ExpenseDatabase.INCOME_TABLE,
					ExpenseDatabase.EXPENSE_INT, new String[] { "" + amount,
					"" + date ,"" + bankName });
		}
		db.close();
	}



	private void loadExpenses() {
		db_dates = new ArrayList<DataForMain>();
		//db_amnt = new ArrayList<String>();

		ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
		dbase.open();
		Cursor db_cur = dbase.fetchAllAlerts(ExpenseDatabase.EXPENSE_TABLE,
				ExpenseDatabase.EXPENSE_INT);
		if (db_cur != null && db_cur.getCount() > 0) {
			db_cur.moveToFirst();
			do {
				DataForMain data=new DataForMain();

				data.setAmount(db_cur.getLong(1)+"");data.setName(db_cur.getString(3));
				Log.d("date==",db_cur.getString(2));
				db_dates.add(data);

			} while (db_cur.moveToNext());
		}
		dbase.close();
	}


	private void loadIncome() {
		db_dates = new ArrayList<DataForMain>();
		//db_amnt = new ArrayList<String>();

		ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
		dbase.open();
		Cursor db_cur = dbase.fetchAllIncomeAlerts(ExpenseDatabase.INCOME_TABLE,
				ExpenseDatabase.EXPENSE_INT);
		if (db_cur != null && db_cur.getCount() > 0) {
			db_cur.moveToFirst();
			do {
				DataForMain data=new DataForMain();

				data.setAmount(db_cur.getLong(2)+"");data.setName(db_cur.getString(1));
				db_dates.add(data);
			} while (db_cur.moveToNext());
		}
		dbase.close();
	}
	
	private String loadManualExpense() {
		 //manualList = new ArrayList<User>();
			//db_amnt = new ArrayList<String>();
		 String money = null;
			ExpenseDatabase dbase = new ExpenseDatabase(getApplicationContext());
			dbase.open();
			Cursor db_cur = dbase.fetchAllManualAlerts(ExpenseDatabase.MANUAL_TABLE,
					ExpenseDatabase.EXPENSE_INT);
			if (db_cur != null && db_cur.getCount() > 0) {
				db_cur.moveToFirst();
			money=	db_cur.getString(5);
			}
			dbase.close();
			return money;
		}
}
