package com.bizsoft.fmcgv2.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.bizsoft.fmcgv2.LoginActivity;
import com.bizsoft.fmcgv2.MainActivity;
import com.bizsoft.fmcgv2.R;
import com.bizsoft.fmcgv2.Tables.TransactionType;
import com.bizsoft.fmcgv2.dataobject.AccountGroup;
import com.bizsoft.fmcgv2.dataobject.Company;
import com.bizsoft.fmcgv2.dataobject.Customer;
import com.bizsoft.fmcgv2.dataobject.Product;
import com.bizsoft.fmcgv2.Tables.Sale;
import com.bizsoft.fmcgv2.dataobject.StockGroup;
import com.bizsoft.fmcgv2.dataobject.Store;
import com.bizsoft.fmcgv2.dataobject.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.bizsoft.fmcgv2.service.Network.TAG;

/**
 * Created by GopiKing on 22-12-2017.
 */

public class SignalRService {


    static final String TAG = "SIGNAL R SERVICE";
    public static ArrayList<Company>  getCompanyDetails(Context context)
    {
        ArrayList<LinkedTreeMap> companyCollection = new ArrayList<LinkedTreeMap>();
        try {
            companyCollection = Store.getInstance().mHubProxyCaller.invoke(companyCollection.getClass(),"CompanyDetail_List").get();



            System.out.println("Obj ---------"+companyCollection.size());

            ArrayList<Company> companyArrayList = new ArrayList<Company>();
            for(int i=0;i<companyCollection.size();i++) {
                LinkedTreeMap<Object, Object> map = new LinkedTreeMap<Object, Object>();
                Company company = new Company();
                map = companyCollection.get(i);
                System.out.println("----------0---->" + map);
                for (Map.Entry<Object, Object> entry : map.entrySet()) {
                    {
                        System.out.println(entry.getKey() + "/" + entry.getValue());
                        String key = (String) entry.getKey();
                        Object value = entry.getValue();
                        if (value != null) {
                            Class<? extends Object> c = value.getClass();
                             System.out.println("----------1---->" + value.getClass());
                        }
                        if (value instanceof String || value instanceof Boolean || value instanceof Double || value instanceof Float) {
                            String v = String.valueOf(value);
                             System.out.println("----------2" + v);
                            if (key.equals("Id")) {
                                long l = (new Double(v)).longValue();
                                company.setId(Long.valueOf(l));
                            }
                            if (key.equals("CompanyName")) {

                                company.setCompanyName(v);
                            }
                            if (key.equals("CompanyType")) {

                                company.setCompanyType(v);
                            }
                            if (key.equals("IsActive")) {

                                company.setActive(Boolean.valueOf(v));
                            }
                            if (key.equals("AddressLine1")) {

                                company.setAddressLine1(v);
                            }
                            if (key.equals("AddressLine2")) {

                                company.setAddressLine2(v);
                            }
                            if (key.equals("TelephoneNo")) {

                                company.setTelephoneNo(v);
                            }
                            if (key.equals("EMailId")) {

                                company.setEMailId(v);
                            }
                            if (key.equals("GSTNo")) {

                                company.setGSTNo(v);
                            }
                            if (key.equals("CityName")) {

                                company.setCityName(v);
                            }
                            if (key.equals("UnderCompanyId")) {

                                company.setUnderCompanyId(v);
                            }
                        }


                    }


                }
                System.out.println("adding---------"+"----"+i);
                companyArrayList.add(company);




                //displayDM(companyDetails);
            }
            Store.getInstance().companyList = companyArrayList;
            if (Store.getInstance().companyList.size() != 0) {
                Store.getInstance().serverStatus = "Online";

            } else {
                Store.getInstance().serverStatus = "Offline";
                Log.d(TAG, "No Company list found");

                Toast.makeText(context, "No company List Found", Toast.LENGTH_SHORT).show();
                //  Intent intent = new Intent(context,LoginActivity.class);
                //context.startActivity(intent);
            }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
        System.out.println("____________________"+Store.getInstance().companyList.size());
        Store.getInstance().dealerList.clear();
        for(int i=0;i<Store.getInstance().companyList.size();i++)
        {


            if(Store.getInstance().companyList.get(i).getUnderCompanyId()!=null && Store.getInstance().companyList.get(i).getCompanyType().toLowerCase().contains("dealer"))
            {
                System.out.println("UnderCompanyId = "+Store.getInstance().companyList.get(i).getUnderCompanyId());
                System.out.println("Dealer Name = "+Store.getInstance().companyList.get(i).getCompanyName());
                Store.getInstance().dealerList.add(Store.getInstance().companyList.get(i));
            }

        }
        System.out.println("Dealer Size ----"+Store.getInstance().dealerList.size());
        return Store.getInstance().companyList;



    }
    public static boolean login(Context context,String dlrName,String usrName,String passWord)
    {
        boolean status = false;
        //-----------------------------------------------
        SharedPreferences prefs = context.getSharedPreferences(Store.getInstance().MyPREFERENCES, context.MODE_PRIVATE);
        boolean isLogged = prefs.getBoolean(context.getString(R.string.isLogged), false);
        String username = prefs.getString(context.getString(R.string.username), "");
        String password = prefs.getString(context.getString(R.string.password), "");
        String dealerName = prefs.getString(context.getString(R.string.dealerName), "0");
        String dealerId = prefs.getString(context.getString(R.string.dealerID), "0");
        String companyId = prefs.getString(context.getString(R.string.companyID), "0");
        String companyGSTnO = prefs.getString(context.getString(R.string.gstNo), "0");


        System.out.println("company name =============================="+prefs.getString(context.getString(R.string.phoneNumber), "0"));
        System.out.println("company address line 1 =============================="+prefs.getString(context.getString(R.string.companyAddressLine1), "0"));
        System.out.println("company address line 2 =============================="+prefs.getString(context.getString(R.string.companyAddressLine2), "0"));
        System.out.println("company gst =============================="+prefs.getString(context.getString(R.string.gstNo), "0"));
        System.out.println("company mail =============================="+prefs.getString(context.getString(R.string.email), "0"));
        System.out.println("company postalcode =============================="+prefs.getString(context.getString(R.string.postal_code), "0"));
        Store.getInstance().companyPosition = Integer.parseInt(prefs.getString(context.getString(R.string.companyPosition), "0"));

        String year = BizUtils.getCurrentYear();
        System.out.println("Acc year  =============================="+year);
        if(dealerName!=null && !dealerName.equals(""))
        {

            Store.getInstance().dealerName = dealerName;
            System.out.println("dealerName = "+ Store.getInstance().dealerName);
            Store.getInstance().dealerId = Long.valueOf(dealerId);
            Store.getInstance().companyID = Long.valueOf(companyId);

        }

        //-----------------------------------------------------------SL

        System.out.println("isLogged = "+isLogged);
        System.out.println("mHubProxyCaller = "+ Store.getInstance().mHubProxyCaller);
        if(isLogged)
        {
            try {
                if(usrName!=null && passWord!=null & dlrName !=null)
                {
                    System.out.println("Login details____NEWLOGIN"+Store.getInstance().user);

                    Store.getInstance().user = Store.getInstance().mHubProxyCaller.invoke(User.class,"userAccount_Login",year,dlrName,usrName,passWord).get();
                    if(Store.getInstance().user!=null)
                    {
                        status = true;
                    }
                }
                else
                {
                    Store.getInstance().user = Store.getInstance().mHubProxyCaller.invoke(User.class,"userAccount_Login",year,dealerName,username,password).get();
                    System.out.println("Login details____OLDLOGIN"+Store.getInstance().user);
                    if(Store.getInstance().user!=null)
                    {
                        status = true;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                status = false;
            } catch (ExecutionException e) {
                e.printStackTrace();
                status = false;
            }

        }
        else
        {
            ArrayList<Company> s = getCompanyDetails(context);
            System.out.print("--Test--"+s.size());
            Store.getInstance().serverStatus = "Online";
            Intent mainIntent = new Intent(context,LoginActivity.class);
            context.startActivity(mainIntent);
            Store.getInstance().mainActvity.finish();
        }

        return  status;
        //---------------------------------------------------------------------------
    }

    public static boolean newLogin(Context context, String dlrName, String usrName, String passWord)
    {
        boolean status = false;
        String year = BizUtils.getCurrentYear();

        if(usrName!=null && passWord!=null & dlrName !=null)
        {

            User obj = new User();
            try {
                obj = Store.getInstance().mHubProxyCaller.invoke(obj.getClass(),"userAccount_Login",year,dlrName,usrName,passWord).get();

                System.out.println("---user info---"+obj);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            if(obj!=null)
            {
                Store.getInstance().user = (User) obj;

                if(Store.getInstance().user.getId()!=null) {
                    status = true;
                    System.out.println("Login details____NEWLOGIN"+Store.getInstance().user);
                }
            }
        }


        return  status;

    }

    public static void customerList()
    {

        ArrayList<LinkedTreeMap> customreCollection = new ArrayList<LinkedTreeMap>();
        try {

            customreCollection = Store.getInstance().mHubProxyCaller.invoke(customreCollection.getClass(),"Customer_List").get();


            System.out.println("customertList----"+customreCollection.size());

            Store.getInstance().customerList.clear();

            for(int i=0;i<customreCollection.size();i++)
            {
                Customer customer = new Customer();
                final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                customer = mapper.convertValue(customreCollection.get(i),Customer.class);
                System.out.println("Customer Id"+customer.getId());
                Store.getInstance().customerList.add(customer);

            }


        } catch (InterruptedException e) {


        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("customer_list----"+customreCollection.size());
    }
    public static void productList()
    {

        ArrayList<LinkedTreeMap> companyCollection = new ArrayList<LinkedTreeMap>();

        try {
            companyCollection = Store.getInstance().mHubProxyCaller.invoke(companyCollection.getClass(),"Product_List").get();

            Store.getInstance().productList.clear();
            for(int i=0;i<companyCollection.size();i++)
            {
                Product product = new Product();
                final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                product  = mapper.convertValue(companyCollection.get(i),Product.class);
                System.out.println("Product Id"+product.getId());
                Store.getInstance().productList.add(product);

            }


        } catch (InterruptedException e) {


        } catch (ExecutionException e) {

            e.printStackTrace();
        }

    }
    public static void stockHomeList()
    {
        ArrayList<LinkedTreeMap> companyCollection = new ArrayList<LinkedTreeMap>();
        try {
            companyCollection = Store.getInstance().mHubProxyCaller.invoke(companyCollection.getClass(),"StockGroup_List").get();

            Store.getInstance().stockGroupList.clear();
            for(int i=0;i<companyCollection.size();i++)
            {
                StockGroup stockGroup = new StockGroup();
                final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                stockGroup  = mapper.convertValue(companyCollection.get(i),StockGroup.class);
                System.out.println("stockGroup Id"+stockGroup.getId());
                Store.getInstance().stockGroupList.add(stockGroup);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("customertList----"+Store.getInstance().stockGroupList.size());
    }
    public static Customer saveCustomer(Customer customer) {

        Object o = new Object();

        Customer customerResponse = new Customer();
        Gson gson = new Gson();
        try {
            System.out.println("---------------saving--"+customer.getLedgerName());

            o = Store.getInstance().mHubProxyCaller.invoke(o.getClass(),"Customer_Save",customer).get();
            System.out.println("---------------class type--"+o.getClass());



            final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            customerResponse  = mapper.convertValue(o,Customer.class);
            System.out.println("customer Id"+customerResponse.getId());



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        return customerResponse;

    }

    public static void   accountGroupList()
    {

        ArrayList<LinkedTreeMap> collections = new ArrayList<LinkedTreeMap>();
        try {
            collections = Store.getInstance().mHubProxyCaller.invoke(collections.getClass(),"AccountGroup_List").get();

            Store.getInstance().accountsGroupList.clear();
            for(int i=0;i<collections.size();i++)
            {
                AccountGroup  accountGroup = new AccountGroup();
                final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                accountGroup  = mapper.convertValue(collections.get(i),AccountGroup.class);
                System.out.println("accountGroup Id-"+accountGroup.getId());
                System.out.println("accountGroup Name-"+accountGroup.getGroupName());
                if(accountGroup.getGroupName().equals("Sundry Debtors"))
                {
                    Store.getInstance().currentAccGrpId = accountGroup.getId();
                    Store.getInstance().currentAccGrpName = accountGroup.getGroupName();
                    System.out.println("Current - accountGroup Name-"+accountGroup.getGroupName());
                }
                Store.getInstance().accountsGroupList.add(accountGroup);

            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("accountGroup----"+Store.getInstance().accountsGroupList.size());
    }

    public static boolean salesSave(Sale sale)
    {
        Object o = new Object();

        boolean status = false;
        try {
            Gson gson = new Gson();
            String json = gson.toJson(sale);
            System.out.println("JSON : " + json);


            //System.out.println(" ---------Sales  details size "+sale.getSDetail().size());
            o = Store.getInstance().mHubProxyCaller.invoke(o.getClass(),"Sales_Save",sale).get();

            System.out.println(" ---------Sale save"+o.getClass());

            status = (boolean) o;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return status;

    }

    public static void  getTransactionType()
    {

        ArrayList<LinkedTreeMap> collections = new ArrayList<LinkedTreeMap>();



        try {

            collections = Store.getInstance().mHubProxyCaller.invoke(collections.getClass(),"TransactionType_List").get();

            Store.getInstance().transactionTypeList.clear();
            for(int i=0;i<collections.size();i++)
            {
                TransactionType transactionType = new TransactionType();
                final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
                transactionType  = mapper.convertValue(collections.get(i),TransactionType.class);
                System.out.println("transactionType Id"+transactionType.getId());
                Store.getInstance().transactionTypeList.add(transactionType);



            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Trans type list size "+Store.getInstance().transactionTypeList.size());

    }



}
